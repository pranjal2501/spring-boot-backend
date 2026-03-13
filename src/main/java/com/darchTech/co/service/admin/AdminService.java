package com.darchTech.co.service.admin;

import com.darchTech.co.dto.AdminUpdateDTO;
import com.darchTech.co.entity.Admin;

public interface AdminService {
    Admin updateAdmin(AdminUpdateDTO adminUpdateDTO);
}
