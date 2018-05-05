package com.gshy.web.pc.controllers;

import com.bj58.wf.mvc.MvcController;
import com.bj58.ycs.tool.webutil.tools.Md5Helper;
import com.gshy.web.pc.bll.EmployeeBLL;

public class BaseController extends MvcController{
	
	protected static final EmployeeBLL employeeBLL = new EmployeeBLL();
	
	public String defaultEnc(){
		return doubleEnc("aaa123");
	}
	
	public String doubleEnc(String password){
		return Md5Helper.md5Encrypt(Md5Helper.md5Encrypt(password));
	}
}
