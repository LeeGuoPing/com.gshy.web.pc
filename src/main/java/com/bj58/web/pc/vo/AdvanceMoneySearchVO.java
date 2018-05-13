package com.bj58.web.pc.vo;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bj58.ycs.tool.webutil.query.DateRange;
import com.gshy.web.service.query.AdvanceMoneyQuery;
import com.gshy.web.service.query.AdvanceMoneyQuery.AdvanceMoneyQueryBuilder;

public class AdvanceMoneySearchVO {
	
	
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

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
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

	public AdvanceMoneyQuery query() throws Exception {
		
		return countQuery(auditState);
	}

	public AdvanceMoneyQuery countQuery(int value) throws Exception {
		AdvanceMoneyQueryBuilder builder = AdvanceMoneyQueryBuilder.builder();
		if(auditState>0){
			builder.addAuditState(auditState);
		}
		if(page<=0){
			page = 1;
		}
		if(pageSize<=0){
			pageSize=20;
		}
		if(createId !=0){
			builder.addCreateEmp(createId);
		}
		DateRange createTimeRange = new DateRange();
		if(StringUtils.isNotBlank(createTimeStart)){
			createTimeRange.setBegin(sdf.parse(createTimeStart+" 00:00:00"));
		}
		if(StringUtils.isNotBlank(createTimeEnd)){
			createTimeRange.setEnd(sdf.parse(createTimeEnd+" 23:59:59"));
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
