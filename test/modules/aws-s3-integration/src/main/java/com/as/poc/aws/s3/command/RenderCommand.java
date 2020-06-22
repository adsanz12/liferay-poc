package com.as.poc.aws.s3.command;

import com.as.poc.aws.s3.constants.AwsS3IntegrationPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + AwsS3IntegrationPortletKeys.AWSS3INTEGRATION,
				"mvc.command.name=/" 
		},
	service = MVCRenderCommand.class
)
public class RenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		return "/view.jsp";
	}

}
