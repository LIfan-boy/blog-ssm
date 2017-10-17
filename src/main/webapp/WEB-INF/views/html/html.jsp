<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath }" var="path" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${blog.title }</title>

<link rel="stylesheet" href="${path }/static/jquery-easyui-1.3.5/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="${path }/static/jquery-easyui-1.3.5/themes/default/easyui.css" type="text/css"></link>

<script type="text/javascript" src="${path}/static/js/jquery.min.js"></script>

<script type="text/javascript" src="${path}/static/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/static/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" charset="utf-8"
	src="${path}/static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${path}/static/ueditor1_4_3_3/ueditor.all.min.js"></script>	
<script type="text/javascript" charset="utf-8"
	src="${path}/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>

<style type="text/css">
	.art {
		width: 60%;
		margin: auto;
	}
	#title {
		text-align: center;
	}
	.msg {
		text-align: right;
	}
	.msg a {
		text-decoration: none;
	}
</style>
</head>
<body>
	<div class="art">
		<h1 id="title">${blog.title }</h1>
		<p class="msg"><fmt:formatDate value="${blog.releaseDate }" type="both" dateStyle="default" timeStyle="default"/></p>
		<p class="msg"><a href="javascript:updatePraise()">点赞</a>（<i id="praise">${blog.clickPraise }</i>）</p>
		<hr>
		<div id="content">${blog.content }</div>
		<!-- 评论以及评论的显示 -->
		<hr>
		<h4>对本文有什么好的建议就提一下吧-！</h4>
		<script id="editor" name="content" type="text/plain" style="width: 95%; height: 200px;">
			请写上你想说的话。。。
		</script>
		<a href="javascript:submitData()" class="easyui-linkbutton" 
			data-options="iconCls:'icon-submit'">发表评论</a>
		<hr>
		<p>评论总数：${total }</p>
		<h2>小伙伴的留言：</h2>
		<c:forEach items="${comment }" var="comment">
			<table>
				<tr>
					<td rowspan="2" width="50px"><img alt="头像" src="${path }${comment.visitorId.photoImg}" style="width: 50px; height: 50px;"></td>
					<td rowspan="2">
						<tr>
							<td>
								<p>${comment.visitorId.nikeName }</p>
								<p id="pindate"><fmt:formatDate value="${comment.commentDate }" type="both" dateStyle="default" timeStyle="default"/></p>
							</td>
						</tr>
					</td>
				</tr>
				<tr>
					<td colspan="10"><p id="pincontext">${comment.commentContent }</p></td>
				</tr>
			</table>
			<hr>
		</c:forEach>
			<div id="pinlunadd">
				<p id="artcileadd"> 
				
					<p id="pindateadd"></p>
				</p>
				<p id="pincontextadd"></p>
				<hr>
			</div>
		
	</div>
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var ue = UE.getEditor('editor', {
			autoHeight: false,
			toolbars: [['undo', 'redo', 'bold', 'indent', 'italic', 'underline', 'forecolor', 'strikethrough',
				'selectall', 'horizontal', '|', 'time', 'date', '|', 'insertcode', 'fontfamily', 'fontsize'],
				['cleardoc', 'justifyleft', 'justifyright', 'justifycenter', 'justifyjustify', '|',
					'simpleupload', 'emotion', '']]
		});
	</script>
	<script type="text/javascript">
	
	// 提交评论
	function submitData() {
		var time = new Date();
		var pincontent = ue.getContent();
		var blogid = ${blog.id};
		var index = 1;
		$.post(
			'${path}/comment/add/'+ index + '/' +blogid,
			{
				'content' : pincontent
			},
			function(result) {
				if(result.flag) {
					alert("插入OK")
					$('#pindateadd').html(time.toLocaleString());
					$('#pincontextadd').html(pincontent);
				}
			}, 'json');
	}
	
	// 更新点赞数
	function updatePraise() {
		var praiseNum = ${blog.clickPraise};
		var blogid = ${blog.id};
		$.post(
			'${path}/comment/updatepraise/' + blogid,
			{
				'praise' : praiseNum
			},
			function(result) {
				if(result.flag) {
					$('#praise').html(praiseNum + 1);
					// 警用 <a> 标签的 onclick 属性
					$(this).removeAttr('onclick');
				}
			}, 'json');
	}
	</script>
</body>
</html>