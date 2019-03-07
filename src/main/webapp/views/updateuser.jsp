<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>添加</title>
<script type="text/javascript" src="<c:url value='/views/jquery-1.11.1.min.js'/>"></script>
</head>
<body>
    <form action="<c:url value="/sys/update"/>" method="post">
    	<input type="hidden" name="id" value="${user.id }">
    	账号： <input type="text" name="login_name" value="${user.login_name }"> <br /> 
		姓名： <input type="text" name="user_name" value="${user.user_name }"> <br /> 
		性别: <input type="radio" name="sex" value="1" <c:if test="${user.sex==1}">checked="checked"</c:if> >男
		<input type="radio" name="sex" value="0" <c:if test="${user.sex==0}">checked="checked"</c:if> >女<br>
		电话： <input type="text" name="tele_phone" value="${user.tele_phone }"><br>
		区域： <select name="province" id="pro" >
		<option value="${user.province_code }">${user.province_name }</option>
		</select>
		 <select name="city" id="city">
		<option value="${user.city_code }">${user.city_name }</option>
		</select>
		 <select name="county" id="county">
		<option value="${user.county_code }">${user.county_name }</option>
		</select><br>
		邮箱：<input type="email" name="mail" value="${user.mail }"><br>
		工号：<input type="text" name="job_num" value="${user.job_num }"><br>
    	<input type="submit"value="保存用户"> 
    	<input type="reset" value="取消">
    </form>
   <script type="text/javascript">
   $(function(){
	    $.ajax({
	        type:"POST",
	        cache:false,
	        url:"<c:url value="/sys/getProvince1"/>",
	        dataType:"json",
	        success:function(data){
	           
	        	 $(data).each(function(index){
						$("#pro").append(
								'<option value="'+data[index].code+'">'+data[index].name+'</option>'
		                     );
						  });
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
					$("#county").html('<option value="">选择县</option>');
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
   })
   </script>
</body>
</html>