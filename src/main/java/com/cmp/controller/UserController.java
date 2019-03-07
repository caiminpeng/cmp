package com.cmp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;

import com.alibaba.druid.stat.TableStat.Mode;
import com.cmp.model.Area;
import com.cmp.model.User;
import com.cmp.model.UtilPoi;
import com.cmp.service.AreaService;
import com.cmp.service.UserService;

import net.sf.json.JSONArray;




@Controller
@RequestMapping("/sys")
public class UserController {
	@Autowired
	 private UserService userService;
	@Autowired
	private AreaService areaService;
	 
	@RequestMapping("/login")
	 public String login(Model model,@RequestParam(value="username", required=true)String username,@RequestParam(value="password", required=true)String password){
		System.out.println(username+"  "+password);
		boolean result;
		if(username.equals(password)){
			result = true;
		}else{
			result = false;
		}
		//boolean result  = userService.login(username, password);
		 if(result){
			
			 return "redirect:/sys/showuser";
		 }else{
			 model.addAttribute("result", "用户名密码不符，登录失败！");
			 return "/hello";
		 }
		//return "df";
	 }
	  @RequestMapping("/showuser")
	    public String showPersons(Model model,HttpSession session){
	        List<User> users = userService.queryAll();
	        model.addAttribute("users", users);
	        System.out.println(users.size());
	        session.setAttribute("userss", users);
	        return "/index";
	    }
	  @RequestMapping("/add")
	  	public String add(Mode model,User user,@RequestParam(value="province", required=true)String province,
	  			@RequestParam(value="city", required=true)String city,
	  			@RequestParam(value="county", required=true)String county
	  			) throws ParseException{
		  System.out.println(county);
		  	Area area=new Area();
		  	area.setCode(county);
		  	Area a=areaService.selectBycode(area);
		  	System.out.println(a.getFull_name());
		  	String[] s = a.getFull_name().split(",");
		  	System.out.println(s[0]+s[1]+s[2]);
		  	user.setProvince_name(s[0]);
		  	user.setCity_name(s[1]);
		  	user.setCounty_name(s[2]);
		  	user.setProvince_code(province);
		  	user.setCounty_code(county);
		  	user.setCity_code(city);
		  	Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String create_time=sdf.format(date);
	        Date time=sdf.parse(create_time);
	        user.setCreate_time(time);
		  if(userService.add(user)){
			  return "redirect:/sys/showuser";
			  
		  }else{
			
			  JOptionPane.showMessageDialog(null, "添加失败！", "Error",JOptionPane.ERROR_MESSAGE); 
			  return "/add";
		  }
	  }
	  @RequestMapping(value="/select")
	  @ResponseBody
	  	public String select(HttpServletRequest request,Model model,User user,@RequestParam(value="province", required=true)String province,
	  			@RequestParam(value="city", required=true)String city,
	  			@RequestParam(value="county", required=true)String county,HttpSession session,HttpServletResponse response){
		  System.out.println(user.getUser_name());
		  user.setProvince_code(province);
		  user.setCity_code(city);
		  user.setCounty_code(county);
		  List<User> users=userService.select(user);
		  

		  model.addAttribute("users", users);
	      System.out.println(users.size());
	      session.setAttribute("userss", users);
	      
	      //将note对象集合转成json数组（静态方法）
			JSONArray json = JSONArray.fromObject(users);
		
			String json_str = json.toString();
			response.setContentType("text/html;charset=UTF-8");
			System.out.println(json_str);
			return json_str;
	       
	  }
	  @RequestMapping("/selectBycode")
	  	public String selectBycode(Model model,User user){
		  user=userService.selectBycode(user);
		  model.addAttribute("user", user);
	        
	        return "/updateuser";
	  }
	  @RequestMapping("/update")
	  	public String update(Model model,User user,
	  			@RequestParam(value="county", required=true)String county,@RequestParam(value="province", required=true)String province,
	  			@RequestParam(value="city", required=true)String city){
		  System.out.println(county);
		  	Area area=new Area();
		  	area.setCode(county);
		  	Area a=areaService.selectBycode(area);
		  	System.out.println(a.getFull_name());
		  	String[] s = a.getFull_name().split(",");
		  	System.out.println(s[0]+s[1]+s[2]);
		  	user.setProvince_name(s[0]);
		  	user.setCity_name(s[1]);
		  	user.setCounty_name(s[2]);
		  	user.setProvince_code(province);
		  	user.setCounty_code(county);
		  	user.setCity_code(city);
		  if(userService.update(user)){
			  return "redirect:/sys/showuser";
		  }else{
			  JOptionPane.showMessageDialog(null, "修改失败！", "Error",JOptionPane.ERROR_MESSAGE); 
			  return "/updateuser";
		  }
	  }
	  @RequestMapping("/delete")
	  	public String delete(Model model,User user){
		  if(userService.delete(user)){
			  return "redirect:/sys/showuser";
			  
		  }else{
			  JOptionPane.showMessageDialog(null, "删除失败！", "Error",JOptionPane.ERROR_MESSAGE); 
			  return "/index";
		  }
	  }
	  
	 @RequestMapping("/export")
	 public void export(HttpServletResponse response,HttpSession session){
		 SXSSFWorkbook workbook=null;
		 UtilPoi export=new UtilPoi();
			try {
				String excelName = "users";
				OutputStream out = response.getOutputStream();
				excelName = new String(excelName.getBytes("GBK"), "ISO8859_1");
				response.setHeader("Content-Disposition", "attachment;filename=" + excelName + ".xlsx");
				//这里flightService.selectFlightPage(1) 只是得到一个flight的集合 不用太关心
				List<User> list=(List<User>) session.getAttribute("userss");
				System.out.println(list.get(0).getUser_name());
				workbook = export.export_User(list);
				workbook.write(out);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 
	 }
}
