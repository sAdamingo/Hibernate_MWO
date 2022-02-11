package pl.edu.agh.mwo.hibernate.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    private String userName;

    private Date joinDate;

    @ManyToMany(mappedBy = "userLikes")
    private Set<Photo> photosLiked;

    @OneToMany
    private Set<Album> albums;

    public User() {
    }

    public User(String userName, Date joinDate) {
        this.userName = userName;
        this.joinDate = joinDate;
        this.photosLiked = new HashSet<>();
        this.albums = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Set<Photo> getPhotosLiked() {
        return photosLiked;
    }

    public void setPhotosLiked(Set<Photo> photosLiked) {
        this.photosLiked = photosLiked;
    }

    public void likePhoto(Photo photo){
        this.photosLiked.add(photo);
        photo.getUserLikes().add(this);
    }

    public void unlikePhoto(Photo photo){
        this.photosLiked.remove(photo);
        photo.getUserLikes().remove(this);
    }

    public void addAlbum(Album album){
        albums.add(album);
    }
    public void removeAlbum(Album album){
        albums.remove(album);
    }
}
