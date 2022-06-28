package com.brazhnyk.epam_finalproject_spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_edition")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class UserEdition implements Serializable {

    @EmbeddedId
    private UserEditionId id;

    @MapsId("userId")
    @ManyToOne
    private User user;

    @MapsId("editionId")
    @ManyToOne
    private Edition edition;

    public UserEdition(User user, Edition edition) {
        this.user = user;
        this.edition = edition;
        this.id = new UserEditionId(user.getId(), getEdition().getId());
    }

}
