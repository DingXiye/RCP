package com.dy.test;

import java.util.ArrayList;
import java.util.List;
/**
 * this的用法
 * 1.this表示的是成员变量而不是局部变量
 * 2.把自己当做参数传递时，可以使用this
 * 3.匿名内部类中的this表示的是内部类或者匿名内部类本身
 * 
 * @author dingye
 *
 */
public class Test {
	private String s="hello";
	
	public Test(String s){
		System.out.println("s="+s);
		System.out.println("this.s="+this.s);//表示的成员变量
		this.s=s;//将成员变量改变成输入值
		System.out.println("使用this.s赋值后的s值"+s);
	}
	
	public static void main(String args[]){
		Test test=new Test("test");
		System.out.println("改变后的s="+test.s);
	}
	
}
