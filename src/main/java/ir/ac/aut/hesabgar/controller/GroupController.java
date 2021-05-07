package ir.ac.aut.hesabgar.controller;

import ir.ac.aut.hesabgar.domain.document.GroupInfo;
import ir.ac.aut.hesabgar.domain.repo.GroupInfoRepo;
import ir.ac.aut.hesabgar.manager.GroupManager;
import ir.ac.aut.hesabgar.request.group.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupManager groupManager;
    @Autowired
    private GroupInfoRepo groupInfoRepo;


    @PostMapping("/createGroup")
    public GroupInfo createGroup(@RequestBody CreatingGroupRequest creatingGroupInfoRequest) {
        return groupManager.createGroup(creatingGroupInfoRequest);
    }

    @GetMapping("/getGroup/{groupId}")
    public GroupInfo getGroup(@PathVariable String groupId){
       return groupInfoRepo.getGroupInfoById(groupId);
    }

    @PostMapping("/inviteToGroup")
    public GroupInfo inviteGroup(@RequestBody InvitingToGroupRequest invitingToGroupInfoRequest) {
        return groupManager.inviteToGroup(invitingToGroupInfoRequest);
    }

    @PostMapping("/deleteGroup")
    public String deleteGroup(@RequestBody DeletingGroupRequest deletingGroupRequest) {
        return groupManager.deletingGroup(deletingGroupRequest);
    }

    @PostMapping("/makingNewInvoiceAdmin")
    public GroupInfo makingMemberInvoiceAdmin(@RequestBody MakingMemberInvoiceAdminRequest makingMemberInvoiceAdminRequest) {
        return groupManager.makingMemberInvoiceAdmin(makingMemberInvoiceAdminRequest, true);
    }

    @PostMapping("/dismissalInvoiceAdmin")
    public GroupInfo DismissalMemberFromInvoiceAdmin(@RequestBody MakingMemberInvoiceAdminRequest dismissalMemberInvoiceAdminRequest) {
        return groupManager.makingMemberInvoiceAdmin(dismissalMemberInvoiceAdminRequest, false);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        groupInfoRepo.deleteAll();
    }

    @PostMapping("/deletingGroupMember")
    public String deletingGroupMember(@RequestBody DeletingGroupMemberRequest deletingGroupMemberRequest) {
        return groupManager.deletingGroupMember(deletingGroupMemberRequest);
    }

    @PostMapping("/addInvoice")
    public GroupInfo getPaymentTerm(@RequestBody AddingInvoiceRequest addingInvoiceRequest) {
        return groupManager.addingInvoice(addingInvoiceRequest);
    }


}
