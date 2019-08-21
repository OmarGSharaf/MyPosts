package de.pharos.myPosts;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyPostsApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MyPostsApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}