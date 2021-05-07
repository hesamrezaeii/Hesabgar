package ir.ac.aut.hesabgar.helper;

import ir.ac.aut.hesabgar.domain.data.GroupMember;
import ir.ac.aut.hesabgar.domain.data.PaymentTerm;
import ir.ac.aut.hesabgar.domain.data.events.AddingInvoiceEvent;
import ir.ac.aut.hesabgar.domain.document.GroupInfo;
import ir.ac.aut.hesabgar.domain.document.UserInfo;
import ir.ac.aut.hesabgar.domain.repo.GroupInfoRepo;
import ir.ac.aut.hesabgar.domain.repo.UserInfoRepo;
import ir.ac.aut.hesabgar.request.group.AddingInvoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InvoiceHelper {

    @Autowired
    private GroupInfoRepo groupInfoRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;

    private String findMin(Map<String, Integer> groupBalance) {
        int min = 0;
        String toBeReturn = null;
        for (String user : groupBalance.keySet()) {
            if (groupBalance.get(user) < min) {
                toBeReturn = user;
                min = groupBalance.get(user);
            }

        }
        return toBeReturn;
    }

    private String findMax(Map<String, Integer> groupBalance) {
        int max = 0;
        String toBeReturn = null;
        for (String user : groupBalance.keySet()) {
            if (groupBalance.get(user) > max) {
                toBeReturn = user;
                max = groupBalance.get(user);
            }

        }
        return toBeReturn;
    }

    public GroupInfo findPaymentTerm(GroupInfo groupInfo, Map<String, Integer> balance) {
        List<GroupMember> groupMembers = groupInfo.getMembers();
        Map<String, Integer> groupBalance = new HashMap<>();
        for (GroupMember groupMember : groupMembers) {
            groupMember.setPaymentTerms(new ArrayList<>());
        }
        for (String s : balance.keySet()) {
            groupBalance.put(s, groupInfo.getGroupBalance().get(s));
        }

        for (String user : groupBalance.keySet()) {
            for (String user2 : groupBalance.keySet()) {
                if (groupBalance.get(user).equals(groupBalance.get(user2) * -1) && !user.equals(user2) && !groupBalance.get(user).equals(0)) {

                    PaymentTerm paymentTermUser = new PaymentTerm(user2, groupBalance.get(user2));
                    PaymentTerm paymentTermUser2 = new PaymentTerm(user, groupBalance.get(user));

                    for (GroupMember groupMember : groupMembers) {
                        if (groupMember.getUserId().equals(user)) {
                            List<PaymentTerm> paymentTerms = groupMember.getPaymentTerms();
                            paymentTerms.add(paymentTermUser);
                        } else if (groupMember.getUserId().equals(user2)) {
                            List<PaymentTerm> paymentTerms = groupMember.getPaymentTerms();
                            paymentTerms.add(paymentTermUser2);
                        }
                    }

                    groupBalance.put(user, 0);
                    groupBalance.put(user2, 0);
                }
            }
        }
        while (findMin(groupBalance) != null) {
            String maxDebt = findMin(groupBalance);
            String maxCred = findMax(groupBalance);
            if (groupBalance.get(maxDebt) * -1 == groupBalance.get(maxCred)) {
                int minAmount = groupBalance.get(maxDebt);
                int maxAmount = groupBalance.get(maxCred);

                PaymentTerm paymentTermMaxDebt = new PaymentTerm(maxCred, groupBalance.get(maxDebt));
                PaymentTerm paymentTermMaxCred = new PaymentTerm(maxDebt, groupBalance.get(maxCred));

                for (GroupMember groupMember : groupMembers) {
                    if (groupMember.getUserId().equals(maxCred)) {
                        List<PaymentTerm> paymentTerms = groupMember.getPaymentTerms();
                        paymentTerms.add(paymentTermMaxCred);
                    } else if (groupMember.getUserId().equals(maxDebt)) {
                        List<PaymentTerm> paymentTerms = groupMember.getPaymentTerms();
                        paymentTerms.add(paymentTermMaxDebt);
                    }
                }
                groupBalance.put(maxDebt, minAmount + maxAmount);
                groupBalance.put(maxCred, minAmount + maxAmount);

            } else if (groupBalance.get(maxDebt) * -1 > groupBalance.get(maxCred)) {
                int minAmount = groupBalance.get(maxDebt);
                int maxAmount = groupBalance.get(maxCred);


                PaymentTerm paymentTermMaxDebt = new PaymentTerm(maxCred, maxAmount * -1);
                PaymentTerm paymentTermMaxCred = new PaymentTerm(maxDebt, maxAmount);

                for (GroupMember groupMember : groupMembers) {
                    if (groupMember.getUserId().equals(maxCred)) {
                        List<PaymentTerm> paymentTerms = groupMember.getPaymentTerms();
                        paymentTerms.add(paymentTermMaxCred);
                    } else if (groupMember.getUserId().equals(maxDebt)) {
                        List<PaymentTerm> paymentTerms = groupMember.getPaymentTerms();
                        paymentTerms.add(paymentTermMaxDebt);
                    }
                }

                groupBalance.put(maxDebt, minAmount + maxAmount);
                groupBalance.put(maxCred, 0);

            } else if (groupBalance.get(maxDebt) * -1 < groupBalance.get(maxCred)) {
                int minAmount = groupBalance.get(maxDebt);
                int maxAmount = groupBalance.get(maxCred);


                PaymentTerm paymentTermMaxDebt = new PaymentTerm(maxCred, minAmount);
                PaymentTerm paymentTermMaxCred = new PaymentTerm(maxDebt, minAmount * -1);

                for (GroupMember groupMember : groupMembers) {
                    if (groupMember.getUserId().equals(maxCred)) {
                        List<PaymentTerm> paymentTerms = groupMember.getPaymentTerms();
                        paymentTerms.add(paymentTermMaxCred);
                    } else if (groupMember.getUserId().equals(maxDebt)) {
                        List<PaymentTerm> paymentTerms = groupMember.getPaymentTerms();
                        paymentTerms.add(paymentTermMaxDebt);
                    }
                }
                groupBalance.put(maxDebt, 0);
                groupBalance.put(maxCred, minAmount + maxAmount);
            }


        }
        groupInfo.setMembers(groupMembers);
        return groupInfoRepo.save(groupInfo);

    }

    public GroupInfo makingNewInvoiceEvent(GroupInfo groupInfo, AddingInvoiceRequest addingInvoiceRequest) {
        List<AddingInvoiceEvent> addingInvoiceEvents = groupInfo.getAddingInvoiceEvents();
        if (addingInvoiceEvents == null) {
            addingInvoiceEvents = new ArrayList<>();
        }
        AddingInvoiceEvent addingInvoiceEvent = new AddingInvoiceEvent();

        addingInvoiceEvent.setInvoiceAdmin(addingInvoiceRequest.getUserId());

        addingInvoiceEvent.setTopic(addingInvoiceRequest.getTopic());
        addingInvoiceEvent.setDescription(addingInvoiceRequest.getDescription());


        List<String> eventMembers = new ArrayList<>();
        for (String user : addingInvoiceRequest.getGroupShare().keySet()) {
            eventMembers.add(user);
        }
        addingInvoiceEvent.setEventMembers(eventMembers);

        Map<String, Integer> groupBalance = groupInfo.getGroupBalance();
        for (String user : addingInvoiceRequest.getGroupShare().keySet()) {
            int balance = addingInvoiceRequest.getGroupShare().get(user);
            groupBalance.put(user, balance + groupBalance.get(user));
        }

        Map<String, Integer> eventBalance = new HashMap<>();

        for (String user : addingInvoiceRequest.getGroupShare().keySet()) {
            eventBalance.put(user, addingInvoiceRequest.getGroupShare().get(user));
        }
        addingInvoiceEvent.setEventBalance(eventBalance);

        addingInvoiceEvent.setCreationDate(new Date());

        addingInvoiceEvents.add(addingInvoiceEvent);

        groupInfo.setAddingInvoiceEvents(addingInvoiceEvents);


        return groupInfo;
    }

    public void updateEventMembersBalance(AddingInvoiceRequest addingInvoiceRequest) {
        Map<String, Integer> groupShare = addingInvoiceRequest.getGroupShare();
        for (String userId : groupShare.keySet()) {
            UserInfo userInfo = userInfoRepo.getUserInfoById(userId);
            int balance = userInfo.getOverallBalance();
            userInfo.setOverallBalance(balance + groupShare.get(userId));
            userInfoRepo.save(userInfo);
        }
    }

}
