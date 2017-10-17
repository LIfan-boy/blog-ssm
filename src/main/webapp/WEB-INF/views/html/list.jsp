<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath }" var="path" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文件列表</title>

<link rel="stylesheet" href="${path }/static/jquery-easyui-1.3.5/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="${path }/static/jquery-easyui-1.3.5/themes/default/easyui.css" type="text/css"></link>

<script type="text/javascript" src="${path}/static/js/jquery.min.js"></script>

<script type="text/javascript" src="${path}/static/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/static/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

</head>
<body>
	<div>
		
		<table id="tab">
			<c:forEach items="${imageList }" var="list">
				<tr>
					<th><img id="imgPath" style="width: 100px; height: 140px;" alt="list" src="${path }${list}"></th>
					<th><button id="btn" onclick="deleteImg()">删除</button></th>
				</tr>
			</c:forEach>
			
		</table>
		
	</div>
	<script type="text/javascript">
	function deleteImg() {
		var url = $('#imgPath').attr('src');
		$.post(
			'${path}/admin/list/deleteImg',
			{
				'url' : url
			},
			function(result) {
				if(result.flag) {
					alert("删除成功");
					$('#tab').onload();
				} else {
					alert("删除失败");
				}			
			}, 
			'json'
		);
	}	
	
	
	</script>
</body>
</html>