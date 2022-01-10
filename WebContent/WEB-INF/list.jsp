<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>


<%  //어트리뷰트의 영역에서 가져온것임.dao에서 가져오는거 아님
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList"); //리스트를 읽어오게 형변환

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>[phonebook2]</h1>
	
	<h2>전화번호 리스트</h2>
	
	<p>입력한 정보 내용입니다.</p>
	
	
	<%
	for(int i=0; i<personList.size(); i++){ 
	%>
	<table border='1'>
		<tr>
			<td>이름(name)</td>
			<td><%=personList.get(i).getName()%></td>
		</tr>
		<tr>
			<td>핸드폰(hp)</td>
			<td><%=personList.get(i).getHp()%></td>
		</tr>
		<tr>
			<td>회사(company)</td>
			<td><%=personList.get(i).getCompany() %></td>
		</tr>
	</table>
	<br>
	<%
	}
	%>
	<br>
	
	
</body>
</html>