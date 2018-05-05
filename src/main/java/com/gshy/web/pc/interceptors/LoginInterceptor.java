package com.gshy.web.pc.interceptors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.BeatContext;
import com.bj58.wf.mvc.interceptor.ActionInterceptor;
import com.bj58.ycs.tool.webutil.actionresult.ActionResult4Javascript;


public class LoginInterceptor implements ActionInterceptor {
	private Log log = LogFactory.getLog(LoginInterceptor.class);

	
	@Override
	public ActionResult preExecute(BeatContext beat) {
		try {
			
			
		} catch (Exception e) {
			this.log.error("update frist ssoinfo : " + e);
		}
		return new ActionResult4Javascript("alert(\"无法判断的情形！\");history.go(-1);");
	}


	

}