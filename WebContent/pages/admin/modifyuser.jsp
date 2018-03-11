<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	/**
request.getContextPath()  返回站点的根目录
request.getRealpath("/")得到的是实际的物理路径，也就是你的项目所在服务器中的路径
request.getScheme() 等到的是协议名称，默认是http
request.getServerName() 得到的是在服务器的配置文件中配置的服务器名称 比如:localhost .baidu.com 等等
request.getServerPort() 得到的是服务器的配置文件中配置的端口号 比如 8080等等
	*/
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${ORDER_SYS_NAME}-修改用户账户信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- <link rel="stylesheet" type="text/css" href="styles.css"> -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/dashboard.css">
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<script src="jquery/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	function begin() {//刷新头像
		var input = document.getElementById("inputfile").value;
		if (input != "") {
			var preview = document.getElementById("face");
			preview.src = "img/loading.gif";
        	imgform.submit();
			request();
		}
	}

	function request() {
		txtAjaxRequest(
				"state.order?messageTitle=upstate&time=" + Math.random(),
				"get", true, null, showPreview, null, null);
	} 
	//显示当前头像
	function showPreview(responseTxt, obj) {
		var preview = document.getElementById("face");
		preview.src = "img/faces/" + responseTxt;
		var path = document.getElementById("faceimgname");
		path.value = responseTxt;
	}
	var formstate = 0;
/*	function testState() {
		var btu = document.getElementById("addbtu");
		if ((formstate & 3) == 3) {
			btu.disabled = false;
		} else {
			btu.disabled = "disabled";
		}
	}*/
	function checkpass() {
		var pass = document.getElementById("userPass");
		var pass1 = document.getElementById("userPass1");
		var error = document.getElementById("passerror");
		var btu = document.getElementById("addbtu");
		if(pass.value!=""&&pass1.value!=""){
			if (pass.value == pass1.value) {
				btu.disabled = false;
				error.innerHTML="";
			} else {
				error.innerHTML = "两次密码不匹配！";
				btu.disabled = "disabled";
			}
		}
		else{
			error.innerHTML = "密码不能为空！";
			btu.disabled = "disabled";
		}
	}
	 function ajaxFileUpload(){
			$.ajaxFileUpload({ 
				type: "POST",//post提交
				url: "FileUploadServlet",
				secureuri: false, //是否需要安全协议，一般设置为false
				fileElementId: "inputfile", //文件上传域的ID
				dataType: "json", //返回值类型 一般设置为json
				success: function (data, status)  {//服务器成功响应处理函数
					if(data.message!=""){
						alert(data.message);
					}else{
						$("#face").attr("src", data.url);
                     var path = document.getElementById("faceimgname");
                     path.value = data.url;
					}
				},
				error: function (data, status, e){//服务器响应失败处理函数
				    alert(e);
				}
			});
	 }
