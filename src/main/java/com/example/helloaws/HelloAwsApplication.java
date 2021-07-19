package com.example.helloaws;

import com.gremlin.*;

public class HelloAwsApplication {

	public static void main(String[] args) {


		final GremlinCoordinatesProvider coordinatesProvider = new GremlinCoordinatesProvider() {
			@Override
			public ApplicationCoordinates initializeApplicationCoordinates() {
				return new ApplicationCoordinates.Builder()
						.withType("HelloAWSExample")
						.withField("name", "tammy").build();
			}
		};
		final GremlinServiceFactory gremlinServiceFactory = new GremlinServiceFactory(coordinatesProvider);
		final GremlinService gremlinService = gremlinServiceFactory.getGremlinService();

		final TrafficCoordinates injectionPoint = new TrafficCoordinates.Builder()
				.withType("main")
				.withField("name", "tammy")
				.build();
		gremlinService.applyImpact(injectionPoint);

	}



}
