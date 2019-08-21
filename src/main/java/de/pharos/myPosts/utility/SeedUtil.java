package de.pharos.myPosts.utility;

import de.pharos.myPosts.dao.UserDao;
import de.pharos.myPosts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class SeedUtil implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User user1 = new User();
        user1.setName("test");
        user1.setPassword(passwordEncoder.encode("Ph@ros-90F"));
        user1.setEmail("test@pharos.de");
        userDao.save(user1);
    }
}