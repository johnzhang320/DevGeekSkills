package hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
/**
 * 
 * Create a GitHub lookup service

	Next you need to create a service that queries GitHub to find user information.
	
 *  The GitHubLookupService class uses Spring’s RestTemplate to invoke a remote REST point (api.github.com/users/), 
 *  and then convert the answer into a User object. Spring Boot automatically provides a RestTemplateBuilder that 
 *  customizes the defaults with any auto-configuration bits (i.e. MessageConverter).
 *
 */
/** 
 * The class is marked with the @Service annotation, making it a candidate for Spring’s component scanning to detect 
 * it and add it to the application context.
 */
@Service
public class GitHubLookupService {

    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

    private final RestTemplate restTemplate;
    /**
     * The GitHubLookupService class uses Spring’s RestTemplate to invoke a remote REST point (api.github.com/users/), 
     * and then convert the answer into a User object. Spring Boot automatically provides a RestTemplateBuilder that 
     * customizes the defaults with any auto-configuration bits (i.e. MessageConverter).
     * 
     * 
     * This is constructor to create RestTemplate
     * @param restTemplateBuilder
     */
    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    /**
     * This is kind of async client used RestTemplate
     * CompletableFuture is wrapper to contain object (here is User)
     * The findUser method is flagged with Spring’s @Async annotation, indicating it will run on a separate thread. 
     * The method’s return type is CompletableFuture<User> instead of User, a requirement for any asynchronous service. 
     * This code uses the completedFuture method to return a CompletableFuture instance which is already completed with 
     * result of the GitHub query.
     * @param user
     * @return
     * @throws InterruptedException
     */
    /*
     * Creating a local instance of the GitHubLookupService class does NOT allow the findUser method to run asynchronously. 
     * It must be created inside a @Configuration class or picked up by @ComponentScan.
     */
    	
    /*
     * @Async annotation, indicating it will run on a separate thread
     */
    @Async
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        logger.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }

}
