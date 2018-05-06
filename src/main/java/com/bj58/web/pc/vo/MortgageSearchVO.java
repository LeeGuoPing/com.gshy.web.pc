package com.bj58.web.pc.vo;

import java.util.HashMap;
import java.util.Map;

import com.gshy.web.service.query.MortgageQuery;
import com.gshy.web.service.query.MortgageQuery.MortgageQueryBuilder;

public class MortgageSearchVO {
	
	private int auditState;
	
	private int page;
	
	private int pageSize;

	public int getAuditState() {
		return auditState;
	}

	public void setAuditState(int auditState) {
		this.auditState = auditState;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public MortgageQuery query() {
		if(auditState==0){
			auditState =1;
		}
		return countQuery(auditState);
	}
	
	public MortgageQuery countQuery(int auditState){
		MortgageQueryBuilder builder = MortgageQueryBuilder.builder();
		if(auditState>0){
			builder.addAuditState(auditState);
		}
		if(page<=0){
			page = 1;
		}
		if(pageSize<=0){
			pageSize=20;
		}
		builder.setPage(page);
		builder.setPageSize(pageSize);
		return builder.build();
	}

	public Map<String, Object> paramMap() {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("auditState", auditState);
		return paramMap;
	}
	
	
}
