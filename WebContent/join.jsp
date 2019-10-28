<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DBPKG.memberDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css.css">
<script>
    function checkInfo(){
        if(document.getElementById("name").value==0 ||
        document.getElementById("phone").value==0 ||
        document.getElementById("addr").value==0 ||
        document.getElementById("joinedDate").value==0 ||
        document.getElementById("grade").value==0 ||
        document.getElementById("cityCode").value==0)
        {
            alert("회원정보가 입력되지 않았습니다");
        }else{
        	alert("회원등록이 완료 되었습니다!");
        }
        document.fm.action="joinPro.jsp";
        
    }
</script>
</head>

<body>
<%
	memberDAO dao= memberDAO.getInstance();
%>
	<header>
		쇼핑몰 회원관리 ver1.0
	</header>
		
	
	<nav>
		<a href="join.jsp">회원등록</a>
		<a href="searchM.jsp">회원목록 조회/수정</a>
		<a href="searchP">회원매출조회</a>
		<a href="Index.jsp">홈으로.</a>
	</nav>
	
	<section>
		<h2 class="cc">홈쇼핑 회원등록</h2>
		<div class="content">
		<form name="fm" method="post">
	    	<table border="1">
                <tr>
                    <td>회원번호(자동발생)</td>
                    <td id="td2"><input type="text" name="no" value="<%= dao.count()%>"></td>
                </tr>
                <tr>
                    <td>회원성명</td>
                    <td id="td2"><input type="text" name="name" id="name" value=""></td>
                </tr>
                <tr>
                    <td>회원전화</td>
                    <td id="td2"><input type="text" name="tel" id="phone" value=""></td>
                </tr>
                <tr>
                    <td>회원주소</td>
                    <td id="td2"><input type="text" name="addr" id="addr" value=""></td>
                </tr>
                <tr>
                    <td>가입일자</td>
                    <td id="td2"><input type="text" name="joinedDate" id="joinedDate" value=""></td>
                </tr>
                <tr>
                    <td>고객등급</td>
                    <td id="td2"><input type="text" name="grade" id="grade" value=""></td>
                </tr>
                <tr>
                    <td>도시코드</td>
                    <td id="td2"><input type="text" name="cityCode" id="cityCode" value=""></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit" onclick="checkInfo()">등록</button>
                    	<input type="reset" value="조회" onclick="location.href='searchResult.jsp'">
                    </td>

                </tr>
            </table>
            </form>
           </div>
	</section>
	
	<footer>
		HRD KOREA
	</footer>
</body>
</html>