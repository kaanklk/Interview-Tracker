package tcs.interviewtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:system-specific.properties")
})
public class InterviewTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewTrackerApplication.class, args);
	}

}
