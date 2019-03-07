package com.cmp.model;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class UtilPoi {
	 public SXSSFWorkbook export_User(List<User> users){
		 SXSSFWorkbook workbook=new SXSSFWorkbook(100);
		 Sheet sheet=workbook.createSheet();
		 int userSize=users.size();
		 Row row=sheet.createRow(0);
		 Cell title1=row.createCell(0);
		 Cell title2 = row.createCell(1);
		 Cell title3 = row.createCell(2);
		 Cell title4 = row.createCell(3);
		 Cell title5 = row.createCell(4);
		 Cell title6 = row.createCell(5);
		 Cell title7 = row.createCell(6);
		 Cell title8 = row.createCell(7);
		 
		 
		 title1.setCellValue("序号");
		 title2.setCellValue("姓名");
		 title3.setCellValue("性别");
		 title4.setCellValue("电话");
		 title5.setCellValue("创建时间");
		 title6.setCellValue("省");
		 title7.setCellValue("市");
		 title8.setCellValue("区/县");
		 
		 for(int i=0;i<users.size();i++){
			 User user=users.get(i);
			 Row row1=sheet.createRow(i+1);
			 Cell cell1=row1.createCell(0);
			 Cell cell2=row1.createCell(1);
			 Cell cell3=row1.createCell(2);
			 Cell cell4=row1.createCell(3);
			 Cell cell5=row1.createCell(4);
			 Cell cell6=row1.createCell(5);
			 Cell cell7=row1.createCell(6);
			 Cell cell8=row1.createCell(7);
			 
			 cell1.setCellValue(user.getId());
			 cell2.setCellValue(user.getUser_name());
			 String sex="";
			 if(user.getSex()==1){
				 sex="男";
			 }else{
				 sex="女";
			 }
			 cell3.setCellValue(sex);
			 cell4.setCellValue(user.getTele_phone());
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String time=sdf.format(user.getCreate_time());
			 cell5.setCellValue(time);
			 cell6.setCellValue(user.getProvince_name());
			 cell7.setCellValue(user.getCity_name());
			 cell8.setCellValue(user.getCounty_name());
		 }
			return workbook;
	 }
}
