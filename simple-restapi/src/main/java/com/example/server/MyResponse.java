package com.example.server;

import java.util.List;

import com.example.common.ResponseBase;

/**
 * Response class of MyResource
 */
public class MyResponse extends ResponseBase {

	public int statusCode;

	public String resultData;

	public List<String> errorMessage;
}
