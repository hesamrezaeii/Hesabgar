package ir.ac.aut.hesabgar.domain.document;

import ir.ac.aut.hesabgar.domain.data.events.AddingInvoiceEvent;
import ir.ac.aut.hesabgar.domain.data.GroupMember;
import ir.ac.aut.hesabgar.domain.data.events.InvoiceAdminInvAndDelEvent;
import ir.ac.aut.hesabgar.domain.data.events.MemberInvAndDelEvent;
import ir.ac.aut.hesabgar.domain.data.events.PayingInvoiceEvent;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = GroupInfo.Name)
public class GroupInfo {
    public final static String Name = "GroupInfo";

    public final static String Id_Col = "_id";
    public final static String GroupName_Col = "groupName";
    public final static String Members_Col = "members";
    public final static String Admin_Col = "admin";
    public final static String AddingInvoiceEvents_Col = "addingInvoiceEvents";
    public final static String PayingInvoiceEvents_Col = "payingInvoiceEvents";
    public final static String AddingMemberEvents_Col = "addingMemberEvents";
    public final static String InvoiceAdminInvAndDelEvent_Col = "invoiceAdminInvAndDelEvents";
    public final static String GroupBalance_Col = "groupBalance";

    public final static String CreationDate_Col = "creationDate";

    @Id
    private String id;

    @Field(GroupName_Col)
    private String groupName;

    @Field(Members_Col)
    private List<GroupMember> members;

    @Field(Admin_Col)
    private String admin;

    @Field(AddingInvoiceEvents_Col)
    private List<AddingInvoiceEvent> addingInvoiceEvents;

    @Field(PayingInvoiceEvents_Col)
    private List<PayingInvoiceEvent> payingInvoiceEvents;

    @Field(AddingMemberEvents_Col)
    private List<MemberInvAndDelEvent> memberInvAndDelEvents;

    @Field(InvoiceAdminInvAndDelEvent_Col)
    private List<InvoiceAdminInvAndDelEvent> invoiceAdminInvAndDelEvents;

    @Field(GroupBalance_Col)
    private Map<String, Float> groupBalance;

    @Field(CreationDate_Col)
    @CreatedDate
    private Date creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public List<AddingInvoiceEvent> getAddingInvoiceEvents() {
        return addingInvoiceEvents;
    }

    public void setAddingInvoiceEvents(List<AddingInvoiceEvent> addingInvoiceEvents) {
        this.addingInvoiceEvents = addingInvoiceEvents;
    }

    public List<PayingInvoiceEvent> getPayingInvoiceEvents() {
        return payingInvoiceEvents;
    }

    public void setPayingInvoiceEvents(List<PayingInvoiceEvent> payingInvoiceEvents) {
        this.payingInvoiceEvents = payingInvoiceEvents;
    }

    public Map<String, Float> getGroupBalance() {
        return groupBalance;
    }

    public void setGroupBalance(Map<String, Float> groupBalance) {
        this.groupBalance = groupBalance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<MemberInvAndDelEvent> getMemberInvAndDelEvents() {
        return memberInvAndDelEvents;
    }

    public void setMemberInvAndDelEvents(List<MemberInvAndDelEvent> memberInvAndDelEvents) {
        this.memberInvAndDelEvents = memberInvAndDelEvents;
    }

    public List<InvoiceAdminInvAndDelEvent> getInvoiceAdminInvAndDelEvents() {
        return invoiceAdminInvAndDelEvents;
    }

    public void setInvoiceAdminInvAndDelEvents(List<InvoiceAdminInvAndDelEvent> invoiceAdminInvAndDelEvents) {
        this.invoiceAdminInvAndDelEvents = invoiceAdminInvAndDelEvents;
    }
}
