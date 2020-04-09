package com.mylearning.api.domain;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mylearning.api.bean.RequestBean;

public class ConsumeApi {

	public static String response(String val) throws UnsupportedEncodingException {
		AWSCredentialsProvider awsCred = DefaultAWSCredentialsProviderChain.getInstance();
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(awsCred).withRegion("eu-west-1").build();
		
		ClientConfig clientConfig = new ClientConfig();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("apiuser", "pass123");
		clientConfig.register(feature);

		final Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://xxxxxxxxxxx.elb.amazonaws.com/xx");
		client.register(feature);

		//For the POJO for the request
		//SetRequestBean(val);
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.entity(SetRequestBean(val), MediaType.APPLICATION_XML));
		String respse = response.readEntity(String.class);
		System.out.println(response.getStatus());
		System.out.println(respse);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(respse.getBytes().length);
		metadata.setContentType("application/xml");
		
		PutObjectRequest putObjectRequest = new PutObjectRequest("xxxxx", "xxxx"+".xml" ,
				new ByteArrayInputStream(respse.getBytes("UTF-8")), metadata);
		s3Client.putObject(putObjectRequest);
		client.close();
		

		return null;
	}

	private static RequestBean SetRequestBean(String val) {
		// TODO Auto-generated method stub
		RequestBean reqBean = new RequestBean();
		reqBean.setDocId(val);
		return reqBean;
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		
		ConsumeApi.response("232243");
	}
}
