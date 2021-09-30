package com.creckett.dao;

import com.creckett.model.Admin;

public interface AdminDAO {

    public Admin getAdminUserByUsername(String username);
    
    public int save(Admin admin);
}
