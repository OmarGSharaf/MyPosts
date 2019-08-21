package de.pharos.myPosts.service;

import de.pharos.myPosts.dao.PostDao;
import de.pharos.myPosts.dto.PostDto;
import de.pharos.myPosts.exception.PostException;
import de.pharos.myPosts.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    /**
     * Search for public posts that contain text.
     *
     * @param text     String to be searched for.
     * @param pageable used for pagination and sorting.
     * @return page of users.
     */
    public Page<Post> search(String text, Pageable pageable) {
        return postDao.findByContentIgnoreCaseContainingAndStatus(text, "PUBLIC", pageable);
    }

    /**
     * Find posts given a filter. A filter is created by creating an Example of Post.
     *
     * @param post     contain the example values.
     * @param pageable used for pagination and sorting.
     * @return page of users.
     */
    public Page<Post> find(Post post, Pageable pageable) {
        return postDao.findAll(Example.of(post), pageable);
    }

    /**
     * Finds a Post by it's ID.
     *
     * @param id Post id.
     * @return Optional of type post.
     */
    public Optional<Post> findById(Integer id) {
        return postDao.findById(id);
    }

    /**
     * Saves a Post. Post DTO is mapped to Post object, and the author of the post is fetched by his ID, and set in
     * the Post object before saving.
     *
     * @param postDto the post details without the user.
     * @param token   JSON Web Token (JWT).
     * @return saved post.
     */
    public Post save(PostDto postDto, String token) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(userService.findByToken(token));
        return postDao.save(post);
    }

    /**
     * Update a Post. Checks if the post exists, and then performs the update operation by mapping the Post DTO to the
     * Post object, if not an exception is thrown.
     *
     * @param postDto the post details without the user.
     * @param token   JSON Web Token (JWT).
     * @return updated post.
     */
    public Post update(Integer id, PostDto postDto, String token) {
        if (!postDao.existsById(id)) throw new PostException("POST_NOT_FOUND");
        Post post = modelMapper.map(postDto, Post.class);
        post.setId(id);
        post.setUser(userService.findByToken(token));
        return postDao.save(post);
    }

    /**
     * Delete a post by his ID.
     *
     * @param id Post id.
     */
    public void deleteById(Integer id) {
        postDao.deleteById(id);
    }
}