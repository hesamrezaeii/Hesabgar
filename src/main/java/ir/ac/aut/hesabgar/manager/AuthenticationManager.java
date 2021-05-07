package ir.ac.aut.hesabgar.manager;

import ir.ac.aut.hesabgar.domain.data.BankAccount;
import ir.ac.aut.hesabgar.domain.data.JoinedGroupInfo;
import ir.ac.aut.hesabgar.domain.document.UserInfo;
import ir.ac.aut.hesabgar.domain.repo.UserInfoRepo;
import ir.ac.aut.hesabgar.request.authentication.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuthenticationManager {
    @Autowired
    private UserInfoRepo userInfoRepo;

    public String register(RegisterInfoRequest registerInfoRequest) {
        UserInfo userInfo = userInfoRepo.getUserInfoByUserName(registerInfoRequest.getUserName());
        if (userInfo == null) {
            userInfo = userInfoRepo.getUserInfoByEmailAddress(registerInfoRequest.getEmailAddress());
            if (userInfo == null) {
                userInfo = userInfoRepo.getUserInfoByTelephoneNumber(registerInfoRequest.getTelephoneNumber());
                if (userInfo == null) {
                    userInfo = new UserInfo();

                    userInfo.setAdmin(false);
                    userInfo.setName(registerInfoRequest.getName());
                    userInfo.setLastName(registerInfoRequest.getLastName());
                    userInfo.setEmailAddress(registerInfoRequest.getEmailAddress());
                    userInfo.setPassword(registerInfoRequest.getPassword());
                    userInfo.setUserName(registerInfoRequest.getUserName());
                    userInfo.setTelephoneNumber(registerInfoRequest.getTelephoneNumber());
                    List<JoinedGroupInfo> joinedGroupInfos = new ArrayList<>();
                    userInfo.setJoinedGroupList(joinedGroupInfos);
                    userInfo.setOverallBalance(0);
                    userInfo.setCreationDate(new Date());

                    userInfoRepo.save(userInfo);

                    return "ok";


                }
            } else return "420";
        } else {
            return "421";
        }
        return "422";
    }

    public UserInfo login(LoginInfoRequest loginInfoRequest) {
        UserInfo userInfo = userInfoRepo.getUserInfoByUserName(loginInfoRequest.getUserName());
        if (userInfo == null) {
            userInfo = userInfoRepo.getUserInfoByEmailAddress(loginInfoRequest.getUserName());
            if (userInfo == null) {
                userInfo = userInfoRepo.getUserInfoByTelephoneNumber(loginInfoRequest.getUserName());
                if (userInfo == null) {
                    return null;
                } else {
                    if (userInfo.getPassword().equals(loginInfoRequest.getPassword())) {
                        return userInfo;
                    }
                }
            } else {
                if (userInfo.getPassword().equals(loginInfoRequest.getPassword())) {
                    return userInfo;
                }
            }
        } else {
            if (userInfo.getPassword().equals(loginInfoRequest.getPassword())) {
                return userInfo;
            }
        }
        return null;
    }

    public UserInfo changeProfile(ChangeProfileRequest changeProfileRequest) {
        UserInfo userInfo = userInfoRepo.getUserInfoById(changeProfileRequest.getUserId());
        if (!changeProfileRequest.getName().equals("")) {
            userInfo.setName(changeProfileRequest.getName());
        }
        if (!changeProfileRequest.getLastName().equals("")) {
            userInfo.setLastName(changeProfileRequest.getLastName());
        }
        if (!changeProfileRequest.getPassword().equals("")) {
            userInfo.setPassword(changeProfileRequest.getPassword());
        }
        if (!changeProfileRequest.getTelephoneNumber().equals("")) {
            userInfo.setTelephoneNumber(changeProfileRequest.getTelephoneNumber());
        }
        return userInfoRepo.save(userInfo);
    }

    public UserInfo addFriend(AddFriendRequest addFriendRequest) {
        UserInfo userInfo = userInfoRepo.getUserInfoById(addFriendRequest.getUserId());
        List<String> friends = userInfo.getFriendsList();
        if (friends == null) {
            friends = new ArrayList<>();
        }
        UserInfo newFriend = userInfoRepo.getUserInfoByUserName(addFriendRequest.getFriendsAuth());
        if (newFriend == null) {
            newFriend = userInfoRepo.getUserInfoByEmailAddress(addFriendRequest.getFriendsAuth());
            if (newFriend == null) {
                newFriend = userInfoRepo.getUserInfoByTelephoneNumber(addFriendRequest.getFriendsAuth());
                if (newFriend == null) {
                    return null;
                } else {
                    List<String> newFriendFriendsList = newFriend.getFriendsList();
                    if (newFriendFriendsList == null) {
                        newFriendFriendsList = new ArrayList<>();
                    }
                    newFriendFriendsList.add(userInfo.getId());
                    newFriend.setFriendsList(newFriendFriendsList);
                    userInfoRepo.save(newFriend);

                    friends.add(newFriend.getId());
                    userInfo.setFriendsList(friends);
                    return userInfoRepo.save(userInfo);
                }
            } else {

                List<String> newFriendFriendsList = newFriend.getFriendsList();
                if (newFriendFriendsList == null) {
                    newFriendFriendsList = new ArrayList<>();
                }
                newFriendFriendsList.add(userInfo.getId());
                newFriend.setFriendsList(newFriendFriendsList);
                userInfoRepo.save(newFriend);

                friends.add(newFriend.getId());
                userInfo.setFriendsList(friends);
                return userInfoRepo.save(userInfo);
            }
        } else {

            List<String> newFriendFriendsList = newFriend.getFriendsList();
            if (newFriendFriendsList == null) {
                newFriendFriendsList = new ArrayList<>();
            }
            newFriendFriendsList.add(userInfo.getId());
            newFriend.setFriendsList(newFriendFriendsList);
            userInfoRepo.save(newFriend);


            friends.add(newFriend.getId());
            userInfo.setFriendsList(friends);
            return userInfoRepo.save(userInfo);
        }
    }

    public UserInfo addingBankAccount(AddingBankAccountRequest addingBankAccountRequest) {
        UserInfo userInfo = userInfoRepo.getUserInfoById(addingBankAccountRequest.getUserId());
        List<BankAccount> bankAccounts = userInfo.getBankAccounts();
        if (bankAccounts == null) {
            bankAccounts = new ArrayList<>();
        }
        bankAccounts.add(new BankAccount(addingBankAccountRequest.getBankName(), addingBankAccountRequest.getBankAccountNumber(), addingBankAccountRequest.getAtmCardNumber()));
        userInfo.setBankAccounts(bankAccounts);

        return userInfoRepo.save(userInfo);
    }
}
