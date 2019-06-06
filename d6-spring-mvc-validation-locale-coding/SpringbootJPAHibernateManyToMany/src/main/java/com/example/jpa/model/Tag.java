package com.example.jpa.model;
//https://www.callicoder.com/hibernate-spring-boot-jpa-many-to-many-mapping-example/
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
/*
 * We use @ManyToMany annotation to create a many-to-many relationship between two entities. 
 * In a bi-directional association, the @ManyToMany annotation is used on both the entities 
 * but only one entity can be the owner of the relationship.

The entity that specifies the @JoinTable is the owning side of the relationship and the entity 
that specifies the mappedBy attribute is the inverse side.

In the above example, Post entity is the owner of the relationship and Tag entity is the inverse side.
 */
/**
 * Created by rajeevkumarsingh on 23/11/17.
 */
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @NaturalId
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    public Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
