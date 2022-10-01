package is.hi.hbv501g.habittracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class HabittrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HabittrackerApplication.class, args);
    }

}
