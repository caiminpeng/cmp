package com.cmp.controller;


import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.model.Role;

import com.cmp.service.RoleService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/sys")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@RequestMapping(value="/getRole")
	@ResponseBody
	public String selectAll(HttpServletRequest request,HttpServletResponse response) {
		List<Role> list=roleService.getAll();
		
			 //将note对象集合转成json数组（静态方法）
				JSONArray json = JSONArray.fromObject(list);
			
				String json_str = json.toString();
				response.setContentType("text/html;charset=UTF-8");
				System.out.println(json_str);
				return json_str;
			   
		}
	
}
