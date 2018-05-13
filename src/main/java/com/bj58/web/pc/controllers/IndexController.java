package com.bj58.web.pc.controllers;

import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.annotation.Path;


public class IndexController extends BaseController{
	
	@Path("/")
	public ActionResult index(){
		return ActionResult.view("/login");
	}
	
	@Path("/home")
	public ActionResult home(){
		return ActionResult.view("/login");
	}
}
