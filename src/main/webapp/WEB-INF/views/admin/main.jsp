<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<title>个人博客后台管理</title>
<%@include file="/common/head.jspf" %>

<style type="text/css">
    body {
        font-family: microsoft yahei;
    }
</style>
    
<script type="text/javascript">
/**
 * 打开选项卡
 * @param text  选项卡标题
 * @param url   请求打开路径
 * @param icon  选项卡图标
 */
function openTab(text,url,icon) {
    //判断当前选项卡是否存在
    if($('#tabs').tabs('exists',text)){
        //如果存在 显示
        $("#tabs").tabs("select",text);
    }else{
        //如果不存在 则新建一个
        $("#tabs").tabs('add',{
            title:text,
            closable:true,   //是否允许选项卡摺叠。
            iconCls:icon,    //显示图标
            content:"<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${blog}/admin/"+url+"'></iframe>"
            //url 远程加载所打开的url
        })
    }
}
/**
 * 打开系统监控选项卡
 */
function openSystemTab(text,url,icon) {
    //判断当前选项卡是否存在
    if($('#tabs').tabs('exists',text)){
        //如果存在 显示
        $("#tabs").tabs("select",text);
    }else{
        //如果不存在 则新建一个
        $("#tabs").tabs('add',{
            title:text,
            closable:true,   //是否允许选项卡摺叠。
            iconCls:icon,    //显示图标
            content:"<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${blog}/"+url+"'></iframe>"
            //url 远程加载所打开的url
        })
    }
}
/**
 * 打开友情链接选项卡
 */
function openFriendTab(text,url,icon) {
	//判断当前选项卡是否存在
    if($('#tabs').tabs('exists',text)){
        //如果存在 显示
        $("#tabs").tabs("select",text);
    }else{
        //如果不存在 则新建一个
        $("#tabs").tabs('add',{
            title:text,
            closable:true,   //是否允许选项卡摺叠。
            iconCls:icon,    //显示图标
            content:"<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"+url+"'></iframe>"
            //url 远程加载所打开的url
        })
	}
}

/**
 * 打开 Dialog 
 */
function openPasswordModifyDialog() {
    //打开对话框并且设置标题
    $("#dlg").dialog("open").dialog("setTitle", "修改登录密码");
}

/**
 * 提交修改 Dialog 按钮
 */
function saveModify() {
    $("#fm").form("submit",{
        url: '${blog}/admin/info/updateInfo',
        onSubmit: function() {
            return $(this).form("validate");
        }, //进行验证，通过才让提交
        success: function(result) {
            var result = eval("(" + result + ")"); //将json格式的result转换成js对象
            if(result.flag) {
                $.messager.alert("系统提示", "密码修改成功");
                $("#yuanPass").val(""); //保存成功后将内容置空
                $("#newPass").val("");
                $("#dbPass").val("");
                $("#dlg").dialog("close"); //关闭对话框
            } else {
                $.messager.alert("系统提示", "密码修改失败");
                return;
            }
        }
    });
}

/**
* Dialog 取消按钮
*/
function closeModifyDialog() {
    $("#yuanPass").val(""); //保存成功后将内容置空
    $("#newPass").val("");
    $("#dbPass").val("");
    $("#dlg").dialog("close"); //关闭对话框
}


</script>

</head>
<body class="easyui-layout">

<div region="north" style="height: 78px; 
	background-color: #E0ECFF;
	background-size: 100%, 78px;">
    <table style="padding: 0px" width="100%">
        <tr>
            <td valign="top" align="left" width="50%">
            <!-- 
                <img alt="" src="${blog}/static/images/logo.png">
             -->
             	<h1>小凡博客管理系统</h1>
            </td>
            <td valign="bottom" align="right" width="50%">
            	<font size="3">&nbsp;&nbsp;<strong>欢迎：&nbsp;&nbsp;&nbsp;</strong>${nike}</font>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<font><a href="${blog }/logout" iconCls="icon-print">退出系统</a></font>
            </td>
        </tr>
    </table>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <div title="常用操作" data-options="selected:true,iconCls:'icon-item'" style="padding: 10px">
        	<a href="/blog/index.html" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px">博客首页</a>
            <a href="javascript:openTab('写博客','blog/openWriteBlog','icon-writeblog')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px">写博客</a>
            <a href="javascript:openTab('评论审核','commentReview.jsp','icon-review')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-review'" style="width: 150px">评论审核</a>
        </div>
        <div title="博客管理" data-options="iconCls:'icon-bkgl'" style="padding:10px;">
            <a href="javascript:openTab('写博客','blog/openWriteBlog','icon-writeblog')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px;">写博客</a>
            <a href="javascript:openTab('博客信息管理','blog/openListBlog','icon-bkgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px;">博客管理</a>
            <a href="javascript:openTab('博客类别信息管理','blogType/openBlogTypeManage','icon-bklb')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-bklb'" style="width: 150px;">博客类别信息管理</a>
        </div>
        <div title="评论管理" data-options="iconCls:'icon-plgl'" style="padding:10px">
            <a href="javascript:openTab('评论信息管理','','icon-plgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-plgl'" style="width: 150px;">评论信息管理</a>
            <a href="javascript:openTab('评论审核','','icon-review')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-review'" style="width: 150px">评论审核</a>
        </div>
        <div title="上传文件管理" data-options="iconCls:'icon-bklb'" style="padding:10px">
            <a href="javascript:openTab('文件列表','list/image','icon-review')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-review'" style="width: 150px">文件列表</a>
        </div>
        <div title="个人信息管理" data-options="iconCls:'icon-grxx'" style="padding:10px">
            <a href="javascript:openTab('修改个人信息','/info/openInfo','icon-grxxxg')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-grxxxg'" style="width: 150px;">修改个人信息</a>
        </div>
        <div title="系统管理" data-options="iconCls:'icon-system'" style="padding:10px">
            <a href="javascript:openSystemTab('系统监控','druid/index.html','icon-link')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-system'" style="width: 150px">系统监控</a>
               
            <a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
           
            <a href="javascript:refreshSystemCache()" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a>
            
            <a href="javascript:openFriendTab('友情链接','jianshu.com','icon-link')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-link'" style="width: 150px">友情链接</a>
           
            <a href="${blog }/logout" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'"
               style="width: 150px;">安全退出</a>
        </div>
    </div>
