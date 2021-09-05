package ir.ac.aut.hesabgar.domain.document;

import com.mongodb.lang.NonNull;
import ir.ac.aut.hesabgar.domain.data.BankAccount;
import ir.ac.aut.hesabgar.domain.data.JoinedGroupInfo;
import ir.ac.aut.hesabgar.domain.data.UserReportStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = UserInfo.Name)
public class UserInfo {
    public final static String Name = "UserInfo";

    public final static String Id_Col = "_id";
    public final static String IsHesabgarAdmin_Col = "role";
    public final static String IsActive_Col = "active";
    public final static String UserName_Col = "userName";
    public final static String Password_Col = "password";
    public final static String Email_Col = "emailAddress";

    public final static String Name_Col = "name";
    public final static String LastName_Col = "lastName";
    public final static String TelephoneNumber_Col = "telephoneNumber";
    public final static String BankAccounts_Col = "bankAccount";
    public final static String ReportList_Col = "reportList";


    public final static String JoinedGroupList_Col = "joinedGroupList";

    public final static String OverallBalance_Col = "overallBalance";

    public final static String FriendsList_Col = "friendsList";

    public final static String CreationDate_Col = "creationDate";

    @Id
    private String id;

    @Field(IsHesabgarAdmin_Col)
    private boolean isAdmin;

    @Field(Password_Col)
    private boolean isActive;

    @Field(UserName_Col)
    @Indexed(unique = true)
    @NonNull
    private String userName;

    @Field(IsActive_Col)
    private String password;

    @Field(Email_Col)
    @Indexed(unique = true)
    @NonNull
    private String emailAddress;

    @Field(BankAccounts_Col)
    private BankAccount bankAccount;

    @Field(ReportList_Col)
    private UserReportStatus userReportStatus;

    @Field(Name_Col)
    private String name;

    @Field(LastName_Col)
    private String lastName;

    @Field(TelephoneNumber_Col)
    @Indexed(unique = true)
    @NonNull
    private String telephoneNumber;

    @Field(JoinedGroupList_Col)
    private List<JoinedGroupInfo> joinedGroupList;

    @Field(FriendsList_Col)
    private List<String> friendsList;

    @Field(OverallBalance_Col)
    private float overallBalance;

//    @Field(NewInvitedGroup_Col)
//    private List<GroupInfo> newInvitedGroup;

    public List<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<String> friendsList) {
        this.friendsList = friendsList;
    }

    @Field(CreationDate_Col)
    @CreatedDate
    private Date creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserReportStatus getUserReportStatus() {
        return userReportStatus;
    }

    public void setUserReportStatus(UserReportStatus userReportStatus) {
        this.userReportStatus = userReportStatus;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<JoinedGroupInfo> getJoinedGroupList() {
        return joinedGroupList;
    }

    public void setJoinedGroupList(List<JoinedGroupInfo> joinedGroupList) {
        this.joinedGroupList = joinedGroupList;
    }

    public float getOverallBalance() {
        return overallBalance;
    }

    public void setOverallBalance(float overallBalance) {
        this.overallBalance = overallBalance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
