<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL name="upload" var="uploadUrl"/>

<form id="uploadForm" method="POST" action="${uploadUrl}" enctype="multipart/form-data">
	<input type="file" name="<portlet:namespace/>file" id="<portlet:namespace/>file" required="required"/>
	<button  class="btn btn-default" type="submit" value="Cargar archivo" >Guardar</button>	
</form>