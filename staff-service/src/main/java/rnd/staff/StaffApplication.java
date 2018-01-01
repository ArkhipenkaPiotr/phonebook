package rnd.staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StaffApplication {
    public static void main (String args[]){
        SpringApplication.run(StaffApplication.class,args);
    }
}
