package hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
/**
 * 
 * @SpringBootApplication is a convenience annotation that adds all of the following:

	@Configuration tags the class as a source of bean definitions for the application context.
	@EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, 
	other beans, and various property settings.
 *
 */
/*
 * The @EnableAsync annotation switches on Spring’s ability to run @Async methods in a background thread pool. 
 * This class also customizes the used Executor. In our case, we want to limit the number of concurrent threads 
 * to 2 and limit the size of the queue to 500. There are many more things you can tune. By default, a 
 * SimpleAsyncTaskExecutor is used.
 */
@SpringBootApplication
@EnableAsync
public class Application {

    public static void main(String[] args) {
        // close the application context to shut down the custom ExecutorService
        SpringApplication.run(Application.class, args).close();
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }


}