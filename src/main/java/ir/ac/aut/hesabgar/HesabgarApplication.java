package ir.ac.aut.hesabgar;

import ir.ac.aut.hesabgar.domain.data.JoinedGroupInfo;
import ir.ac.aut.hesabgar.domain.data.UserReportStatus;
import ir.ac.aut.hesabgar.domain.document.UserInfo;
import ir.ac.aut.hesabgar.domain.repo.UserInfoRepo;
import ir.ac.aut.hesabgar.group_feign.GroupGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
//@EnableFeignClients(basePackageClasses = GroupGateway.class)
public class HesabgarApplication implements CommandLineRunner {
    @Autowired
    private UserInfoRepo userInfoRepo;

    public static void main(String[] args) {
        SpringApplication.run(HesabgarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserInfo userInfo = userInfoRepo.getUserInfoByUserName("ADMIN");
        if(userInfo == null){
            userInfo = new UserInfo();

            userInfo.setAdmin(true);
            userInfo.setActive(true);
            userInfo.setName("admin");
            userInfo.setLastName("admin");
            userInfo.setEmailAddress("admin@hesabgar.com");
            userInfo.setPassword("adminadmin");
            userInfo.setUserName("ADMIN");
            userInfo.setTelephoneNumber("09353368575");
            List<JoinedGroupInfo> joinedGroupInfos = new ArrayList<>();
            userInfo.setJoinedGroupList(joinedGroupInfos);
            userInfo.setOverallBalance(0);
            UserReportStatus userReportStatus = new UserReportStatus();
            userReportStatus.setReportCount(0);
            List<String> reportDescription = new ArrayList<>();
            userReportStatus.setReportDescription(reportDescription);
            userInfo.setUserReportStatus(userReportStatus);
            userInfo.setCreationDate(new Date());


            userInfoRepo.save(userInfo);
        }
    }
}


