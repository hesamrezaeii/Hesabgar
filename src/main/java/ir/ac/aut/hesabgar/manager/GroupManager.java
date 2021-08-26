package ir.ac.aut.hesabgar.manager;

import ir.ac.aut.hesabgar.domain.data.events.AddingInvoiceEvent;
import ir.ac.aut.hesabgar.domain.data.GroupMember;
import ir.ac.aut.hesabgar.domain.data.JoinedGroupInfo;
import ir.ac.aut.hesabgar.domain.data.events.InvoiceAdminInvAndDelEvent;
import ir.ac.aut.hesabgar.domain.data.events.MemberInvAndDelEvent;
import ir.ac.aut.hesabgar.domain.data.events.PayingInvoiceEvent;
import ir.ac.aut.hesabgar.domain.document.GroupInfo;
import ir.ac.aut.hesabgar.domain.document.UserInfo;
import ir.ac.aut.hesabgar.domain.repo.GroupInfoRepo;
import ir.ac.aut.hesabgar.domain.repo.UserInfoRepo;
import ir.ac.aut.hesabgar.helper.InvoiceHelper;
import ir.ac.aut.hesabgar.request.group.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GroupManager {
    @Autowired
    private GroupInfoRepo groupInfoRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private InvoiceHelper invoiceHelper;

    public GroupInfo createGroup(CreatingGroupRequest creatingGroupInfoRequest) {
        UserInfo adminInfo = userInfoRepo.getUserInfoById(creatingGroupInfoRequest.getUserId());

        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setAdmin(adminInfo.getId());
        groupInfo.setCreationDate(new Date());
        groupInfo.setGroupName(creatingGroupInfoRequest.getGroupName());

        Map<String, Float> groupBalance = new HashMap<String, Float>();
        groupBalance.put(adminInfo.getId(), (float) 0);
        groupInfo.setGroupBalance(groupBalance);

        List<GroupMember> groupMembers = new ArrayList<>();
        GroupMember groupMember = new GroupMember();
        groupMember.setInvoiceAdmin(true);
        groupMember.setUserId(adminInfo.getId());
        groupMembers.add(groupMember);
        groupInfo.setMembers(groupMembers);

        List<AddingInvoiceEvent> addingInvoiceEvents = new ArrayList<>();
        groupInfo.setAddingInvoiceEvents(addingInvoiceEvents);

        List<PayingInvoiceEvent> payingInvoiceEvents = new ArrayList<>();
        groupInfo.setPayingInvoiceEvents(payingInvoiceEvents);

        groupInfo = groupInfoRepo.save(groupInfo);

        List<JoinedGroupInfo> adminJoinedGroupInfos = adminInfo.getJoinedGroupList();
        JoinedGroupInfo joinedGroupInfo = new JoinedGroupInfo(groupInfo.getId(), groupInfo.getGroupName(), true, true);
        adminJoinedGroupInfos.add(joinedGroupInfo);
        adminInfo.setJoinedGroupList(adminJoinedGroupInfos);

        userInfoRepo.save(adminInfo);

        return groupInfo;

    }

    public GroupInfo inviteToGroup(InvitingToGroupRequest invitingToGroupInfoRequest) {
        GroupInfo groupInfo = groupInfoRepo.getGroupInfoById(invitingToGroupInfoRequest.getGroupId());
        for (GroupMember groupMember : groupInfo.getMembers()) {
            if (groupMember.getUserId().equals(invitingToGroupInfoRequest.getNewUserId())) {
                return null;
            }
        }
        if (groupInfo.getAdmin().equals(invitingToGroupInfoRequest.getAdminId())) {

            UserInfo newInvUser = userInfoRepo.getUserInfoById(invitingToGroupInfoRequest.getNewUserId());

            List<GroupMember> groupMembers = groupInfo.getMembers();
            GroupMember groupMember = new GroupMember();
            groupMember.setInvoiceAdmin(false);
            groupMember.setUserId(newInvUser.getId());
            groupMembers.add(groupMember);
            groupInfo.setMembers(groupMembers);

            List<MemberInvAndDelEvent> memberInvAndDelEvents = groupInfo.getMemberInvAndDelEvents();
            if (memberInvAndDelEvents == null) {
                memberInvAndDelEvents = new ArrayList<>();
            }
            memberInvAndDelEvents.add(new MemberInvAndDelEvent(newInvUser.getUserName(), true, new Date()));
            groupInfo.setMemberInvAndDelEvents(memberInvAndDelEvents);

            Map<String, Float> groupBalance = groupInfo.getGroupBalance();
            groupBalance.put(newInvUser.getId(),  (float) 0);
            groupInfo.setGroupBalance(groupBalance);

            List<JoinedGroupInfo> newUserJoinedGroupInfos = newInvUser.getJoinedGroupList();
            JoinedGroupInfo joinedGroupInfo = new JoinedGroupInfo(groupInfo.getId(), groupInfo.getGroupName(), false, false);
            newUserJoinedGroupInfos.add(joinedGroupInfo);
            newInvUser.setJoinedGroupList(newUserJoinedGroupInfos);

            userInfoRepo.save(newInvUser);

            return groupInfoRepo.save(groupInfo);

        }
        return null;
    }

    public String deletingGroup(DeletingGroupRequest deletingGroupRequest) {
        GroupInfo groupInfo = groupInfoRepo.getGroupInfoById(deletingGroupRequest.getGroupId());
        UserInfo adminUser = userInfoRepo.getUserInfoById(deletingGroupRequest.getAdminId());
        List<String> groupUsers = new ArrayList<>();
        boolean deletable = false;
        if (adminUser.getId().equals(groupInfo.getAdmin())) {
            deletable = true;
            Map<String, Float> groupBalance = groupInfo.getGroupBalance();
            for (String member : groupBalance.keySet()) {
                groupUsers.add(member);
                if (groupBalance.get(member) != 0) {
                    deletable = false;
                }
            }
        }
        if (deletable) {
            for (String mem : groupUsers) {
                UserInfo userInfo = userInfoRepo.getUserInfoByUserName(mem);
                List<JoinedGroupInfo> newJoinedGroup = new ArrayList<>();
                List<JoinedGroupInfo> userJoinedGroupInfos = userInfo.getJoinedGroupList();
                for (JoinedGroupInfo joinedGroupInfo : userJoinedGroupInfos) {
                    if (!joinedGroupInfo.getGroupId().equals(groupInfo.getId())) {
                        newJoinedGroup.add(joinedGroupInfo);
                    }
                }
                userInfo.setJoinedGroupList(newJoinedGroup);
                userInfoRepo.save(userInfo);
            }
            groupInfoRepo.delete(groupInfo);
            return "SUCCESSFULLY DELETED";
        }
        return "CAN'T BE DONE";
    }

    public GroupInfo makingMemberInvoiceAdmin(MakingMemberInvoiceAdminRequest makingMemberInvoiceAdminRequest, Boolean toBeInvoiceAdmin) {
        GroupInfo groupInfo = groupInfoRepo.getGroupInfoById(makingMemberInvoiceAdminRequest.getGroupId());
        for (GroupMember groupMember : groupInfo.getMembers()) {
            if (groupMember.getUserId().equals(makingMemberInvoiceAdminRequest.getUserId())) {
                if (toBeInvoiceAdmin == groupMember.isInvoiceAdmin()) {
                    return null;
                }
            }
        }
        if (groupInfo.getAdmin().equals(makingMemberInvoiceAdminRequest.getAdminId())) {

            UserInfo newInvoiceAdmin = userInfoRepo.getUserInfoById(makingMemberInvoiceAdminRequest.getUserId());

            List<GroupMember> groupMembers = groupInfo.getMembers();
            List<GroupMember> newGroupMembers = new ArrayList<>();
            for (GroupMember groupMember : groupMembers) {
                if (groupMember.getUserId().equals(newInvoiceAdmin.getId())) {
                    groupMember.setInvoiceAdmin(toBeInvoiceAdmin);
                }
                newGroupMembers.add(groupMember);
            }
            groupInfo.setMembers(newGroupMembers);

            List<JoinedGroupInfo> newJoinedGroup = new ArrayList<>();
            List<JoinedGroupInfo> userJoinedGroupInfos = newInvoiceAdmin.getJoinedGroupList();
            for (JoinedGroupInfo joinedGroupInfo : userJoinedGroupInfos) {
                if (joinedGroupInfo.getGroupId().equals(groupInfo.getId())) {
                    joinedGroupInfo.setInvoiceAdmin(toBeInvoiceAdmin);
                }
                newJoinedGroup.add(joinedGroupInfo);
            }

            newInvoiceAdmin.setJoinedGroupList(newJoinedGroup);
            userInfoRepo.save(newInvoiceAdmin);

            List<InvoiceAdminInvAndDelEvent> invoiceAdminInvAndDelEvents = groupInfo.getInvoiceAdminInvAndDelEvents();
            if (invoiceAdminInvAndDelEvents == null) {
                invoiceAdminInvAndDelEvents = new ArrayList<>();
            }
            invoiceAdminInvAndDelEvents.add(new InvoiceAdminInvAndDelEvent(newInvoiceAdmin.getUserName(), toBeInvoiceAdmin, new Date()));
            groupInfo.setInvoiceAdminInvAndDelEvents(invoiceAdminInvAndDelEvents);
            return groupInfoRepo.save(groupInfo);
        }

        return null;

    }

    public String deletingGroupMember(DeletingGroupMemberRequest deletingGroupMemberRequest) {
        GroupInfo groupInfo = groupInfoRepo.getGroupInfoById(deletingGroupMemberRequest.getGroupId());
        UserInfo adminUser = userInfoRepo.getUserInfoById(deletingGroupMemberRequest.getAdminId());
        UserInfo toBeDeletedUser = userInfoRepo.getUserInfoById(deletingGroupMemberRequest.getUserId());
        if (adminUser.getId().equals(groupInfo.getAdmin())) {
            Map<String, Float> groupBalance = groupInfo.getGroupBalance();
            Map<String, Float> newGroupBalance = new HashMap<>();
            if (groupBalance.get(toBeDeletedUser.getId()).equals(0)) {
                for (String member : groupBalance.keySet()) {
                    if (!member.equals(toBeDeletedUser.getUserName())) {
                        newGroupBalance.put(member, groupBalance.get(member));
                    }
                }
                groupInfo.setGroupBalance(newGroupBalance);
            }
            List<JoinedGroupInfo> newJoinedGroup = new ArrayList<>();
            List<JoinedGroupInfo> userJoinedGroupInfos = toBeDeletedUser.getJoinedGroupList();
            for (JoinedGroupInfo joinedGroupInfo : userJoinedGroupInfos) {
                if (!joinedGroupInfo.getGroupId().equals(groupInfo.getId())) {
                    newJoinedGroup.add(joinedGroupInfo);
                }
            }
            toBeDeletedUser.setJoinedGroupList(newJoinedGroup);
            userInfoRepo.save(toBeDeletedUser);

            List<GroupMember> groupMembers = groupInfo.getMembers();
            List<GroupMember> newGroupMembersList = new ArrayList<>();
            for (GroupMember groupMember : groupMembers) {
                if (!groupMember.getUserId().equals(toBeDeletedUser.getId())) {
                    newGroupMembersList.add(groupMember);
                }
            }
            groupInfo.setMembers(newGroupMembersList);

            List<MemberInvAndDelEvent> memberInvAndDelEvents = groupInfo.getMemberInvAndDelEvents();
            if (memberInvAndDelEvents == null) {
                memberInvAndDelEvents = new ArrayList<>();
            }
            memberInvAndDelEvents.add(new MemberInvAndDelEvent(toBeDeletedUser.getUserName(), false, new Date()));
            groupInfo.setMemberInvAndDelEvents(memberInvAndDelEvents);
            groupInfoRepo.save(groupInfo);
            return "SUCCESSFULLY DELETED";
        }
        return "CAN'T BE DONE";
    }

    public GroupInfo addingInvoice(AddingInvoiceRequest addingInvoiceRequest) {
        //finding payment term and add it to groupMembers
        boolean allowed = false;
        GroupInfo groupInfo = groupInfoRepo.getGroupInfoById(addingInvoiceRequest.getGroupId());
        for (GroupMember groupMember : groupInfo.getMembers()) {
            if (groupMember.getUserId().equals(addingInvoiceRequest.getUserId()) && groupMember.isInvoiceAdmin()) {
                allowed = true;
            }
        }
        if (allowed) {
            //making a invoiceEvents and add it to group
            groupInfo = invoiceHelper.makingNewInvoiceEvent(groupInfo, addingInvoiceRequest);
            //making everyones balance right
            invoiceHelper.updateEventMembersBalance(addingInvoiceRequest);
            //making paymentTerms
            groupInfo = invoiceHelper.findPaymentTerm(groupInfo, addingInvoiceRequest.getGroupShare());
//            groupInfo = invoiceHelper.updateGroupBalance(groupInfo, addingInvoiceRequest);
            return groupInfoRepo.save(groupInfo);
        }
        return null;
    }
}
