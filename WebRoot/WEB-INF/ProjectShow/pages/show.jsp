<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>项目展示</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="${resourceUrl}/js/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${resourceUrl}/css/index.css">
	<script>
		var fyToolUrl = '${fyToolUrl}';//提交表单请求url
		var fyForwardUrl = '${fyForwardUrl}';//跳转的url
		var resourceUrl = '${resourceUrl}';//资源url
		var userAccount = '${userAccount}';//当前用户
		var bvid = '${bViewID}';
	</script>
</head>
<body>
	<header>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${fyForwardUrl}&forwardPage=main.jsp">
                        <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                        <span>项目展示</span>
                    </a>
                </div>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="输入项目名称搜索..."/>
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
        </nav>
    </header>

    <section>
            <div class="model_quarter">
                <div class="panel-group" role="tablist">
                    <div class="panel panel-warning">
                        <div class="panel-heading" role="tab" >
                            <h4 class="panel-title" id="0_title">
                            </h4>
                        </div>
                        <div id="" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="" aria-expanded="true">
                            <ul class="list-group" id="0_panel_group">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="model_quarter">
                <div class="panel-group" role="tablist">
                    <div class="panel panel-info">
                        <div class="panel-heading" role="tab" id="">
                            <h4 class="panel-title" id="1_title">
                            </h4>
                        </div>
                        <div id="" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="" aria-expanded="true">
                            <ul class="list-group" id="1_panel_group">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="model_quarter">
                    <div class="panel-group" role="tablist">
                    <div class="panel panel-success">
                        <div class="panel-heading" role="tab" id="">
                            <h4 class="panel-title" id="2_title">
                            </h4>
                        </div>
                        <div id="" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="" aria-expanded="true">
                            <ul class="list-group" id="2_panel_group">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="model_quarter">
                    <div class="panel-group" role="tablist">
                    <div class="panel panel-danger">
                        <div class="panel-heading" role="tab" id="">
                            <h4 class="panel-title" id="3_title">
                            </h4>
                        </div>
                        <div id="" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="" aria-expanded="true">
                            <ul class="list-group" id="3_panel_group">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

	</section>

	<footer >飞元科技信息@FY</footer>

<script src="${resourceUrl}/js/jquery-1.10.2.min.js"></script>
<script src="${resourceUrl}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${resourceUrl}/js/index.js"></script>
<script src="${resourceUrl}/js/data.js"></script>
<script src="${resourceUrl}/js/view.js"></script>

</body>
</html>