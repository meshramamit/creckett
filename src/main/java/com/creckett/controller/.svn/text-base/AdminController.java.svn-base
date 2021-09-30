package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.creckett.service.AdminService;

public class AdminController extends MultiActionController {

	AdminService adminService;

	public ModelAndView loginAdmin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getParameter("username") != null
				&& request.getParameter("password") != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int status = adminService.validateAdminUser(username, password);
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("status", status);
			if (status == 1) {
				request.getSession().setAttribute("isAdmin", "true");
			}
			return new ModelAndView("genericStatus", model);
		} else {
			throw new IllegalArgumentException("Not valid arguments.");
		}
	}

	public ModelAndView addAdmin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getParameter("username") != null
				&& request.getParameter("password") != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int status = adminService.addNewAdmin(username, password);
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("status", status);
			if (status == 1) {
				request.getSession().setAttribute("isAdmin", "true");
			}
			return new ModelAndView("genericStatus", model);
		} else {
			throw new IllegalArgumentException("Not valid arguments.");
		}
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

}
