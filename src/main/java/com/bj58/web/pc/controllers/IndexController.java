package com.bj58.web.pc.controllers;

import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.annotation.Path;


public class IndexController extends BaseController{
	
	@Path("/index")
	public ActionResult index(){
		return ActionResult.view("/index");
	}
	
	@Path("/home")
	public ActionResult home(){
		return ActionResult.view("/home");
	}
}
