package ir.ac.aut.hesabgar;

import ir.ac.aut.hesabgar.domain.document.UserInfo;
import ir.ac.aut.hesabgar.domain.repo.UserInfoRepo;
import ir.ac.aut.hesabgar.group_feign.GroupGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.HttpServerErrorException;

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
        UserInfo adminInfo = userInfoRepo.getUserInfoByUserName("ADMIN");
        if(adminInfo == null){
            adminInfo = new UserInfo();
            adminInfo.setActive(true);
            adminInfo.setAdmin(true);
            adminInfo.setUserName("ADMIN");
            adminInfo.setPassword("adminadmin");
            userInfoRepo.save(adminInfo);
        }
    }
}


