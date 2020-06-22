<%@ include file="/init.jsp" %>

<%@ page import="java.util.List" %>
<%@ page import="com.as.poc.documentlibrary.upload.constants.UploadToDocumentLibraryPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.repository.model.Folder" %>

<liferay-portlet:actionURL name="upload" var="uploadUrl"/>

<%
List <Folder> folderList = (List<Folder>) request.getAttribute( com.as.poc.documentlibrary.upload.constants.UploadToDocumentLibraryPortletKeys.FOLDERLIST );
%>

<div class="container mb-3 border">
	<div class="row">
		<div class="col-4">RepositoryId</div>
		<div class="col-4">FolderId</div>
		<div class="col-4">Name</div>
	</div>
<% for(Folder l : folderList) {%>
	<div class="row">
		<div class="col-4"><%= l.getRepositoryId() %></div>
		<div class="col-4"><%= l.getFolderId() %></div>
		<div class="col-4"><%= l.getName() %></div>
	</div>
<% } %>
</div>

<form id="uploadForm" method="POST" action="${uploadUrl}" enctype="multipart/form-data">
	<div class="row">
		<span class="col-2 text-right">repositoryId</span>
		<input class="col-5" type="text" name="<portlet:namespace/>repositoryId" id="<portlet:namespace/>repositoryId" required="required"/>
	</div>
	<div class="row">
		<span class="col-2 text-right">folderId</span>
		<input class="col-5" type="text" name="<portlet:namespace/>folderId" id="<portlet:namespace/>folderId" required="required"/>
	</div>
	<div class="row">
		<span class="col-2 text-right">title</span>
		<input class="col-5" type="text" name="<portlet:namespace/>title" id="<portlet:namespace/>title" required="required"/>
	</div>
	<div class="row">
		<span class="col-2 text-right">description</span>
		<input class="col-5" type="text" name="<portlet:namespace/>description" id="<portlet:namespace/>description" required="required"/>
	</div>
	<div class="row">
		<span class="col-2 text-right">changeLog</span>
		<input class="col-5" type="text" name="<portlet:namespace/>changeLog" id="<portlet:namespace/>changeLog" required="required"/>
	</div>
	<div class="row">
		<span class="col-2 text-right">file</span>
		<input class="col-5" type="file" name="<portlet:namespace/>file" id="<portlet:namespace/>file" required="required"/>
	</div>
	<div class="row">
		<button  class="btn btn-default" type="submit" value="Cargar archivo" >Guardar</button>
	</div>	
</form>