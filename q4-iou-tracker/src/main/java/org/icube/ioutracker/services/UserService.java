package org.icube.ioutracker.services;

import org.icube.ioutracker.models.User;
import org.icube.ioutracker.payload.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    Optional<User> findById(long id);
    List<UserResponse> findAll();
    User save(User user);
    Optional<User> findByName(String name);
    boolean existsByName(String name);
    boolean deleteById(long id);
}
