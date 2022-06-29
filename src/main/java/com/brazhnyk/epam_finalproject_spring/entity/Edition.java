package com.brazhnyk.epam_finalproject_spring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleEn;
    private String titleUa;

    private String textEn;
    private String textUa;

    private String titleImage;
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(
            mappedBy = "edition",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private List<UserEdition> users;

    public Edition(String titleEn, String titleUa, String textEn, String textUa, int price, Genre genre) {
        this.titleEn = titleEn;
        this.titleUa = titleUa;
        this.textEn = textEn;
        this.textUa = textUa;
        this.price = price;
        this.genre = genre;
    }
}
