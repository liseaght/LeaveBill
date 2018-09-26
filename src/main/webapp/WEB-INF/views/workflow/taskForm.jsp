<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/js/commons.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假任务办理</title>
</head>
<body>
 	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom"><span class="STYLE1">请假申请的任务办理</span></td>
		              </tr>
		            </table></td>
		            <td><div align="right"><span class="STYLE1">
		              </span></div></td>
		          </tr>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		  	<td>
		  		<form action="/WebActiviti/leaveBill_task_ complete.do" method="POST">
			  		<div align="left" class="STYLE21">
				 		请假天数:<input type="text" name="days" value="${leaveBillTask.days }" disabled="true" Style="width: 200px;"/><br/>
				 		请假原因:<input type="text" name="content" value="${leaveBillTask.content }" disabled="true" Style="width: 800px;"/><br/>
				 		请假备注:<textarea name="remark" disabled="true" cols="30" rows="2">${leaveBillTask.remark }</textarea><br/>
				 		批&emsp;&emsp;注:<textarea name="comment" cols="50" rows="5"></textarea><br/>
				 		<!-- 使用连线的名称作为按钮 -->
				 		
				 		<c:if test="${fn:length(sessionScope.outComeList)!=0}">
			      			<c:forEach items="${sessionScope.outComeList}" var="out">
					 			
					 			<input type="submit" name="outcome" value="${out }" class="button_ok" Style="margin-top:10px;height:25px"/>
					 		</c:forEach>
				 		</c:if>	
				 		
			 		</div>
			 	</form>
		  	</td>
		  </tr>
	</table>
	<hr>
	<br>	
	<c:if test="${fn:length(sessionScope.commentList)!=0}">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
			    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
			                <td width="94%" valign="bottom"><span class="STYLE1">显示请假申请的批注信息</span></td>
			              </tr>
			            </table></td>
			            <td><div align="right"><span class="STYLE1">
			              </span></div></td>
			          </tr>
			        </table></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr>
			    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
			      <tr>
			        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">时间</span></div></td>
			        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">批注人</span></div></td>
			        <td width="75%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">批注信息</span></div></td>
			      </tr>
			      <c:forEach items="${sessionScope.commentList}" var="comment">
			      	<tr>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center">${comment.time }</div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${comment.userId }</div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${comment.fullMessage }</div></td>
				    </tr> 
			      </c:forEach>
			        
			      
			    </table></td>
			  </tr>
		</table>
	</c:if>
	<c:if test="${fn:length(sessionScope.commentList)==0}">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
			    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td height="24" bgcolor="#F7F7F7"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
			                <td width="94%" valign="bottom"><span><b>暂时没有批注信息</b></span></td>
			              </tr>
			            </table></td>
			          </tr>
			        </table></td>
			      </tr>
			    </table></td>
			  </tr>
		</table>
	</c:if>
	
		<%--  --%>
</body>
</html>