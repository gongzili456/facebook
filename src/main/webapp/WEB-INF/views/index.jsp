<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Facebook</title>
<link rel="shortcut icon" href="resources/img/logo.ico">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="resources/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/register.css">
<style type="text/css">
</style>
</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <i
					class="fb_logo img sp_2xp1ac sx_f72f28"></i>
				</a>
			</div>
			<div class="collapse navbar-collapse">




				<form class="navbar-form navbar-right" role="form" action="login"
					method="post">


					<div class="form-group">
						<input type="text" placeholder="邮箱" class="form-control"
							name="email">
					</div>

					<div class="form-group">

						<input type="password" placeholder="密码" class="form-control"
							name="password">
					</div>

					<button type="submit" class="btn btn-success">登录</button>
					<br>
					<div class="checkbox">
						<label> <input type="checkbox"> 保持登陆状态
						</label> <a href="#">忘记密码？</a>
					</div>
				</form>





			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">

			<div class="col-md-6">
				<div class="line_me">
					<h3>联系你我，分享生活，尽在 Facebook。</h3>
					<img alt="" src="resources/img/line_me.png">
				</div>

			</div>

			<div class="col-md-6">

				<form class="form_register" role="form">

					<h2>注册</h2>
					<h5>永久免费</h5>

					<div class="form-group">
						<input type="text" class="form-control" id="inputName" name="name"
							placeholder="姓名">
					</div>

					<div class="form-group">
						<input type="email" class="form-control" id="inputEmail"
							name="email" placeholder="你的电子邮件地址">
					</div>
					<div class="form-group">
						<input type="email" class="form-control" id="inputEmail2"
							name="email2" placeholder="再次输入电子邮件地址">
					</div>

					<div class="form-group">
						<input type="password" class="form-control" id="inputPassword"
							name="password" placeholder="新的密码">
					</div>


					<div class="form-group">
						<label>生日:</label> <select id="birthday_year">
							<option>2014</option>
							<option>2013</option>
							<option>2012</option>
							<option>2011</option>
						</select> <select id="birthday_month">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
						</select> <select id="birthday_day">
							<option value="">1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
						</select>
					</div>



					<div class="form-group">
						<span class="sex_span"><input type="radio" id="sex_woman"
							name="sex" value="1"> <label>女</label> </span> <span
							class="sex_span"><input type="radio" id="sex_man"
							name="sex" value="2"> <label>男</label> </span>
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-success btn-lg" style="min-width: 195px;">注册</button>
					</div>
				</form>
			</div>
		</div>
	</div>










	<script src="resources/js/jquery-2.0.2.min.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/js/register.js"></script>

</body>
</html>