</div>

<div data-options="region:'center'" style="background:#eee;">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
        	<img alt="" src="${blog}/static/userImages/timg.jpg" style="width: 100%; height: 600px;"/>
        </div>
    </div>
</div>

<!-- dialog start -->
<div id="dlg" class="easyui-dialog" style="width:500px; height:260px; padding:10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post" novalidate>
        <table cellspacing="8px">
            <tr>
                <td>输入原始密码</td>
                <td>
                    <input type="text" id="yuanPass" onblur="tipOk1()" onfocus="unTipOk1()" 
                    	name="yuanPass" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>输入新密码</td>
                <td>
                    <input type="password" id="newPass" name="newPass" class="easyui-validatebox" 
                    	required="true"/>
                </td>
            </tr>
            <tr>
                <td>再次确认输入</td>
                <td>
                    <input type="password" id="dbPass" onblur="tipOk2()" onfocus="unTipOk2()" name="dbPass" 
                    	class="easyui-validatebox" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div> <!-- dialog end -->

<!-- dialog button -->
<div id="dlg-buttons">
    <div>
        <a href="javascript:saveModify()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">确认修改</a>
        <a href="javascript:closeModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
    </div>
</div>

<div region="south" style="height: 45px;padding: 5px;line-height: 45px;" align="center">
    Copyright © 2012-2017 小凡的SSM博客系统 版权所有
</div>

<script type="text/javascript">
/**
 * 原密码 表单失去焦点 Ajax 校验密码
 */
function tipOk1() {
	var yuanpass = $('#yuanPass').val();
	$.post(
		'${blog}/admin/info/checkpass',
		{
			'yuanPass' : yuanpass
		},
		function(result) {
			if(result.flag) {
				// 校验通过，添加校验结果信息
				var img = '<img id="tipImg1" src="${blog}/static/userImages/ok.png" style="padding-left: 5px;" />'
				var tiptext = '<i id="tipText1" style=“color: #419752;”>通过验证</i>'
				$('#yuanPass').after(img, tiptext);
			} else {
				// 校验未通过，添加校验结果信息
				var img = '<img id="tipImg1" src="${blog}/static/userImages/cancel.png" style="padding-left: 5px;" />'
				var tiptext = '<i id="tipText1" style=“color: #419752;”>验证失败</i>'
				$('#yuanPass').after(img, tiptext);
			}
		}, 'json');
	
}
/**
 * 表单重新获得焦点时，清除校验信息
 */
function unTipOk1() {
	$('#tipImg1').remove();
	$('#tipText1').remove();
}
/*******************************/
// 确认密码与输入的新密码进行校验
function tipOk2() {
	var newpass = $('#newPass').val();
	var dbpass = $('#dbPass').val();
	if(newpass == dbpass) {
		var img = '<img id="tipImg2" src="${blog}/static/userImages/ok.png" style="padding-left: 5px;" />'
		var tiptext = '<i id="tipText2" style=“color: #419752;”>通过验证</i>'
		$('#dbPass').after(img, tiptext);		
	} else {
		var img = '<img id="tipImg2" src="${blog}/static/userImages/cancel.png" style="padding-left: 5px;" />'
		var tiptext = '<i id="tipText2" style=“color: #419752;”>验证失败</i>'
		$('#dbPass').after(img, tiptext);
	}
}
function unTipOk2() {
	$('#tipImg2').remove();
	$('#tipText2').remove();
}
</script>
</body>
</html>
