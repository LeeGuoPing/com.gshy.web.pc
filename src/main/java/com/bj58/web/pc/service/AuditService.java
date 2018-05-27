package com.bj58.web.pc.service;

import com.gshy.web.service.bll.AdvanceMoneyBLL;
import com.gshy.web.service.bll.MortgageBLL;
import com.gshy.web.service.entity.AuditInterface;

public class AuditService {
	
	private static final MortgageBLL mortgageBLL = new MortgageBLL();
	
	private static final AdvanceMoneyBLL advanceMoneyBLL = new AdvanceMoneyBLL();

	public AuditInterface getAudit(int type, long id) throws Exception {
		if(type==1){
			return mortgageBLL.getById(id);
		}
		if(type==2){
			return advanceMoneyBLL.getById(id);
		}
		return null;
	}

	public void pass(int type, long id,long auditEmp) throws Exception {
		if(type==1){
			mortgageBLL.pass(id,auditEmp);
		}else if(type==2){
			advanceMoneyBLL.pass(id,auditEmp);
		}
		
	}

	public void fail(int type, long id,long auditEmp) throws Exception {
		if(type==1){
			mortgageBLL.fail(id,auditEmp);
		}else if(type==2){
			advanceMoneyBLL.fail(id,auditEmp);
		}
		
	}
	
}
