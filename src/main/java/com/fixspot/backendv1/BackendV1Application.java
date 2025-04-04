package com.fixspot.backendv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendV1Application {
	public static void main(String[] args) {
		SpringApplication.run(BackendV1Application.class, args);
	}
}


/**
 * To design authority problem solving flow and onboarding flow.
 * for geocoding/reverse-geocoding - https://maps.olakrutrim.com/apidocs/geocode
 * for places photo - https://maps.olakrutrim.com/apidocs/places
 * for distance and duration and poly-line on maps - https://maps.olakrutrim.com/apidocs/routing
 * */