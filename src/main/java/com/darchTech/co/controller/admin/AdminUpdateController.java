package com.darchTech.co.controller.admin;

import com.darchTech.co.dto.AdminUpdateDTO;
import com.darchTech.co.entity.Admin;
import com.darchTech.co.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/update")
public class AdminUpdateController {

    @Autowired
    private AdminService adminService;

    @PutMapping("/")
    public ResponseEntity<Admin> updateAdmin(@RequestBody AdminUpdateDTO updatedAdmin){
            return new ResponseEntity<>(adminService.updateAdmin(updatedAdmin),HttpStatus.OK);
    }
}
