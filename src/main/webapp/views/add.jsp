<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>添加</title>
<script type="text/javascript" src="<c:url value='/views/jquery-1.11.1.min.js'/>"></script>
 <script type="text/javascript">

   $(function(){
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
	 $.ajax({
	        type:"POST",
	        cache:false,
	        url:"<c:url value="/sys/getRole"/>",
	        dataType:"json",
	        success:function(data){
	        	if(data!=null){
	        		$("#role").html('<option value="">选择角色</option>');
	        	 $(data).each(function(index){
						$("#role").append(
								'<option value="'+data[index].role_code+'">'+data[index].role_name+'</option>'
		                     );
						  });
	        	}
	        }
	    });
})

</script>
</head>
<body>
    <form action="<c:url value="/sys/add"/>" method="post">
    	角色：<select name="role_code" id="role">
    	</select><br>
    	账号： <input type="text" name="login_name" id="hh"> <br /> 
		姓名： <input type="text" name="user_name"> <br /> 
		性别: <input type="radio" name="sex" value="1" checked="checked">男
		<input type="radio" name="sex" value="0">女<br>
		电话： <input type="text" name="tele_phone"><br>
		区域： <select name="province" id="pro" onchange="getProvince()">
		<option>选择省</option>
		<c:forEach items="${province}" var="pro" >
			<option value="${pro.code}" >${pro.name}</option>
		</c:forEach>
		</select>
		 <select name="city" id="city">
		<option>选择市</option>
		</select>
		 <select name="county" id="county">
		<option>选择县</option>
		</select><br>
		邮箱：<input type="email" name="mail"><br>
		工号：<input type="text" name="job_num"><br>
    	<input type="submit"value="保存用户"> 
    	<input type="reset" value="取消">
    </form>
  
</body>
</html>