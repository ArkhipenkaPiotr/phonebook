package rnd.addresses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync
public class AddressesApplication {
    public static void main (String args[]){
        SpringApplication.run(AddressesApplication.class,args);
    }
}
