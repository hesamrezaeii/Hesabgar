package ir.ac.aut.hesabgar.controller;

import ir.ac.aut.hesabgar.domain.document.GroupInfo;
import ir.ac.aut.hesabgar.domain.repo.GroupInfoRepo;
import ir.ac.aut.hesabgar.group_feign.GroupGateway;
import ir.ac.aut.hesabgar.request.group.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupGatewayController {
    @Autowired
    private GroupGateway groupGateway;

    @Autowired
    private GroupInfoRepo groupInfoRepo;



    @PostMapping("/createGroup")
    public ResponseEntity<Object> createGroup(@RequestBody CreatingGroupRequest creatingGroupInfoRequest) {
       return groupGateway.createGroup(creatingGroupInfoRequest);
    }

    @GetMapping("/getGroup/{groupId}")
    public ResponseEntity<Object> getGroup(@PathVariable String groupId){
        GroupInfo groupInfo = groupInfoRepo.getGroupInfoById(groupId);
        if(groupInfo != null){
            return ResponseEntity.status(HttpStatus.OK).body(groupInfo);
        } return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/inviteToGroup")
    public ResponseEntity<Object> inviteGroup(@RequestBody InvitingToGroupRequest invitingToGroupInfoRequest) {
        return groupGateway.inviteGroup(invitingToGroupInfoRequest);
    }

    @PostMapping("/deleteGroup")
    public ResponseEntity<Object> deleteGroup(@RequestBody DeletingGroupRequest deletingGroupRequest) {
        return groupGateway.deleteGroup(deletingGroupRequest);

    }

    @PostMapping("/makingNewInvoiceAdmin")
    public ResponseEntity<Object> makingMemberInvoiceAdmin(@RequestBody MakingMemberInvoiceAdminRequest makingMemberInvoiceAdminRequest) {
        return groupGateway.makingMemberInvoiceAdmin(makingMemberInvoiceAdminRequest);

    }

    @PostMapping("/dismissalInvoiceAdmin")
    public ResponseEntity<Object> DismissalMemberFromInvoiceAdmin(@RequestBody MakingMemberInvoiceAdminRequest dismissalMemberInvoiceAdminRequest) {
        return groupGateway.DismissalMemberFromInvoiceAdmin(dismissalMemberInvoiceAdminRequest);

    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        groupInfoRepo.deleteAll();
    }

    @PostMapping("/deletingGroupMember")
    public String deletingGroupMember(@RequestBody DeletingGroupMemberRequest deletingGroupMemberRequest) {
        return groupGateway.deletingGroupMember(deletingGroupMemberRequest);
    }

    @PostMapping("/addInvoice")
    public ResponseEntity<Object> getPaymentTerm(@RequestBody AddingInvoiceRequest addingInvoiceRequest) {
       return groupGateway.getPaymentTerm(addingInvoiceRequest);

    }


}
