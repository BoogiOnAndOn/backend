package com.boogionandon.backend.service;

import com.boogionandon.backend.domain.Member;
import com.boogionandon.backend.dto.AdminUpdateDTO;
import com.boogionandon.backend.dto.BulkDeleteRequestDTO;
import com.boogionandon.backend.dto.WorkerUpdateDTO;
import com.boogionandon.backend.dto.WorkerUpdateEndDateRequestDTO;
import com.boogionandon.backend.dto.admin.AdminDetailResponseDTO;
import com.boogionandon.backend.dto.admin.WorkerDetailResponseDTO;
import com.boogionandon.backend.dto.member.AdminResponseDTO;
import com.boogionandon.backend.dto.member.WorkerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    
    WorkerResponseDTO getWorkerProfile(Long workerId);
    
    AdminResponseDTO getAdminProfile(Long adminId);
    
    Page<Member> getMemberByRegularAdmin(Long adminId, String tabCondition, String nameSearch, Pageable pageable);
    
    Page<Member> getMemberBySuperAdmin(Long adminId, String tabCondition, String nameSearch, Pageable pageable);
    
    WorkerDetailResponseDTO getWorkerById(Long memberId);
    
    AdminDetailResponseDTO getAdminById(Long adminId);
    
    void updateWorkerProfile(Long workerId, WorkerUpdateDTO workerUpdateDTO);
    
    void updateAdminProfile(Long adminId, AdminUpdateDTO adminUpdateDTO);
    
    void confirmOldPassword(Long memberId, String oldPassword);
    
    void changePassword(Long memberId, String newPassword, String newConfirmPassword);
    
    void softDeleteMembers(BulkDeleteRequestDTO requestDTO);
    
    void updateWorkerEndDate(Long workerId, WorkerUpdateEndDateRequestDTO requestDTO);
}
