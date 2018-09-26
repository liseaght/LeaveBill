<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前流程图</title>
</head>
<body>
<!-- 1.获取到规则流程图 -->
<img style="position: absolute;top: 0px;left: 0px;" src="/WebActiviti/workflow_getPic.do?deploymentId=${sessionScope.dpid }&diagramResourceName=${sessionScope.drn }">

<!-- 2.根据当前活动的坐标，动态绘制DIV -->
<div style="position: absolute;border:1px solid red;top:${sessionScope.y }px;left: ${sessionScope.x }px;width: ${sessionScope.width }px;height:${sessionScope.height }px;   "></div></body>
</html>