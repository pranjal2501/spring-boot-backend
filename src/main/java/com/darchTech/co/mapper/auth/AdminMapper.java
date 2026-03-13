package com.darchTech.co.mapper.auth;

import com.darchTech.co.constants.MessageConstants;
import com.darchTech.co.dto.auth.RegisterAdminRequestDTO;
import com.darchTech.co.dto.auth.RegisterAdminResponseDTO;
import com.darchTech.co.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    // MapStruct automatically maps name -> name, email -> email, etc.
    // We only need to ignore audit fields.
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    Admin toEntity(RegisterAdminRequestDTO request);

    @Mapping(target = "message", constant = MessageConstants.ADMIN_REGISTERED)
    RegisterAdminResponseDTO toResponse(Admin admin);
}