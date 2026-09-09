package be.commit_wealth;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class CommitWealthApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitWealthApplication.class, args);
	}

}
