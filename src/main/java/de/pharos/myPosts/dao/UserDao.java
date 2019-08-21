package de.pharos.myPosts.dao;

import de.pharos.myPosts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}