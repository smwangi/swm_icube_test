package org.icube.ioutracker.controllers;

import org.icube.ioutracker.models.IOU;
import org.icube.ioutracker.models.User;
import org.icube.ioutracker.payload.requests.IOURequest;
import org.icube.ioutracker.repositories.IOURepository;
import org.icube.ioutracker.repositories.UserRepository;
import org.icube.ioutracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/q4-iou-tracker")
@Validated
public class IOUController {

    @Autowired
    private IOURepository iouRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getAllIOU(){
        return ResponseEntity.ok(iouRepository.findAll());
    }

    @PostMapping("/iou")
    public ResponseEntity<?>create(@Valid @RequestBody IOURequest iouRequest){

        Optional<User> lender = userRepository.findById(iouRequest.getLenderId());
        if(!lender.isPresent())
            return new  ResponseEntity<>("Lender not found.", HttpStatus.BAD_REQUEST);

        Optional<User> borrower = userRepository.findById(iouRequest.getBorrowerId());
        if(!borrower.isPresent())
            return new  ResponseEntity<>("Borrower not found.", HttpStatus.BAD_REQUEST);


        IOU iou = new IOU();
        iou.setAmount(iouRequest.getAmount());
        iou.setBorrower(borrower.get());
        iou.setLender(lender.get());
        iouRepository.save(iou);

        return new  ResponseEntity<>(userService.findAll(),HttpStatus.ACCEPTED);
    }
}
