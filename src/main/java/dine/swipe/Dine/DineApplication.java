package dine.swipe.Dine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DineApplication {

	public static void main(String[] args) {
		SpringApplication.run(DineApplication.class, args);
	}

}
