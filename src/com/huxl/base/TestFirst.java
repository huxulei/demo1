package com.huxl.base;

public class TestFirst {

	public static void main(String[] args) {
		boolean flag = true;
		int i =0;
		while (flag) {
			System.out.println("therd " + i);
			
			i ++;
			if(i>=10) {
				flag = false;
			}
		}
	}
}
