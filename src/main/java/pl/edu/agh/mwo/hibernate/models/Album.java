package pl.edu.agh.mwo.hibernate.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Album {
    @Id
    @Column(name = "album_id", nullable = false)
    private Integer id;

    private String name;

    private String description;

    @OneToMany
    private Set<Photo> photos;

    public Album() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
