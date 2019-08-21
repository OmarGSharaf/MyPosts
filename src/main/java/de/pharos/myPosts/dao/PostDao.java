package de.pharos.myPosts.dao;

import de.pharos.myPosts.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
    Page<Post> findByContentIgnoreCaseContainingAndStatus(String content, String status, Pageable pageable);
}