package ir.ac.aut.hesabgar.controller;

import ir.ac.aut.hesabgar.domain.document.UserInfo;
import ir.ac.aut.hesabgar.domain.repo.UserInfoRepo;
import ir.ac.aut.hesabgar.manager.AuthenticationManager;
import ir.ac.aut.hesabgar.request.authentication.AddFriendRequest;
import ir.ac.aut.hesabgar.request.authentication.ChangeProfileRequest;
import ir.ac.aut.hesabgar.request.authentication.LoginInfoRequest;
import ir.ac.aut.hesabgar.request.authentication.RegisterInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @PostMapping("/register")
    public String register(@RequestBody RegisterInfoRequest registerInfoRequest) {
        return authenticationManager.register(registerInfoRequest);
    }

    @PostMapping("/login")
    public UserInfo login(@RequestBody LoginInfoRequest loginInfoRequest) {
        return authenticationManager.login(loginInfoRequest);
    }

    @PostMapping("/changeProfile")
    public UserInfo changeProfileInfo(@RequestBody ChangeProfileRequest changeProfileRequest) {
        return authenticationManager.changeProfile(changeProfileRequest);
    }

    @PostMapping("/addFriend")
    public UserInfo addFriend(@RequestBody AddFriendRequest addFriendRequest) {
        return authenticationManager.addFriend(addFriendRequest);
    }


    @GetMapping("/getAll")
    public List<UserInfo> getAll() {
        return userInfoRepo.findAll();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        userInfoRepo.deleteAll();
    }

}
