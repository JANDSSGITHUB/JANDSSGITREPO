package com.demo.DSS4MSMOVIE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class Dss4MsMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dss4MsMovieApplication.class, args);
	}

}
