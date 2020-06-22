package com.as.poc.documentlibrary.upload.command;

import com.as.poc.documentlibrary.upload.constants.UploadToDocumentLibraryPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.io.InputStream;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + UploadToDocumentLibraryPortletKeys.UPLOADTODOCUMENTLIBRARY,
			"mvc.command.name=upload"
		},
		service = MVCActionCommand.class
)
public class ActionCommand implements MVCActionCommand{

	@Reference
	private DLAppService _dlAppService;
	
	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(
                actionRequest);

		long repositoryId = ParamUtil.getLong(uploadPortletRequest, "repositoryId");
		long folderId = ParamUtil.getLong(uploadPortletRequest, "folderId");
		String sourceFileName = uploadPortletRequest.getFileName("file");
		String title = ParamUtil.getString(uploadPortletRequest, "title");
		String description = ParamUtil.getString(uploadPortletRequest, "description");
		String changeLog = ParamUtil.getString(uploadPortletRequest, "changeLog");

		try (InputStream inputStream = uploadPortletRequest.getFileAsStream("file")) {

		    String contentType = uploadPortletRequest.getContentType("file");
		    long size = uploadPortletRequest.getSize("file");

		    ServiceContext serviceContext = ServiceContextFactory.getInstance(
		            DLFileEntry.class.getName(), uploadPortletRequest);

		    FileEntry fileEntry = _dlAppService.addFileEntry(
                    repositoryId, folderId, sourceFileName, contentType, title, 
                    description, changeLog, inputStream, size, serviceContext);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return false;
	}

}
