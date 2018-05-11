package com.gshy.web.pc.test;

import org.junit.Test;

import com.bj58.ycs.tool.webutil.tools.Md5Helper;

public class MD5Test {
	
	@Test
	public void test01(){
		String md5Encrypt = Md5Helper.md5Encrypt(Md5Helper.md5Encrypt("123456"));
		System.out.println(md5Encrypt);
	}
}
