package com.example.jpa.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Entity
@Table(name = "comments")
public class Comment extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String text;

    /*
     *  @JoinColumn(name = "post_id", nullable = false, referencedColumnName="id")
     *  @JoinColumn will define a foreign key (name is post_id) in comments table which reference
     *  to primary key 'id' in posts table, referencedColumnName="id" is primary key of posts
     *  foreign key in comments table 'name' property of @joinColumn defined as 'post_id' this 
     *  column will be eventually created in comments table
     *  mysql> desc comments;
		+------------+------------+------+-----+---------+----------------+
		| Field      | Type       | Null | Key | Default | Extra          |
		+------------+------------+------+-----+---------+----------------+
		| id         | bigint(20) | NO   | PRI | NULL    | auto_increment |
		| created_at | datetime   | NO   |     | NULL    |                |
		| updated_at | datetime   | NO   |     | NULL    |                |
		| text       | longtext   | NO   |     | NULL    |                |
		| post_id    | bigint(20) | NO   | MUL | NULL    |                |
		+------------+------------+------+-----+---------+----------------+
		5 rows in set (0.00 sec)

     *    
     */
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false, referencedColumnName="id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("post_id")
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
