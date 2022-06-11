package com.brazhnyk.epam_finalproject_spring.entity;

import com.brazhnyk.epam_finalproject_spring.entity.subentity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "usr")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@ToString(of = {"id", "login"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String email;
    private String userPic;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    private String firstName;
    private String lastName;
}
