package com.example.client;

import java.time.Duration;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.example.server.MyRequest;
import com.example.server.MyResource;
import com.example.server.MyResponse;

import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;

/**
 * JAX-RS Client API Sample
 */
public class MyClient {

	public static void main(String[] args) {

		MyRequest req = new MyRequest();
		req.id = 999;
		req.name = "nick";

		// Set Retry Count and Timeout 
		RetryPolicy<Object> retryPolicy = RetryPolicy.builder()
				.withDelay(Duration.ofSeconds(1))
				.withMaxRetries(3)
				.build();

		ClientConfig config = new ClientConfig()
				.property(ClientProperties.CONNECT_TIMEOUT, "5000")
				.property(ClientProperties.READ_TIMEOUT, "3000");

		// POST request with request parameter 
		Builder builder = ClientBuilder.newClient(config)
				.target("http://localhost:8080/simple-restapi")
				.path("/webapi/myresource")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("API-Key", "91a43543c872f77aa1c835ee01996c96");

		String result = Failsafe.with(retryPolicy)
				.get(() -> builder.post(Entity.entity(req, MediaType.APPLICATION_JSON), String.class));

		System.out.println(result);

		// Call an instance method in Java
		MyResource resource = new MyResource();
		MyResponse res = resource.post(req);

		System.out.println(res.statusCode);
		System.out.println(res.resultData);
		System.out.println(res.errorMessage);
	}
}