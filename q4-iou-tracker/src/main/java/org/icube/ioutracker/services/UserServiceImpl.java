package org.icube.ioutracker.services;

import org.icube.ioutracker.models.IOU;
import org.icube.ioutracker.models.User;
import org.icube.ioutracker.payload.response.UserResponse;
import org.icube.ioutracker.repositories.IOURepository;
import org.icube.ioutracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Configurable
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IOURepository iouRepository;

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserResponse> findAll() {

        List<UserResponse> usrResponseList = userRepository.findAll().stream().sorted(Comparator.comparing(i -> i.getName())).map(x -> {

            Map<String, BigDecimal> owed_by = x.getOwed_by().stream().collect(Collectors.toMap(IOU::getBorrower,IOU::getAmount, BigDecimal::add));
            Map<String, BigDecimal> owes = x.getOwes().stream().collect(Collectors.toMap(IOU::getLender,IOU::getAmount,BigDecimal::add));
            BigDecimal creditorsSum = x.getOwed_by().stream().map(IOU::getAmount).reduce(BigDecimal.valueOf(0),BigDecimal::add);
            BigDecimal debtSum = x.getOwes().stream().map(IOU::getAmount).reduce(BigDecimal.valueOf(0),BigDecimal::add);

           UserResponse userResponse = new UserResponse();
           userResponse.setName(x.getName());
           userResponse.setOwed_by(owed_by);
           userResponse.setOwes(owes);
           userResponse.setBalance(creditorsSum.subtract(debtSum));

           return userResponse;
        }).collect(Collectors.toList());

        return usrResponseList;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public boolean deleteById(long id) {

        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
