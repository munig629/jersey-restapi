package com.example.common;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Abstract Class of JAX-RS resources
 */
public abstract class ApiBase<T, S> {

	/**
	 * Abstract Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "application/json" media type.
	 * 
	 * @return S
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public abstract S get();

	/**
	 * Abstract Method handling HTTP POST requests. The returned object will be sent
	 * to the client as "application/json" media type.
	 * 
	 * @param T
	 * @return S
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public abstract S post(T req);

}
