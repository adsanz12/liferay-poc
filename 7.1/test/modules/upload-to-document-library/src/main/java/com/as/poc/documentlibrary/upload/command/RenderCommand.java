package com.as.poc.documentlibrary.upload.command;

import com.as.poc.documentlibrary.upload.constants.UploadToDocumentLibraryPortletKeys;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + UploadToDocumentLibraryPortletKeys.UPLOADTODOCUMENTLIBRARY,
				"mvc.command.name=/" 
		},
	service = MVCRenderCommand.class
)
public class RenderCommand implements MVCRenderCommand{

	@Reference
	private DLAppService _dlAppService;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long [] groupId = themeDisplay.getUser().getGroupIds();
			List<Folder> folders = null;
			List<Folder> folderList = new ArrayList<Folder> ();
			for( long gid : groupId) {
				folders = _dlAppService.getFolders(gid, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, true);
				for( Folder folder : folders ) {
					folderList.add( folder );
				}
			}
			renderRequest.setAttribute( UploadToDocumentLibraryPortletKeys.FOLDERLIST , folderList);
       } catch (Exception e) {
           e.printStackTrace();
       }
		return "/view.jsp";
	}

}
