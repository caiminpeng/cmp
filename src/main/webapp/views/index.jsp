<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>登陆</title>
<script type="text/javascript" src="<c:url value='/views/jquery-1.11.1.min.js'/>"></script>
</head>
<body>
<form >
   		姓名：<input type="text" name="user_name" id="name">
   		电话：<input type="text" name="tele_phone" id="tele">
   		区域： <select name="province" id="pro" onchange="getProvince()">
		<option>选择省</option>
		<c:forEach items="${province}" var="pro" >
			<option>选择市</option>
		</c:forEach>
		</select>
		 <select name="city" id="city">
		<option>选择市</option>
		</select>
		 <select name="county" id="county">
		<option>选择县</option>
		</select><br>
   		<input type="reset" name="清空"> 
   		<input type="button" value="查询" id="select">
   		<a href="<c:url value="/sys/getProvince"/>"><input type="button" value="添加"></a>
   		<a href="<c:url value="/sys/export"/>"><input type="button" value="导出"></a>
</form>
	<div id="table">
	<table  border="1" id="table">
		<thead>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>创建时间</th>
			<th>省</th>
			<th>市</th>
			<th>区/县</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
	     <c:if test="${!empty users}">
	         <c:forEach items="${users}" var="user">
	        	 	<tr id="message">
	                    <td>${user.id }</td>
	                    <td>${user.user_name }</td>
	                    <td><c:if test="${user.sex==1}">男</c:if><c:if test="${user.sex==0}">女</c:if></td>
	                    <td>${user.tele_phone }</td>
	                    <td>${user.mail}</td>
	                    <td><fmt:formatDate value="${user.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                    <td>${user.province_name}</td>
	                    <td>${user.city_name }</td>
	                    <td>${user.county_name }</td>
	                    <td><a href="<c:url value="/sys/selectBycode?id=${user.id }"/>">编辑</a>
	                    <a href="javascript:if(confirm('您确定要删除吗?'))location='<c:url value="/sys/delete?id=${user.id }"/>'">删除</a></td>
	                </tr>
	        </c:forEach>
	    </c:if>
	    </tbody>
    </table>
    </div>
   <script type="text/javascript">

   $(function(){
	  
	   $.ajax({
	        type:"POST",
	        cache:false,
	        url:"<c:url value="/sys/getProvince1"/>",
	        dataType:"json",
	        success:function(data){
	        	if(data!=null){
					 

					$("#city").html('<option value="">选择市</option>');
					$("#county").html('<option value="">选择县</option>');
	        	 $(data).each(function(index){
						$("#pro").append(
								'<option value="'+data[index].code+'">'+data[index].name+'</option>'
		                     );
						  });
	        	}
	        }
	    });
	    
	   $("#pro").change(function(){ 
	        var pid=$(this).val();
	        
	        $.ajax({
	        type:"POST",
	        cache:false,
	        url:"<c:url value="/sys/getCity"/>",
	        data:{"pid":pid},
	        dataType:"json",
	        success : function(data){
				if(data!=null){
					 

					$("#city").html('<option value="">选择市</option>');
					 $(data).each(function(index){
					$("#city").append(
							'<option value="'+data[index].code+'">'+data[index].name+'</option>'
	                     );
					  });
					
				}
			},
			error:function(data){
				alert("查询城市失败了！");
			 }
	
	        });
	    });
	
					 $("#city").change(function(){ 
					        var pad=$(this).val();
					        
					        $.ajax({
					        type:"POST",
					        cache:false,
					        url:"<c:url value="/sys/getCounty"/>",
					        data:{"pid":pad},
					        dataType:"json",
					        success : function(data){
								if(data!=null){
									$("#county").html('<option value="">选择县</option>');
									 $(data).each(function(index){
									$("#county").append(
											'<option value="'+data[index].code+'">'+data[index].name+'</option>'
					                     );
									  });
								}
							},
							error:function(data){
								alert("查询城市失败了！");
							 }
					
					        });
					    });
					 $("#select").click(function(){
						 var name=$("#name").val();
						 var tele=$("#tele").val();
						 var province=$("#pro").val();
						 var city=$("#city").val();
						 var county=$("#county").val();
						 
						 $.ajax({
							 type:"post",
						     cache:false,
							 url:"<c:url value="/sys/select"/>",
							 data:{"user_name":name,"tele_phone":tele,"province":province,"city":city,"county":county},
							 dataType:"json",
							 success : function(data){
								 
								 
								
						
								 
								 
								 if(data!=null){
									 
									 $("#table tbody").remove();
									 $(data).each(function(index){
										 var sex="";
									var d="<c:url value='/sys/delete?id="+data[index].id+"'/>";
										var c=" <a href=\"javascript:if(confirm('您确定要删除吗?'))location='"+d+"'\">删除</a>";
										 var a="<c:url value='/sys/selectBycode?id="+data[index].id+"'/>";
										var b="<a href="+a+">编辑</a>";
									var html="<tr>";
									html+="<td>"+data[index].id+"</td>";
									html+="<td>"+data[index].user_name+"</td>";
									if(data[index].sex==0){
										sex="女";
									}else{
										sex="男";
									}
									html+="<td>"+sex+"</td>";
									html+="<td>"+data[index].tele_phone+"</td>";
									html+="<td>"+data[index].mail+"</td>";
									var s=data[index].create_time.seconds;
									var sc="";
									if(s<10){
										sc="0"+s;
									}else{
										sc=s;
									}
									var h=data[index].create_time.hours;
									if(h<10){
										h="0"+h;
									}
									var m=data[index].create_time.minutes;
									if(m<10){
										m="0"+m;
									}
									var mo=data[index].create_time.month+1;
									if(mo<10){
										mo="0"+mo;
									}
									var day=data[index].create_time.date;
									if(day<10){
										day="0"+day;
									}
									html+="<td>"+(data[index].create_time.year+1900)+"-"+mo+"-"+day+" "+h+":"+m+":"+sc+"</td>";
									html+="<td>"+data[index].province_name+"</td>";
									html+="<td>"+data[index].city_name+"</td>";
									html+="<td>"+data[index].county_name+"</td>";
									html+="<td>"+b+c+"</td>";
									
									html+="</tr>"
									$("table").append(html);
									  });
								} 
								
							}
						 });
					
					 });
	
   })

</script>
</body>
</html>

