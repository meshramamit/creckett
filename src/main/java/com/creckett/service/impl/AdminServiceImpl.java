package com.creckett.service.impl;

import java.math.BigInteger;

import com.creckett.dao.AdminDAO;
import com.creckett.model.Admin;
import com.creckett.service.AdminService;

public class AdminServiceImpl implements AdminService {

	AdminDAO adminDAO;

	@Override
	public int validateAdminUser(String username, String password) {
		if (!username.isEmpty() && !password.isEmpty()) {
			Admin admin = adminDAO.getAdminUserByUsername(username);
			if (admin != null) {
				String passwordHex = String.format("%x", new BigInteger(1,
						password.getBytes()));
				return passwordHex.equalsIgnoreCase(admin.getPassword()) ? 1
						: 0;
			}
		}
		return 0;
	}

	@Override
	public int addNewAdmin(String username, String password) {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		return adminDAO.save(admin);
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
}
