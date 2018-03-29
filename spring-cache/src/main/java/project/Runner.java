package project;

import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import project.config.Config;
import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by borde on 03/28/2018.
 */
@SpringBootApplication
@EnableScheduling
@EnableRetry
@ComponentScan
@EnableIgniteRepositories
public class Runner {

    @Autowired
    private Ignite ignite;

    public static void main(String[] args) {

        SpringApplication.run(new Class[] {Runner.class, Config.class}, args);

    }

}