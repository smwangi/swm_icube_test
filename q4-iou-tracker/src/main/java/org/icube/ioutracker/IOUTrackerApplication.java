package org.icube.ioutracker;

import org.icube.ioutracker.models.User;
import org.icube.ioutracker.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IOUTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IOUTrackerApplication.class,args);
    }

    /**
     * Creates initial user.
     * @param userService
     * @return
     */
    @Bean
    CommandLineRunner runner(UserService userService){
        return args -> {
            userService.save(new User("samwan"));
        };
    }
}
