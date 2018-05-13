package com.bj58.web.pc.controllers;

import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.annotation.Path;
import com.gshy.web.service.interceptors.Login;

@Login
public class IndexController extends BaseController{
	
	@Path("/")
	public ActionResult index(){
		return ActionResult.view("/index");
	}
}
