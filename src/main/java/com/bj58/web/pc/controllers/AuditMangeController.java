package com.bj58.web.pc.controllers;

import java.util.List;

import com.bj58.web.pc.vo.AdvanceMoneySearchVO;
import com.bj58.web.pc.vo.MortgageSearchVO;
import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.BeatContext.Model;
import com.bj58.wf.mvc.annotation.Path;
import com.bj58.ycs.tool.webutil.tools.PageTool;
import com.bj58.ycs.tool.webutil.tools.VOInitHelper;
import com.gshy.web.service.entity.AdvanceMoney;
import com.gshy.web.service.entity.Mortgage;
import com.gshy.web.service.enums.AuditStatusEnum;
import com.gshy.web.service.query.AdvanceMoneyQuery;
import com.gshy.web.service.query.MortgageQuery;

@Path("/audit")
public class AuditMangeController extends BaseController{
	/**
	 * 房抵资料报送
	 */
	@Path("/mortgage/list")
	public ActionResult mortgageList(){
		try {
			MortgageSearchVO vo = VOInitHelper.initVO(beat, MortgageSearchVO.class);
			MortgageQuery query = vo.query();
			MortgageQuery waitQuery = vo.countQuery(AuditStatusEnum.AuditWait.getValue());
			MortgageQuery successQuery = vo.countQuery(AuditStatusEnum.AuditSuccess.getValue());
			MortgageQuery failQuery = vo.countQuery(AuditStatusEnum.AuditFail.getValue());
			
			List<Mortgage> mortgages = mortgageBLL.getByQuery(query);
			int count = mortgageBLL.getCount(query);
			
			int waitCount = mortgageBLL.getCount(waitQuery);
			int successCount = mortgageBLL.getCount(successQuery);
			int failcount = mortgageBLL.getCount(failQuery);
			
			System.out.println(waitCount+": "+successCount+": "+failcount);
			Model model = beat.getModel();
			model.add("mortgages",mortgages);
			model.add("count", count);
			model.add("waitCount",waitCount);
			model.add("successCount",successCount);
			model.add("failcount",failcount);
			
			model.add("pageTool", PageTool.getInstance().page2(beat.getClient().getRelativeUrl(), query.getPage(),
					query.getPageSize(), count, vo.paramMap()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 垫资
	 */
	@Path("/advance/list")
	public ActionResult advanceList(){
		try {
			AdvanceMoneySearchVO vo = VOInitHelper.initVO(beat, AdvanceMoneySearchVO.class);
			AdvanceMoneyQuery query = vo.query();
			AdvanceMoneyQuery waitQuery = vo.countQuery(AuditStatusEnum.AuditWait.getValue());
			AdvanceMoneyQuery successQuery = vo.countQuery(AuditStatusEnum.AuditSuccess.getValue());
			AdvanceMoneyQuery failQuery = vo.countQuery(AuditStatusEnum.AuditFail.getValue());
			
			List<AdvanceMoney> mortgages = advanceMoneyBLL.getByQuery(query);
			int count = advanceMoneyBLL.getCount(query);
			
			int waitCount = advanceMoneyBLL.getCount(waitQuery);
			int successCount = advanceMoneyBLL.getCount(successQuery);
			int failcount = advanceMoneyBLL.getCount(failQuery);
			
			System.out.println(waitCount+": "+successCount+": "+failcount);
			Model model = beat.getModel();
			model.add("mortgages",mortgages);
			model.add("count", count);
			model.add("waitCount",waitCount);
			model.add("successCount",successCount);
			model.add("failcount",failcount);
			
			model.add("pageTool", PageTool.getInstance().page2(beat.getClient().getRelativeUrl(), query.getPage(),
					query.getPageSize(), count, vo.paramMap()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	@Path("/pass")
	public ActionResult pass(){
		return null;
	}
	
	@Path("/refuse")
	public ActionResult refuse(){
		return null;
	}
	
	
	
}
