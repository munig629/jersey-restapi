package com.example.server;

import java.util.ArrayList;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.example.common.ApiBase;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource extends ApiBase<MyRequest, MyResponse> {

	@Context
	UriInfo uriInfo;

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "application/json" media type.
	 * 
	 * @return MyResponse
	 */
	@Override
	public MyResponse get() {

		MyResponse res = new MyResponse();
		res.statusCode = 0;
		res.resultData = "GET：id=" + uriInfo.getQueryParameters().get("id");

		return res;
	}

	/**
	 * Method handling HTTP POST requests. The returned object will be sent to the
	 * client as "application/json" media type.
	 * 
	 * @param MyRequest
	 * @return MyResponse
	 */
	@Override
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
