package com.mylearning.api.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import com.mylearning.api.bean.RequestBean;
import com.mylearning.api.bean.ResponseBean;
import com.mylearning.api.util.S3Util;

@Configuration
@Component
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class APIServiceImpl implements APIService {

	@Autowired
	ConfigurableEnvironment env;
	

	@Override
	@POST
	@Path("/docs")
	public Response getDocument(RequestBean reqBean) throws Exception {
		String dataRes = null;		
		ResponseBuilder rBuild = null;
		dataRes = S3Util.readFromS3(reqBean.getDocId());
		if (null != dataRes) {
			rBuild = Response.ok(dataRes.getBytes(), "application/json");
			rBuild.header("Content-Length", dataRes.getBytes().length);

		} else {
			ResponseBean responseBean = new ResponseBean();
			responseBean.setErrorCode("1000");
			responseBean.setErrorMsg("Document Not available in repository");
			rBuild = Response.ok(responseBean, "application/json");

		}
		return rBuild.build();
		
	}

}
