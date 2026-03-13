package com.darchTech.co.mapper.auth;
import com.darchTech.co.constants.MessageConstants;
import com.darchTech.co.dto.auth.RegisterAdminRequestDTO;
import com.darchTech.co.dto.auth.RegisterAdminResponseDTO;
import com.darchTech.co.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toEntity(RegisterAdminRequestDTO request);

    @Mapping(target = "message", constant = MessageConstants.ADMIN_REGISTERED)
    RegisterAdminResponseDTO toResponse(Admin admin);
}
