package com.huxl.make;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Bill {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("当前是月底 请输入 0，月初请输入1");
		int type = 0;
		type = Integer.parseInt(scan.nextLine());
		
		int month = getMonth(type);
		String lastDayOfMonth = getFirstDayOfMonth(type, null);
		
		System.out.println("请输入上月剩余");
		int lastMoney = Integer.parseInt(scan.nextLine());
		
		System.out.println("请输入支付宝剩余");
		int zhifubao = Integer.parseInt(scan.nextLine());
		
		System.out.println("请输入招商剩余");
		int zhaoshang = Integer.parseInt(scan.nextLine());
		
		System.out.println("请输入微信剩余");
		int weixin = Integer.parseInt(scan.nextLine());
		
		System.out.println("请输入建行剩余");
		int jianhang = Integer.parseInt(scan.nextLine());
		
		System.out.println("请输入本月需还花呗金额");
		int huabei = Integer.parseInt(scan.nextLine());
		
		System.out.println("请输入本月工资金额");
		int salary = Integer.parseInt(scan.nextLine());
		
		System.out.println("请输入本月其他收入一");
		int income1 = Integer.parseInt(scan.nextLine());
		
		System.out.println("请输入本月其他收入二");
		int income2 = Integer.parseInt(scan.nextLine());
		
		
		//未扣除花呗剩余
		int sum = zhifubao + zhaoshang + weixin + jianhang;
		//去掉花呗剩余
		int netSum = sum - huabei;
		//消费
		int consume = lastMoney - netSum + salary + income1 + income2;
		
		
		StringBuffer resultBf = new StringBuffer();
		resultBf.append(lastDayOfMonth + "（上月）剩余：");
		resultBf.append(lastMoney);
		resultBf.append("\r\n");
		resultBf.append("\r\n");
		resultBf.append(month + "月应还花呗：").append(huabei);
		resultBf.append("\r\n");
		resultBf.append("\r\n");
		resultBf.append(month + "月剩余总额：（扣除花呗前）").append(sum);
		resultBf.append("\r\n");
		resultBf.append("支付宝：").append(zhifubao).append(" ，招商：");
		resultBf.append(zhaoshang).append(" ，微信：").append(weixin).append(" ，建行：").append(jianhang);
		resultBf.append("\r\n\r\n");
		resultBf.append("扣除花呗后剩余：").append(netSum);
		
		resultBf.append("\r\n\r\n");
		resultBf.append(lastMoney).append("（上月剩余） - ").append(netSum).append("（当前剩余） + ");
		resultBf.append(salary).append("（工资）");
		if(income1 != 0) {
			resultBf.append("+").append(income1).append("（其他1）");
		}
		if(income2 != 0) {
			resultBf.append("+").append(income2).append("（其他2）");
		}
		resultBf.append(" = ").append(consume).append("（消费）");
		
		
		resultBf.append("\r\n\r\n");
		resultBf.append(month + "月底剩余：" + netSum);
		
		System.out.println(resultBf.toString());
		outPrintFile(type, resultBf.toString());
	}
	
	public static String getFirstDayOfMonth(int type, String formatStr) {
		if(formatStr == null || formatStr.equals("")) {
			formatStr = "yyyy 年 MM 月 dd 日";
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		if(type == 0) {
			//获取当前月第一天：
			Calendar c = Calendar.getInstance();    
			c.add(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			String first = format.format(c.getTime());
			return first;
		}else {
			//获取前一个月第一天：
			Calendar c = Calendar.getInstance();    
			c.add(Calendar.MONTH, -1);
			c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			String first = format.format(c.getTime());
			return first;
		}
	}
	
	public static int getMonth(int type) {
		if(type == 0) {
			Calendar calendar = Calendar.getInstance();
			int month = calendar.get(Calendar.MONTH) + 1;
			return month;
		}else {
			Calendar calendar = Calendar.getInstance();//获取当前日期 
			calendar.add(Calendar.MONTH, -1);
			int month = calendar.get(Calendar.MONTH) + 1;
			return month;
		}
	}
	
	public static void outPrintFile(int type, String content) {
		File file = new File("E:\\java\\"+getFirstDayOfMonth(type, "yyyyMMdd")+".txt");
		FileWriter output = null;
		BufferedWriter writer = null;
		
		try {
			output = new FileWriter(file);
			writer = new BufferedWriter(output);
			writer.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(null != output){
                try {
                    output.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
		}
	}
}
