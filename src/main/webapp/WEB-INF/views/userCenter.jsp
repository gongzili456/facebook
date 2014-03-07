<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Facebook</title>
<link rel="shortcut icon" href="resources/img/logo.ico">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="resources/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/base.css">
<style type="text/css">
.feedBtn {
	height: 74px;
}

.portrait a {
	margin-right: 10px;
	float: left;
}

.portrait img {
	width: 40px;
	height: 40px;
}

.author {
	margin-top: -10px;
}

.img-rounded {
	border-radius: 6px;
	float: left;
	padding: 2px;
}
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


				<ul class="nav navbar-nav">
					<li class=""><a href="#">Link</a></li>
					<li><a href="#">Link</a></li>
				</ul>

				<form class="navbar-form navbar-left" role="search" action="#"
					method="post">
					<div class="input-group input-group-sm" style="width: 250px;">
						<input type="text" class="form-control" placeholder="找朋友">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</span>
					</div>
				</form>



				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">${user.name}</a></li>
					<li><a href="#">找人</a></li>
					<li><a href="#">首页</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" style="height: 50px;"><i
							class="glyphicon glyphicon-cog"></i><b></b></a>
						<ul class="dropdown-menu">
							<li><a href="#"><i class="glyphicon glyphicon-pencil"></i>编辑</a></li>
							<li><a href="#"><i class="glyphicon glyphicon-comment"></i>消息</a></li>
							<li><a href="#"><i class="glyphicon glyphicon-phone"></i>手机</a></li>
							<li class="divider"></li>
							<li><a href="logout"><i class="glyphicon glyphicon-off"></i>退出</a></li>
						</ul></li>
				</ul>


			</div>
		</div>
	</div>

	<div class="container">


		<!-- 动态发布表单区域 -->
		<div class="row" style="min-height: 800px;">

			<!-- left -->
			<div class="col-md-2">

				<ul class="media-list">
					<li class="media"><a class="pull-left" href="#"> <img
							class="media-object" src="resources/img/fb_icon_325x325.png"
							alt="头像" style="width: 40px; height: 40px;">
					</a>

						<div class="media-body">
							<h6 class="media-heading">
								<a href="#">${user.name}</a>
							</h6>
							<h6 class="media-heading">
								<a href="#">修改资料</a>
							</h6>
						</div></li>
				</ul>
				<div class="list-group">
					<a href="#" class="list-group-item active"> <span class="badge"></span>欢迎来到Facebook
					</a> <a href="#" class="list-group-item"> <span class="badge">5</span>
						动态消息
					</a> <a href="#" class="list-group-item"> <span class="badge">1</span>
						消息
					</a> <a href="#" class="list-group-item"> <span class="badge">3</span>
						私信
					</a>
				</div>
			</div>





			<!-- right -->
			<div class="col-md-10">


				<form class="form-horizontal" role="form" id="newsfeedForm">
					<div class="form-group">
						<div class="col-sm-8">
							<div class="input-group">
								<textarea class="form-control" rows="3" placeholder="你在想什么？"
									name="content" style="resize: none;"></textarea>
								<span class="input-group-btn">
									<button class="btn btn-primary feedBtn" type="submit">发布</button>
								</span>
							</div>
						</div>
					</div>
					<div class="btn-toolbar" role="toolbar" style="margin-top: -15px;">
						<div class="btn-group btn-group-sm">
							<button type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-picture"></i>
							</button>
							<button type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-music"></i>
							</button>
							<button type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-film"></i>
							</button>
						</div>
						<div class="btn-group btn-group-sm">
							<button type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-map-marker"></i>
							</button>
							<button type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-qrcode"></i>
							</button>
						</div>
					</div>
				</form>


				<!-- 好友动态 列表区域-->
				<div class="row friendNewsFeedsList">
					<!-- 此处展示列表 -->
				</div>
			</div>
		</div>
	</div>






	<script src="resources/js/jquery-2.0.2.min.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery-tmpl/jquery.tmpl.min.js"></script>
	<script src="resources/js/jquery-tmpl/jquery.tmplPlus.min.js"></script>
	<script src="resources/js/newsfeed.js"></script>



	<script type="text/x-jquery-tmpl" id="newsTmpl">
					<div class="col-md-8">
						<div class="panel panel-info">
							<div class="panel-heading">
								<div class="portrait">
									<a href="#"> <img src="${'${'}portrait}" alt="头像"></a>
								</div>
								<div class="author">
									<h4>
										<a href="#">${'${'}author}</a>
									</h4>
									<h6>发表于：${'${'}created}</h6>
								</div>
							</div>
							<div class="panel-body">
								<p>${'${'}content[1]}</p>

									{{if content[2]}}	
										{{each content[2]}}							
								<img
									src="${'${'}$value}"
									alt="..." class="img-rounded"
									style="width: 300px; height: 200px;">
										{{/each}}
									{{/if}}

							</div>
							<div class="panel-footer">
								<div class="form-group">
									<button type="button" class="btn btn-default btn-sm">
										<span class="glyphicon glyphicon-thumbs-up"></span>赞(${'${'}favourCount})
									</button>
									<button type="button" class="btn btn-default btn-sm">
										<span class="glyphicon glyphicon-comment"></span>评论(${'${'}commentCount})
									</button>
									<button type="button" class="btn btn-default btn-sm">
										<span class="glyphicon glyphicon-share-alt"></span>分享(${'${'}shareCount})
									</button>
								</div>
								<div class="input-group">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button">@</button>
									</span> <input type="text" class="form-control"> <span
										class="input-group-btn">
										<button class="btn btn-default" type="button">发表</button>
									</span>
								</div>
							</div>
						</div>
					</div>
	</script>
</body>
</html>