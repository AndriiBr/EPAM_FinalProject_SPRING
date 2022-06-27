package com.brazhnyk.epam_finalproject_spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@EqualsAndHashCode(of = { "id" })
@ToString(of = {"id"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleEn;
    private String roleUa;

    @OneToMany(mappedBy = "role")
    private List<User> userList;
}
