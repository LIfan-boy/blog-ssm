<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" />

<html>
<head>
<title>修改个人信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="${path }/static/jquery-easyui-1.3.5/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="${path }/static/jquery-easyui-1.3.5/themes/default/easyui.css" type="text/css"></link>
<script src="${path}/static/jquery-easyui-1.3.5/jquery.min.js"></script>
<script src="${path}/static/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script src="${path}/static/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
</head>

<body style="margin: 10px; font-family: microsoft yahei">

	<div id="p" class="easyui-panel" title="修改信息" style="padding: 10px;">
		<form id="uploadform" action="#" method="post" enctype="multipart/form-data">
			<table cellspacing="20px">
				<tr>
					<td width="80px">上传头像：</td>
					<td>
						<input type="file" id="fileName" name="fileName" style="width: 200px"/>
						<input type="button" id="uploadBtn" value="上传" style="width: 60px" 
							onclick="javascript:submitUpload()" />
					</td>
					<td><img id="showPhoto" alt="头像预览" src="" style="width: 100px; height: 140px;"></td>
				</tr>
			</table>	
		</form>
		<form id="myform" method="post">
			<table cellspacing="20px">
				<tr>
					<td width="80px">文件路径：</td>
					<td><input type="text" id="imageName" name="imageName"
						style="width: 200px" /></td>
				</tr>
				<tr>
					<td width="80px">用户名：</td>
					<td><input type="text" id="username" name="username"
						style="width: 200px" /></td>
				</tr>

				<tr>
					<td width="80px">博主信息：</td>
					<td><input type="text" id="profile" name="profile"
						style="width: 200px" /></td>
				</tr>
				<tr>
					<td width="80px">昵称：</td>
					<td><input type="text" id="nick" name="nick"
						style="width: 200px" /></td>
				</tr>
				
				<tr>
					<td width="80px" valign="top">博主签名：</td>
					<td><textarea id="sign" name="sign"
						style="width: 400px; height: 100px"></textarea></td>
				</tr>
				
				<tr>
					<td>博主权限：</td>
					<td>
						<select id="blogTypeId" class="easyui-combobox" name="blogType.id" 
							style="width: 154px" editable="false" panelHeight="auto">
							<option value="">请选择博客类别...</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td><a href="javascript:submitData()"
						class="easyui-linkbutton" data-options="">确认修改</a>
					</td>
				</tr>
			</table>
		</form>
	</div>


	<script type="text/javascript">
		// 初始化页面信息
		$(function() {
			$('#username').attr('value', '${info.userName}');
			$('#profile').attr('value', '${info.profile}');
			$('#nick').attr('value', '${info.nickName}');
			$('#sign').html('${info.sign}');
			$('#showPhoto').attr('src', '${path}${info.imageName}');
		})
		
		// 提交修改
		function submitData() {
			var username = $("#username").val();
			var profile = $("#profile").val();
			var nick = $("#nick").val();
			var sign = $("#sign").val();
			var imagename = $('#imageName').val();

			$.post("${path}/admin/info/updateInfo", 
				{
					'username':username,
					'profile':profile,
					'nick':nick,
					'sign':sign,
					'imagename':imagename
				}, 
				function(result) {
					if (result.flag) {
						$.messager.alert("系统提示", "信息更新成功！");
					} else {
						$.messager.alert("系统提示", "信息更新失败！");
					}
				}, 'json');
			
		}
		
/**
 * Ajax 提交表单，实现文件上传
 */
function submitUpload() {
	var formData = new FormData($('#uploadform')[0]);
	$.ajax({
		url : '${path}/admin/info/uploadfile',
		type : 'post',
		cache : false,
		data : formData, // 一种的新的数据类型
		processData : false,
		contentType : false,
		success : function(data) {
			console.log(data.fla);
			var data = eval("(" + data + ")"); // 将 json 数据转换成 js 对象
			if(data.flag) {
				$('#imageName').attr('value', data.filepath);
	            $.messager.alert("系统提示", "上传成功");
	        } else {
	            $.messager.alert("系统提示", "上传失败");
	            return;
	        }
		},
		error : function(data) {
			 $.messager.alert("系统错误", "Error--上传失败");
		}
	})
	
}
	</script>
</body>
</html>