</script>
</head>
<body style="font-family:微软雅黑;background-image: url('img/body-bg.png')">

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<img src="img/logo.png" class="navbar-brand" />
		    <span class="navbar-brand">${ORDER_SYS_NAME}</span>
		</div>
	</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron" style="background-image: url('img/loginbg.jpg');text-shadow: 0 2px 2px #000;color: white;margin-bottom:10px">
		<div class="container">
			<h1 style="color:red">
				<i class="icon-cogs icon-large"></i>&nbsp;修改员工的账户信息
			</h1>
			<p>
				${ORDER_SYS_NAME}提示：您可以在本界面修改员工<span style="color: red;font-weight: bold;">【${MODIFY_INFO.userAccount}】</span>的账户密码及头像信息！
			</p>
        <p>
            <a class="btn btn-primary btn-lg" href="pages/admin/useradmin.jsp" role="button">返回餐厅管理界面
                &raquo;</a>
        </p>
		</div>
	</div>
	<!--
	<div class="masthead">

		<nav class="navbar navbar-default" role="navigation">
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="#">Projects</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">Downloads</a></li>
			<li><a href="#">About</a></li>
			<li><a href="#">Contact</a></li>
		</ul>
		</nav>
	</div>
  -->
	<div class="container">
		<div class="row" style="padding-top: 0px">
			<div style="width: 50%;display: inline-block;">
				<div>
					<h2>您可以在此修改员工的账户信息：</h2>
					<p>请在下面的界面中修改员工的密码（管理员不需要提供现密码用于认证），员工的账号名信息由餐厅管理员指定后，不能修改</p>
					<div class="panel panel-danger">
						<div class="panel-heading">
							<i class="icon-warning-sign"></i>&nbsp;注意事项
						</div>
						<div class="panel-body">两次输入的新密码必须一致，否则系统拒绝修改用户，如果不在左边的界面中修改自定义的头像，将继续使用用户原有的头像图片。</div>
					</div>
					<p>
					<form class="form-horizontal" role="form" style="margin-top: 20px" method="post" action="AdminModifyUserServlet">
						<input type="hidden" name="userId" id="userId" value="${MODIFY_INFO.userId}" />
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">用户名:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="firstname" name="userAccount" placeholder="${MODIFY_INFO.userAccount}" disabled="disabled" value="${MODIFY_INFO.userAccount}">
							</div>
							<div style="color: red;float: right;">员工的用户名由系统管理员指定后，不能修改</div>
						</div>

						<div class="form-group">
							<label for="userPass" class="col-sm-2 control-label">新密码:</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="userPass" name="userPass" placeholder="请输入员工新密码">
							</div>
						</div>
						
						<div class="form-group">
							<label for="userPass1" class="col-sm-2 control-label">确&nbsp;&nbsp;&nbsp;&nbsp;认:</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="userPass1" name="userPass1" placeholder="请再次输入新员工的密码" onblur="checkpass()">
							</div>	
						<div style="color: red;float: right;" id="passerror"></div>
						</div>
						
						<div class="form-group">
							<label for="role" class="col-sm-2 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;色:</label>
							<div class="col-sm-10">
								<select class="form-control" name="role" id="role">
									<option value="1"
										<c:if test="${MODIFY_INFO.role==1}"> selected="selected"</c:if>>餐厅管理员</option>
									<option value="2"
										<c:if test="${MODIFY_INFO.role==2}"> selected="selected"</c:if>>厨师</option>
									<option value="3"
										<c:if test="${MODIFY_INFO.role==3}"> selected="selected"</c:if>>点餐员</option>

								</select>
							</div>
						</div>

						<input type="hidden" value="${USER_INFO.faceimg}" id="faceimgname" name="faceimg" />
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" class="btn btn-danger" disabled="disabled" id="addbtu" value="确认修改" />
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div style="width: 5%;display: inline-block;padding-top: 0px;margin-top: 0px;vertical-align: top;padding-left: 20px">
			<div style="background-color:#CCC; width:1px;height: 540px;margin-top: 0px"></div>
			</div>

			<div style="width: 40%;display: inline-block;vertical-align: top;">
				<h2>您可以为员工设置新的头像信息：</h2>
				<p style="margin-bottom: 10px">您选择的新头像图片上传成功后将在下面直接预览。</p>
				<div style="text-align: center;">
					<img src="img/faces/${MODIFY_INFO.faceimg}" id="face" width="200px" height="200px" class="img-circle" style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);" />
					<p style="margin-top: 15px">当前的头像预览</p>
					<p style="margin-top: 15px">
						更新头像图片，请选择头像文件后，点击<span style="color: red;font-weight: bold;">更新头像图片</span>按钮，上传头像后需要点击左侧
						<span style="color: red;font-weight: bold;">确定修改</span>按钮才能生效
					</p>
				</div> 
				<!--<form class="form-inline" role="form" enctype="multipart/form-data" action="" name="imgform" target="submitform" method="post">
                   <div class="form-group">    -->
						<div>
							<label class="sr-only" for="inputfile">文件输入</label>
							<input type="file" id="inputfile" name="uploadFile"/><br>
							<input type="button" class="btn btn-danger" value="更新头像图片" onclick="ajaxFileUpload()"/>
						</div>
							
              <!-- </form>  -->
			</div>
         </div>
	  </div>
	  
      <iframe src="" width="0" height="0" style="display: none" name="submitform"></iframe>
         <div style="height:1px;width: 100%;background: #CCC;margin-bottom: 10px"></div>
		<footer>
			<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
		</footer>
</body>
</html>
