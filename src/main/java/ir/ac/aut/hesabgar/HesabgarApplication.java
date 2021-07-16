package ir.ac.aut.hesabgar;

import ir.ac.aut.hesabgar.group_feign.GroupGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.HttpServerErrorException;

@SpringBootApplication
@EnableFeignClients
//@EnableFeignClients(basePackageClasses = GroupGateway.class)
public class HesabgarApplication {

    public static void main(String[] args) {
        SpringApplication.run(HesabgarApplication.class, args);
    }

}


