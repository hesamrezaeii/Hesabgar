package ir.ac.aut.hesabgar.controller;

import ir.ac.aut.hesabgar.domain.document.UserInfo;
import ir.ac.aut.hesabgar.domain.repo.UserInfoRepo;
import ir.ac.aut.hesabgar.manager.AuthenticationManager;
import ir.ac.aut.hesabgar.request.authentication.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authentication")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterInfoRequest registerInfoRequest) {
        String status = authenticationManager.register(registerInfoRequest);
        if(status.equals("ok")){
            return ResponseEntity.status(HttpStatus.OK).body(null);
         } return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginInfoRequest loginInfoRequest) {
        UserInfo userInfo = authenticationManager.login(loginInfoRequest);
        if (userInfo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/changeProfile")
    public ResponseEntity<Object> changeProfileInfo(@RequestBody ChangeProfileRequest changeProfileRequest) {
        UserInfo user = userInfoRepo.getUserInfoById(changeProfileRequest.getUserId());
        if (!user.isActive()) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(null);
        } else {
            UserInfo userInfo = authenticationManager.changeProfile(changeProfileRequest);
            if (userInfo != null) {
                return ResponseEntity.status(HttpStatus.OK).body(userInfo);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/addFriend")
    public ResponseEntity<Object> addFriend(@RequestBody AddFriendRequest addFriendRequest) {
        UserInfo user = userInfoRepo.getUserInfoById(addFriendRequest.getUserId());
        if (!user.isActive()) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(null);
        }else {
            UserInfo userInfo = authenticationManager.addFriend(addFriendRequest);
            if (userInfo != null) {
                return ResponseEntity.status(HttpStatus.OK).body(userInfo);

            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/addBankAccount")
    public ResponseEntity<Object> addingBankAccount(@RequestBody AddingBankAccountRequest addingBankAccountRequest) {
        UserInfo user = userInfoRepo.getUserInfoById(addingBankAccountRequest.getUserId());
        if (!user.isActive()) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(null);
        } else {
            UserInfo userInfo = authenticationManager.addingBankAccount(addingBankAccountRequest);

            if (userInfo != null) {
                return ResponseEntity.status(HttpStatus.OK).body(userInfo);

            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/deleteBankAccount")
    public ResponseEntity<Object> deleteBankAccount(@RequestBody DeletingBanckAccountRequest deletingBanckAccountRequest) {
        UserInfo user = userInfoRepo.getUserInfoById(deletingBanckAccountRequest.getUserId());
        if (!user.isActive()) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(null);
        } else {
            UserInfo userInfo = authenticationManager.deleteBankAccount(deletingBanckAccountRequest);

            if (userInfo != null) {
                return ResponseEntity.status(HttpStatus.OK).body(userInfo);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/getUser")
    public ResponseEntity<Object> getUser(@RequestBody GetUserRequest getUserRequest) {
       UserInfo userInfo =  userInfoRepo.getUserInfoById(getUserRequest.getUserId());
       if(userInfo != null){
           return ResponseEntity.status(HttpStatus.OK).body(userInfo);

       } return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    }

    @GetMapping("/getAll")
    public List<UserInfo> getAll() {
        return userInfoRepo.findAll();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        userInfoRepo.deleteAll();
    }

    @PostMapping("/ban")
    public void ban(@RequestBody UserBanRequest userBanRequest) {
      UserInfo adminInfo = userInfoRepo.getUserInfoById(userBanRequest.getAdminId());
      if(adminInfo.isAdmin()){
          UserInfo userInfo = userInfoRepo.getUserInfoById(userBanRequest.getUserId());
          userInfo.setActive(userBanRequest.isBan());
          userInfoRepo.save(userInfo);
      }
    }

}
