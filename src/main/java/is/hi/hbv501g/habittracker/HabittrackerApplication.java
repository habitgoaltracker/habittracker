package is.hi.hbv501g.habittracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class HabittrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HabittrackerApplication.class, args);
    }

}
