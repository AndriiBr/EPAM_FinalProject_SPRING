package com.brazhnyk.epam_finalproject_spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "edition")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String titleUa;
    private String titleEn;
    private String titleImage;

//    private List<Genre> genres;
    private Double price;

}
