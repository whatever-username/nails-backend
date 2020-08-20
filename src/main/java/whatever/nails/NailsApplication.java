package whatever.nails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "whatever.nails.*",
exclude = {HibernateJpaAutoConfiguration.class, SecurityAutoConfiguration.class})
public class NailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NailsApplication.class, args);
	}

}
