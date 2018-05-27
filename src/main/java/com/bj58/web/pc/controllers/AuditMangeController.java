package com.bj58.web.pc.controllers;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.bj58.web.pc.vo.AdvanceMoneySearchVO;
import com.bj58.web.pc.vo.MortgageSearchVO;
import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.BeatContext.Model;
import com.bj58.wf.mvc.annotation.GET;
import com.bj58.wf.mvc.annotation.POST;
import com.bj58.wf.mvc.annotation.Path;
import com.bj58.ycs.tool.webutil.actionresult.ActionResult4JSON;
import com.bj58.ycs.tool.webutil.tools.PageTool;
import com.bj58.ycs.tool.webutil.tools.ParamHelper;
import com.bj58.ycs.tool.webutil.tools.VOInitHelper;
import com.gshy.web.service.entity.AdvanceMoney;
import com.gshy.web.service.entity.AuditInterface;
import com.gshy.web.service.entity.Image;
import com.gshy.web.service.entity.Mortgage;
import com.gshy.web.service.enums.AuditStatusEnum;
import com.gshy.web.service.interceptors.Login;
import com.gshy.web.service.query.AdvanceMoneyQuery;
import com.gshy.web.service.query.MortgageQuery;
import com.gshy.web.service.utils.SecurityUtils;

@Login
@Path("/audit")
public class AuditMangeController extends BaseController{
	
	/**
	 * 房抵资料报送
	 */
	@Path("/mortgage/list")
	public ActionResult mortgageList(){
		try {
			MortgageSearchVO vo = VOInitHelper.initVO(beat, MortgageSearchVO.class);
			log.info("vo is {}" + JSON.toJSONString(vo));
			MortgageQuery query = vo.query();
			log.info("query is {}" + JSON.toJSONString(query));
			MortgageQuery waitQuery = vo.countQuery(AuditStatusEnum.AuditWait.getValue());
			MortgageQuery successQuery = vo.countQuery(AuditStatusEnum.AuditSuccess.getValue());
			MortgageQuery failQuery = vo.countQuery(AuditStatusEnum.AuditFail.getValue());
			
			List<Mortgage> mortgages = mortgageBLL.getByQuery(query);
			int count = mortgageBLL.getCount(query);
			
			int waitCount = mortgageBLL.getCount(waitQuery);
			int successCount = mortgageBLL.getCount(successQuery);
			int failcount = mortgageBLL.getCount(failQuery);
			
			log.info("待审核的数量:" + waitCount + "审核通过的数量：" + successCount + "审核失败数量" + failcount);
			Model model = beat.getModel();
			commonTools(beat);
			model.add("mortgages",mortgages);
			model.add("count", count);
			model.add("vo",vo);
			model.add("waitCount",waitCount);
			model.add("successCount",successCount);
			model.add("failcount",failcount);
			model.add("employeeMap",getAllEmployee());
						
			model.add("pageTool", PageTool.getInstance().page2("/admin/audit/mortgage/list", query.getPage(),
					query.getPageSize(), count, vo.paramMap()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ActionResult.view("/house");
	}
	
	/**
	 * 垫资
	 */
	@Path("/advance/list")
	@GET
	public ActionResult advanceList(){
		try {
			AdvanceMoneySearchVO vo = VOInitHelper.initVO(beat, AdvanceMoneySearchVO.class);
			log.info("vo is {}" + JSON.toJSONString(vo));
			AdvanceMoneyQuery query = vo.query();
			log.info("query is {}" + JSON.toJSONString(query));
			AdvanceMoneyQuery waitQuery = vo.countQuery(AuditStatusEnum.AuditWait.getValue());
			AdvanceMoneyQuery successQuery = vo.countQuery(AuditStatusEnum.AuditSuccess.getValue());
			AdvanceMoneyQuery failQuery = vo.countQuery(AuditStatusEnum.AuditFail.getValue());
			
			List<AdvanceMoney> advanceMoneys = advanceMoneyBLL.getByQuery(query);
			int count = advanceMoneyBLL.getCount(query);
			
			int waitCount = advanceMoneyBLL.getCount(waitQuery);
			int successCount = advanceMoneyBLL.getCount(successQuery);
			int failcount = advanceMoneyBLL.getCount(failQuery);
			
			log.info("待审核的数量:" + waitCount + "审核通过的数量：" + successCount + "审核失败数量" + failcount);
			Model model = beat.getModel();
			commonTools(beat);
			model.add("advanceMoneys",advanceMoneys);
			model.add("vo", vo);
			model.add("count", count);
			model.add("waitCount",waitCount);
			model.add("successCount",successCount);
			model.add("failcount",failcount);
			model.add("employeeMap",getAllEmployee());
			model.add("pageTool", PageTool.getInstance().page2("/admin/audit/advance/list", query.getPage(),
					query.getPageSize(), count, vo.paramMap()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ActionResult.view("/money");
	}
		
	@Path("/pass")
	@POST
	public ActionResult pass(){
		try {
			int type = ParamHelper.getInt(beat, "type", 0); // 审核类型  1.房抵资料报送 2.垫资
			long id = ParamHelper.getLong(beat, "id", 0);
			long auditEmp = SecurityUtils.currentUserId(beat);
			log.info("type is " + type + "id is " + id+", auditEmp: "+auditEmp);
			if (type == 0 || id == 0) {
				return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"非法参数!\"}");
			}
			AuditInterface auditInterface = auditService.getAudit(type, id);
			if (auditInterface == null) {
				return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"该审核记录不存在!\"}");
			}
			auditService.pass(type, id,auditEmp);
			return new ActionResult4JSON("{\"ret\":\"1\",\"msg\":\"success!\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"fail!\"}");
	}
	
	@Path("/refuse")
	@POST
	public ActionResult refuse(){
		try {
			int type = ParamHelper.getInt(beat, "type", 0); // 审核类型  1.房抵资料报送 2.垫资
			long id = ParamHelper.getLong(beat, "id", 0);
			long auditEmp = SecurityUtils.currentUserId(beat);
			log.info("type is " + type + "id is " + id+",auditEmp "+auditEmp);
			if (type == 0 || id == 0) {
				return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"非法参数!\"}");
			}
			AuditInterface auditInterface = auditService.getAudit(type, id);
			if (auditInterface == null) {
				return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"该审核记录不存在!\"}");
			}
			auditService.fail(type, id,auditEmp);
			return new ActionResult4JSON("{\"ret\":\"1\",\"msg\":\"success!\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"fail!\"}");
	}
	
	@Path("/detail")
	public ActionResult detail(){
		int type = ParamHelper.getInt(beat, "type", 0); // 审核类型  1.房抵资料报送 2.垫资
		long id = ParamHelper.getLong(beat, "Id", 0);
		try {
			AuditInterface audit = auditService.getAudit(type, id);
			List<Image> images = imageBLL.getByType(type, id);
			beat.getModel().add(audit);
			beat.getModel().add(images);
			beat.getModel().add("employeeMap",getAllEmployee());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(type==1){
			return ActionResult.view("/housedetail");			
		}else{
			return ActionResult.view("/moneydetail");
		}
		
		
	}
}
