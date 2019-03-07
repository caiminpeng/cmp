package com.cmp.controller;


import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.model.Area;
import com.cmp.service.AreaService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/sys")
public class AreaController {
	@Autowired
	 private AreaService areaService;
	@RequestMapping("/getProvince")
	public String getAll(Model model){
		List<Area> province=areaService.getProvince();
		model.addAttribute("province", province);
		System.out.println(province.size());
		return "/add";
	}
	@RequestMapping(value="/getCity")
	@ResponseBody
	 public String selectAllCity(HttpServletRequest request,HttpServletResponse response,
			   @RequestParam("pid") String pid) {
		System.out.println(pid);
			   List<Area> list=null;
			   Area area=new Area();
			   area.setParent_code(pid);
			   try {
					  list=areaService.getCity(area);
				} catch (Exception e) {
					e.printStackTrace();
				}
			   System.out.println(list.get(0).getName());
			 //将note对象集合转成json数组（静态方法）
				JSONArray json = JSONArray.fromObject(list);
			
				String json_str = json.toString();
				response.setContentType("text/html;charset=UTF-8");
				System.out.println(json_str);
				return json_str;
			   
		}
	@RequestMapping(value="/getCounty")
	@ResponseBody
	 public String selectAllCounty(HttpServletRequest request,HttpServletResponse response,
			   @RequestParam("pid") String pid) {
		System.out.println(pid);
			   List<Area> list=null;
			   Area area=new Area();
			   area.setParent_code(pid);
			   try {
					  list=areaService.getCounty(area);
				} catch (Exception e) {
					e.printStackTrace();
				}
			   System.out.println(list.get(0).getName());
			 //将note对象集合转成json数组（静态方法）
				JSONArray json = JSONArray.fromObject(list);
			
				String json_str = json.toString();
				response.setContentType("text/html;charset=UTF-8");
				System.out.println(json_str);
				return json_str;
			   
		}
	@RequestMapping(value="/getProvince1")
	@ResponseBody
	 public String selectAllProvince(HttpServletRequest request,HttpServletResponse response) {

			 List<Area> list=null;
			   try {
					  list=areaService.getProvince();
				} catch (Exception e) {
					e.printStackTrace();
				}
			  
			 //将note对象集合转成json数组（静态方法）
				JSONArray json = JSONArray.fromObject(list);
			
				String json_str = json.toString();
				response.setContentType("text/html;charset=UTF-8");
				System.out.println(json_str);
				return json_str;
			   
		}
	
}
