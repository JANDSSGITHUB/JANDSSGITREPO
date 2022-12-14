package DSS8APIGATEWAY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Dss8ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dss8ApiGatewayApplication.class, args);
	}

}
