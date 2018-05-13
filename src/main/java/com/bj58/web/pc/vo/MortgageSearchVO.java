package com.bj58.web.pc.vo;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bj58.ycs.tool.webutil.query.DateRange;
import com.gshy.web.service.bll.EmployeeBLL;
import com.gshy.web.service.query.MortgageQuery;
import com.gshy.web.service.query.MortgageQuery.MortgageQueryBuilder;

public class MortgageSearchVO {
	
	EmployeeBLL employeeBLL = new EmployeeBLL();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private int auditState;
	
	private long createId;
	
	private String createTimeStart;
	
	private String createTimeEnd;
	
	private int page;
	
	private int pageSize;

	public int getAuditState() {
		return auditState;
	}

	public void setAuditState(int auditState) {
		this.auditState = auditState;
	}

	public long getCreateId() {
		return createId;
	}

	public void setCreateId(long createId) {
		this.createId = createId;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
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

	public MortgageQuery query() throws Exception {
		return countQuery(auditState);
	}
	
	public MortgageQuery countQuery(int auditState) throws Exception{
		MortgageQueryBuilder builder = MortgageQueryBuilder.builder();
		if(auditState>0){
			builder.addAuditState(auditState);
		}
		if (createId !=0) {
			builder.addCreateEmp(createId);
		}
		DateRange createTimeRange = new DateRange();
		if(StringUtils.isNotBlank(createTimeStart)){
			createTimeRange.setBegin(sdf.parse(createTimeStart+" 00:00:00"));
		}
		if(StringUtils.isNotBlank(createTimeEnd)){
			createTimeRange.setEnd(sdf.parse(createTimeEnd+" 23:59:59"));
		}
		if(page<=0){
			page = 1;
		}
		if(pageSize<=0){
			pageSize=20;
		}
		builder.setCreateTime(createTimeRange);
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
