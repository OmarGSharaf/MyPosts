package de.pharos.myPosts.controller;

import de.pharos.myPosts.dto.PostDto;
import de.pharos.myPosts.model.Post;
import de.pharos.myPosts.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Api(tags = "Posts", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting posts.")
@RequestMapping("/v1/posts")
public class PostsController {

    @Autowired
    private PostService postService;

    @GetMapping("/search")
    @ApiOperation("Searches for all public posts that contain a text.")
    public ResponseEntity search(String text, Pageable pageable) {
        Page<Post> posts = postService.search(text, pageable);
        return posts.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.ok(posts);
    }

    @GetMapping
    @ApiOperation("Returns list of all posts in the system given a filter.")
    public ResponseEntity index(Post post, Pageable pageable) {
        Page<Post> posts = postService.find(post, pageable);
        return posts.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns a specific post by its identifier.")
    public ResponseEntity show(@PathVariable("id") Integer id) {
        Optional<Post> post = postService.findById(id);
        return post.isPresent()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(post);
    }

    @PostMapping
    @ApiOperation("Creates a new post or updates existing post if matching identifier exists.")
    public ResponseEntity create(@RequestHeader("Authorization") @ApiParam(hidden = true) String token,
                                 @RequestBody @Valid PostDto postDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.save(postDto, token));
    }

    @PutMapping("/{id}")
    @ApiOperation("Updates an existing post.")
    public ResponseEntity update(@RequestHeader("Authorization") @ApiParam(hidden = true) String token,
                                 @PathVariable("id") Integer id,
                                 @RequestBody @Valid PostDto postDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.update(id, postDto, token));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes a posts from the system.")
    public void delete(@PathVariable("id") Integer id) {
        postService.deleteById(id);
    }
}