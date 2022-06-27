package com.brazhnyk.epam_finalproject_spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameEn;
    private String nameUa;

    @OneToMany(mappedBy = "genre")
    private List<Edition> editionList;
}
