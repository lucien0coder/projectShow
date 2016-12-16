 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>项目管理工具-任务展示</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${resourceUrl}/js/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${resourceUrl}/css/loading.css">
	<link rel="stylesheet" type="text/css" href="${resourceUrl}/css/bulma.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${resourceUrl}/css/show.css">
</head>
	<script>
		var fyToolUrl = '${fyToolUrl}';//提交表单请求url
		var fyForwardUrl = '${fyForwardUrl}';//跳转的url
		var resourceUrl = '${resourceUrl}';//资源url
		var userAccount = '${userAccount}';//当前用户
		var bvid = '${bViewID}';
//		var accessToken ='${accessToken}'
	</script>
<body>

<nav class="nav">
  <div class="nav-left">
      <label class="nav-item is-brand">
      	<p class="title is-3">展示<span class="tag is-danger"></span>需完成任务</p>
  	  </label>
  </div>
  
  <div class="nav-center">
    <a class="nav-item" href="${fyForwardUrl}&forwardPage=show.jsp">
      <span class="icon">
        <i class="fa fa-home"></i>
      </span>
    </a>
    <a class="nav-item" id="swiftType">
      <span class="icon">
        <i class="fa fa-calendar"></i>
      </span>
    </a>
  </div>
  

  <div class="nav-right nav-menu" style="display:">
		  <a class="nav-item">
		  	<p class="control">
			  <span class="select">
			    <select id="projectCode" onchange="_goto(this)" style="width:180px;">
			    	<option value="",title="">全部</option>
			    </select>
			  </span>
			</p>
		  </a>
	 	<a class="nav-item">
		     <div class="level-item">
			     <p class="control has-addons">
			 	  	<span class="select">
					    <select id="typeCode">
				      		<option value="userName">人员</option>
				      		<option value="pName">项目</option>
				      		<option value="tName">任务</option>
				    	</select>
			  		</span>
			       <input class="input" type="text" placeholder="Find a post" id="typeCodeValue">
			       <button class="button" id="search">
			         Search
			       </button>
			     </p>
		   </div>
	   </a>
  </div>
</nav>

<section id="cd-timeline" class="cd-container"></section>

<div class="modal fade" id="select_chatroom_modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="select_chat_title">请输入需要发送的消息内容</h4>
			</div>
			<div><textarea id="theSendMessage" class="textarea"></textarea></div>
			<div class="modal-body" id="message_eara"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">不发送</button>
				<button type="button" class="btn btn-primary" id="sendMsg_btn">发送消息</button>
			</div>
		</div>
	</div>
</div>

<div id="loading">
    <div class="loadingbody">
        <div class="loadingtop"></div>
        <div class="spinner">
            <div class="bar1"></div>
            <div class="bar2"></div>
            <div class="bar3"></div>
            <div class="bar4"></div>
            <div class="bar5"></div>
            <div class="bar6"></div>
            <div class="bar7"></div>
            <div class="bar8"></div>
            <div class="bar9"></div>
            <div class="bar10"></div>
            <div class="bar11"></div>
            <div class="bar12"></div>
        </div>
        <div class="loadingbottom"></div>
    </div>
</div>

</body>
<!-- <script src="vue.js"></script> -->
<!-- <script src="es6.js"></script> -->
<script src="${resourceUrl}/js/jquery-1.10.2.min.js"></script>
<script src="${resourceUrl}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${resourceUrl}/js/index.js"></script>
<script src="${resourceUrl}/js/data.js"></script>
<script src="${resourceUrl}/js/view.js"></script>
<script type="text/javascript" src="${resourceUrl}/js/3pa.js"></script>

</html>