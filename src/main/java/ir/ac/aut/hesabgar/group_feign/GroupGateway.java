package ir.ac.aut.hesabgar.group_feign;

import ir.ac.aut.hesabgar.domain.document.GroupInfo;
import ir.ac.aut.hesabgar.request.group.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.List;

@FeignClient(name ="feign-group", url = "http://localhost:8081/group")
public interface GroupGateway {
     @RequestMapping(method = RequestMethod.POST, path = "/createGroup")
     ResponseEntity<Object> createGroup(CreatingGroupRequest creatingGroupInfoRequest);

     @RequestMapping(method = RequestMethod.POST, path = "/inviteToGroup")
     ResponseEntity<Object> inviteGroup(InvitingToGroupRequest invitingToGroupInfoRequest);

     @RequestMapping(method = RequestMethod.POST, path = "/deleteGroup")
     ResponseEntity<Object> deleteGroup(DeletingGroupRequest deletingGroupRequest);

     @RequestMapping(method = RequestMethod.POST, path = "/makingNewInvoiceAdmin")
     ResponseEntity<Object> makingMemberInvoiceAdmin(MakingMemberInvoiceAdminRequest makingMemberInvoiceAdminRequest);

     @RequestMapping(method = RequestMethod.POST, path = "/dismissalInvoiceAdmin")
     ResponseEntity<Object> DismissalMemberFromInvoiceAdmin(MakingMemberInvoiceAdminRequest dismissalMemberInvoiceAdminRequest);

     @RequestMapping(method = RequestMethod.POST, path = "/deletingGroupMember")
     String deletingGroupMember(DeletingGroupMemberRequest deletingGroupMemberRequest);

     @RequestMapping(method = RequestMethod.POST, path = "/addInvoice")
     ResponseEntity<Object> getPaymentTerm(AddingInvoiceRequest addingInvoiceRequest);

     @RequestMapping(method = RequestMethod.POST, path = "/payInvoice")
     ResponseEntity<Object> payingInvoice(PayingInvoiceRequest payingInvoiceRequest);

     @RequestMapping(method = RequestMethod.POST, path = "/showDebt")
     ResponseEntity<Object> debtorInfo(ShowDebtRequest showDebtRequest);

}
