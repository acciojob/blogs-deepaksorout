package com.driver.models;


import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String Description;
    private String Dimensions;

    @ManyToOne
    @JoinColumn
    private Blog blog;

    public Image() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDimensions() {
        return Dimensions;
    }

    public void setDimensions(String dimensions) {
        Dimensions = dimensions;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
