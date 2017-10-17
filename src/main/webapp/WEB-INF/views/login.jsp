<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>博客管理系统</title>

<link href="${path }/static/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="${path }/static/css/login.css" rel="stylesheet">

<script src="${path}/static/jquery-easyui-1.3.5/jquery.min.js"></script>
<script src="${path}/static/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="login-header">
		<h1>欢迎来到小凡博客，永远相信知识的力量！</h1>
	</div>

	<div class="login-bg" style="background-image: url('${path }/static/images/login_bg.jpg')">
		<div class="login-context">

			<form class="form-login" action="${path }/loginCheck" method="post">
				<h2 class="form-login-heading">管理平台登录</h2>
				<label for="inputEmail" class="sr-only">用户名</label>
				<input type="email" id="inputEmail" name="userName" onblur="match()" class="form-control" placeholder="用户名" required autofocus>
				<label for="inputPassword" class="sr-only">密&nbsp;&nbsp;码</label>
				<input type="password" id="inputPassword" name="password" class="form-control" placeholder="密&nbsp;&nbsp;&nbsp;&nbsp;码" required>
				<label for="inputImagecode" class="sr-only">验证码</label>
				<input type="text" id="inputImagecode" onblur="javascript:codeCheck();" class="form-control" placeholder="验证码" required>
				<div class="from-code">
					<img alt="验证码" src="${path }/captcha-image" id="imagecode" class="image-code">
					<a href="javascript:reloadCode();"><img alt="刷新" src="${path }/static/images/shuaxin.png" /></a>
				</div>
				<div class="checkbox">
					<label>
						<input type="checkbox" id="boxName" name="boxName" value="remember-me"> 记住密码
					</label>
				</div>
				<input id="submitbtn" type="submit" class="btn btn-lg btn-primary btn-block" value="登&nbsp;&nbsp;&nbsp;&nbsp;录"></input>
			</form>

		</div>
	</div>

	<div class="login-footer">
		<p>Copyright&nbsp;©&nbsp;2017-2022&nbsp;小凡博客&nbsp;版权所有</p>
	</div>
	
	<script type="text/javascript">
		
		function submitData() {
			var userName = $('#inputEmail').val();
			var password = $('#inputPassword').val();
			var box = $('#boxName').val();
			$.post(
				'${path}/loginCheck',
				{
					'userName':userName,
					'password':password,
					'boxName':box
				},
				function(result) {
					if(!result.flag) {
						alert("用户名或密码错误！")
					}
				}, 'json')
		}
		
		// 刷新验证码
		function reloadCode() {
			var time = new Date().getTime();
			$("#imagecode").attr('src', '${path }/captcha-image?id='+time);
		}

		// 使用 ajax 异步校验用户名
		function match() {
			var txt = $('#inputEmail').val();
			$.post(
				'${path}/match',
				{
					'name' : txt
				},
				function(result) {
					if (result.flag) {
						$('#inputEmail').css('background-color', 'rgba(91, 255, 173, 0.2)')
					} 
					else {
						$('#inputEmail').css('background-color', 'rgba(255, 81, 81, 0.2)')
					}
				}, 'json');
		}
		
		// Ajax 验证验证码
		function codeCheck() {
			var code = $('#inputImagecode').val();
			$.post(
				'${path}/codeCheck',
				{
					'code' : code
				},
				function(result) {
					if(result.ok) {
						//alert("验证码正确！");
						$('#inputImagecode').css('background-color', 'rgba(91, 255, 173, 0.2)')
						$('#submitbtn').addAttr('onsubmit');
					} else {
						//alert("验证码错误！");
						$('#inputImagecode').css('background-color', 'rgba(255, 81, 81, 0.2)')
						$('#submitbtn').removeAttr('onsubmit');
					}
				}, 'json');
		}
		
		function box() {
			var result = $('#boxName').val();
			alert(result);
		}
	</script>
</body>

</html>