package com.example.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

//		System.out.println("AuthenticationFilter start");
		
		// Extract and validate the API key from the request
		String apiKey = requestContext.getHeaderString("API-Key");

		// If no API key; block access
		if (apiKey == null || apiKey.isEmpty()) {
			requestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
			return;
		}

		try {

			// Convert MD5 API key to SHA256
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(apiKey.getBytes(StandardCharsets.UTF_8));
			String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();

			// Compare with API key on the server
			ResourceBundle rb = ResourceBundle.getBundle("common");
			String serverKey = rb.getString("server.apikey");
			
			if (!serverKey.equals(sha256)) {
				requestContext.abortWith(Response.status(
						Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
				return;
			}

		} catch (NoSuchAlgorithmException e) {
			requestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
		}
	}

}
