package danny.yugioh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@SpringBootApplication(scanBasePackages = "danny.yugioh")
@EnableJpaRepositories(basePackages = {"danny.yugioh.repository"})
@EntityScan(basePackages = "danny.yugioh.entity")
public class SpringDataJpaApplication extends SpringBootServletInitializer {
    public static void main(String[] args){
        SpringApplication.run(SpringDataJpaApplication.class);
    }
}
