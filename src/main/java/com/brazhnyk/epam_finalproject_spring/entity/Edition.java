package com.brazhnyk.epam_finalproject_spring.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titleEn;
    private String titleUa;

    private String textEn;
    private String textUa;

    private String titleImage;
    private int price;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany(mappedBy = "editions", cascade = CascadeType.ALL)
    private List<User> users;
}
