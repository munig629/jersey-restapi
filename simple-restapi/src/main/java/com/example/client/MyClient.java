package com.example.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.example.server.MyResource;
import com.example.vo.MyRequest;
import com.example.vo.MyResponse;

/**
 * Jersey Client API Sample
 */
public class MyClient {

	public static void main(String[] args) {

		MyRequest req = new MyRequest();
		req.id = 999;
		req.name = "nick";

		// POST request with request parameter 
		String result = ClientBuilder.newClient()
				.target("http://localhost:8080/simple-restapi")
				.path("/webapi/myresource")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("API-Key", "91a43543c872f77aa1c835ee01996c96")
				.post(Entity.entity(req, MediaType.APPLICATION_JSON), String.class);

		System.out.println(result);
		
		// Call an instance method in Java
		MyResource resource = new MyResource();
		MyResponse res = resource.post(req);
		
		System.out.println(res.statusCode);
		System.out.println(res.resultData);
		System.out.println(res.errorMessage);
	}
}