package com.as.poc.aws.s3.command;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.as.poc.aws.s3.constants.AwsS3IntegrationPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + AwsS3IntegrationPortletKeys.AWSS3INTEGRATION,
			"mvc.command.name=upload"
		},
		service = MVCActionCommand.class
)
public class ActionCommand implements MVCActionCommand{

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException {
		
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
                actionRequest);
		File file = uploadRequest.getFile("file");
		String filename = uploadRequest.getFileName("file");
		
		/*AWSCredentials credentials = new BasicAWSCredentials(
				AwsS3IntegrationPortletKeys.KEY, 
				AwsS3IntegrationPortletKeys.SECRET
				);*/
		AWSCredentials credentials = new BasicAWSCredentials(
				PropsUtil.get("KEY"), 
				PropsUtil.get("SECRET")
				);
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1)
				.build();
		s3client.putObject(
				PropsUtil.get("BUCKETNAME"),
				filename, 
				file
		);
		return false;
	}

}
