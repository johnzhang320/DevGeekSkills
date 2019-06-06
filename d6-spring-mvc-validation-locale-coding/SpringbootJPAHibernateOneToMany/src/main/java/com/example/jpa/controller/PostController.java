package com.example.jpa.controller;
//https://github.com/callicoder/jpa-hibernate-tutorials
//https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Post;
import com.example.jpa.repository.CommentRepository;
import com.example.jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jpa.model.Comment;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @GetMapping("/postsDetail")
    public List<Post> getAllPostsDetail() {
        return postRepository.findAll().stream().map(p->{
        	List<Comment> comments = commentRepository.findCommentsByPostId(p.getId());
        	if (comments!=null && !comments.isEmpty()) {
        		p.setComments(comments);
        	}
        	return p;
        }).collect(Collectors.toList());
    }

    
    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
    	
    	  // CrudRepository would not implement findById: Optional<Post>, I implement it in PostRepository
          return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postRepository.save(post);    // postRepository.save(post) return saved entity;
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
    
   
}
