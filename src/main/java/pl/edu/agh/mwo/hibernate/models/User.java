package pl.edu.agh.mwo.hibernate.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    private String userName;

    private Date joinDate;

    @ManyToMany(mappedBy = "userLikes")
    private Set<Photo> photosLiked;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
