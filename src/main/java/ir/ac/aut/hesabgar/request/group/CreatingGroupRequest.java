package ir.ac.aut.hesabgar.request.group;

public class CreatingGroupRequest {
    private String userId;
    private String groupName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
