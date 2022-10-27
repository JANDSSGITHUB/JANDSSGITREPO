package com.dss;

import com.dss.view.test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Dss1UiV1Application {

	public static void main(String[] args) {
		SpringApplication.run(Dss1UiV1Application.class, args);
		test t = new test();
		t.test();
	}

}
