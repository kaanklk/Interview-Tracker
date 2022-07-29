package tcs.interviewtracker;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import tcs.interviewtracker.properties.CvStorageProperties;

@SpringBootApplication
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:system-specific.properties")
})
@EnableConfigurationProperties({
		CvStorageProperties.class
})
public class InterviewTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewTrackerApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
