package ir.ac.aut.hesabgar.domain.data.events;

import java.util.Date;

public class MemberInvAndDelEvent {
    private String userName;
    private boolean added;
    private Date creationDate;

    public MemberInvAndDelEvent(String userName, boolean added, Date creationDate) {
        this.userName = userName;
        this.added = added;
        this.creationDate = creationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
