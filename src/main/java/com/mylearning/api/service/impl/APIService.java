package com.mylearning.api.service.impl;

import javax.ws.rs.core.Response;

import com.mylearning.api.bean.RequestBean;



public interface APIService {		

	public Response getDocument(RequestBean reqBean) throws Exception;

}
