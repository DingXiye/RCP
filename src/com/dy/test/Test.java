package com.dy.test;

import java.util.ArrayList;
import java.util.List;
/**
 * this���÷�
 * 1.this��ʾ���ǳ�Ա���������Ǿֲ�����
 * 2.���Լ�������������ʱ������ʹ��this
 * 3.�����ڲ����е�this��ʾ�����ڲ�����������ڲ��౾��
 * 
 * @author dingye
 *
 */
public class Test {
	private String s="hello";
	
	public Test(String s){
		System.out.println("s="+s);
		System.out.println("this.s="+this.s);//��ʾ�ĳ�Ա����
		this.s=s;//����Ա�����ı������ֵ
		System.out.println("ʹ��this.s��ֵ���sֵ"+s);
	}
	
	public static void main(String args[]){
		Test test=new Test("test");
		System.out.println("�ı���s="+test.s);
	}
	
}
