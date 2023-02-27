package com.example.server;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.example.vo.MyRequest;
import com.example.vo.MyResponse;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "application/json" media type.
	 * 
	 * @param id
	 * @return MyResponse
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MyResponse get(@QueryParam("id") int id) {

		MyResponse res = new MyResponse();
		res.statusCode = 0;
		res.resultData = "GET：" + id;

		return res;
	}

	/**
	 * Method handling HTTP POST requests. The returned object will be sent to the
	 * client as "application/json" media type.
	 * 
	 * @param req
	 * @return MyResponse
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MyResponse post(MyRequest req) {

		MyResponse res = new MyResponse();
		res.statusCode = 0;
		res.resultData = String.format("POST：id=%d, name=%s", req.id, req.name);
		res.errorMessage = new ArrayList<String>();
		res.errorMessage.add("ErrorMessage1");
		res.errorMessage.add("ErrorMessage2");
		res.errorMessage.add("ErrorMessage3");

		return res;
	}
}
