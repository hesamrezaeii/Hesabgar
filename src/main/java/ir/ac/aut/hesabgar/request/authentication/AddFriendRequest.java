package ir.ac.aut.hesabgar.request.authentication;

public class AddFriendRequest {
    private String userId;
    private String friendsAuth;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendsAuth() {
        return friendsAuth;
    }

    public void setFriendsAuth(String friendsAuth) {
        this.friendsAuth = friendsAuth;
    }
}
