package com.bj58.web.pc.controllers;

import com.bj58.web.pc.service.AuditService;
import com.bj58.wf.mvc.MvcController;
import com.bj58.ycs.tool.webutil.tools.Md5Helper;
import com.gshy.web.service.bll.AdvanceMoneyBLL;
import com.gshy.web.service.bll.EmployeeBLL;
import com.gshy.web.service.bll.MortgageBLL;

public class BaseController extends MvcController{
	
	protected static final AuditService auditService = new AuditService();
	
	protected static final EmployeeBLL employeeBLL = new EmployeeBLL();
	
	protected static final MortgageBLL mortgageBLL = new MortgageBLL();
	
	protected static final AdvanceMoneyBLL advanceMoneyBLL = new AdvanceMoneyBLL();
	
	public String defaultEnc(){
		return doubleEnc("aaa123");
	}
	
	public String doubleEnc(String password){
		return Md5Helper.md5Encrypt(Md5Helper.md5Encrypt(password));
	}
}
