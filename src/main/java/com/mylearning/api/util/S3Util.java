package com.mylearning.api.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Util {

	private final static Logger LOGGER = LoggerFactory.getLogger(S3Util.class.getName());


	public static String readFromS3(String docId) {
		String gsrl = null;

		try {
			AWSCredentialsProvider cre = DefaultAWSCredentialsProviderChain.getInstance();
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(cre).withRegion(Constant.AWS_REGION).build();

			gsrl = s3Client.getObjectAsString(Constant.S3BUCKET, docId.substring(docId.length() - 4) + "/" + docId + ".json");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return gsrl;
	}
}
