package com.boogionandon.backend.service;


import com.boogionandon.backend.domain.Admin;
import com.boogionandon.backend.domain.Member;
import com.boogionandon.backend.domain.Worker;
import com.boogionandon.backend.dto.AdminUpdateDTO;
import com.boogionandon.backend.dto.WorkerUpdateDTO;
import com.boogionandon.backend.dto.admin.AdminDetailResponseDTO;
import com.boogionandon.backend.dto.admin.WorkerDetailResponseDTO;
import com.boogionandon.backend.dto.admin.WorkerDetailResponseDTO.WorkerDetailResponseDTOBuilder;
import com.boogionandon.backend.dto.member.AdminResponseDTO;
import com.boogionandon.backend.dto.member.WorkerResponseDTO;
import com.boogionandon.backend.exception.CustomException;
import com.boogionandon.backend.exception.PasswordMismatchException;
import com.boogionandon.backend.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class MemberLocalServiceImpl implements MemberService {
    
    private final MemberRepository memberRepository;
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public WorkerResponseDTO getWorkerProfile(Long workerId) {
        
        Object[] findData = memberRepository.findByIdWithManager(workerId)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found with id: " + workerId));
        
        Worker worker = null;
        Admin admin = null;
        List<String> assignmentAreaList = null;
        
        if (findData.length > 0 && findData[0] instanceof Object[]) {
            Object[] innerArray = (Object[]) findData[0];
            
            if (innerArray.length >= 1 && innerArray[0] instanceof Worker) {
                worker = (Worker) innerArray[0];
                log.info("Worker: name={}, email={}", worker.getName(), worker.getEmail());
            }
            
            if (innerArray.length >= 2 && innerArray[1] instanceof Admin) {
                admin = (Admin) innerArray[1];
                log.info("Admin: name={}, email={}", admin.getName(), admin.getEmail());
                assignmentAreaList = adminService.getAssignmentAreaList(admin.getId());
            }
        }
        
        log.info("assignmentAreaList : " + assignmentAreaList);
        
        return WorkerResponseDTO.builder()
                .id(worker.getId())
                .username(worker.getUsername())
                .email(worker.getEmail())
                .name(worker.getName())
                .phone(worker.getPhone())
                .birth(worker.getBirth())
                .address(worker.getAddress())
                .addressDetail(worker.getAddressDetail())
                .vehicleCapacity(worker.getVehicleCapacity())
                .startDate(worker.getStartDate())
                .endDate(worker.getEndDate())
                .managerId(worker.getManagerId())
                .managerName(admin.getName())
                .managerDepartment(admin.getDepartment())
                .managerContact(admin.getContact())
                .managerWorkPlace(admin.getWorkPlace())
                .assignmentAreaList(assignmentAreaList)
                .build();
    }
    
    @Override
    public AdminResponseDTO getAdminProfile(Long adminId) {
        
        Object[] findData = memberRepository.findByIdWithManager(adminId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + adminId));
        
        Admin admin = null;
        Admin managedAdmin = null;
        List<String> assignmentAreaList = null;
        
        if (findData.length > 0 && findData[0] instanceof Object[]) {
            Object[] innerArray = (Object[]) findData[0];
            
            if (innerArray.length >= 1 && innerArray[0] instanceof Admin) {
                admin = (Admin) innerArray[0];
                log.info("Admin: name={}, email={}", admin.getName(), admin.getEmail());
            }
            
            if (innerArray.length >= 2 && innerArray[1] instanceof Admin) {
                managedAdmin = (Admin) innerArray[1];
                log.info("managedAdmin: name={}, email={}", managedAdmin.getName(), managedAdmin.getEmail());
                assignmentAreaList = adminService.getAssignmentAreaList(admin.getId());
            }
        }
        
        log.info("assignmentAreaList : " + assignmentAreaList);
        
        return AdminResponseDTO.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .email(admin.getEmail())
                .name(admin.getName())
                .phone(admin.getPhone())
                .address(admin.getAddress())
                .addressDetail(admin.getAddressDetail())
                .workCity(admin.getWorkCity())
                .workPlace(admin.getWorkPlace())
                .department(admin.getDepartment())
                .position(admin.getPosition())
                .contact(admin.getContact())
                .assignmentAreaList(assignmentAreaList)
                .build();
    }
    
    @Override
    public Page<Member> getMemberByRegularAdmin(Long adminId, String tabCondition, String nameSearch,
            Pageable pageable) {
        return memberRepository.findAllByWorkerManagedAdminWithNameSearchForRegular(adminId, tabCondition, nameSearch,
                pageable);
    }
    
    @Override
    public Page<Member> getMemberBySuperAdmin(Long adminId, String tabCondition, String nameSearch, Pageable pageable) {
        return memberRepository.findAllByWorkerManagedAdminWithNameSearchForSuper(adminId, tabCondition, nameSearch,
                pageable);
    }
    
    @Override
    public WorkerDetailResponseDTO getWorkerById(Long memberId) {
        
        // Member 엔티티에 왜 Long 값으로 managerId를 받았는지 설명 해놓음
        Optional<Object[]> byIdWithManager = memberRepository.findByIdWithManager(memberId);
        
        WorkerDetailResponseDTOBuilder builder = WorkerDetailResponseDTO.builder();
        
        byIdWithManager.ifPresent(find -> {
            if (find instanceof Object[]) {
                Object[] result = (Object[]) find[0];
                if (result.length >= 2) {
                    Object memberInfo = result[0];
                    Object adminInfo = result[1];
                    
                    log.info("Member Info: {}", memberInfo);
                    log.info("Admin Info: {}", adminInfo);
                    
                    // Worker 정보 처리
                    if (memberInfo instanceof Worker) {
                        Worker worker = (Worker) memberInfo;
                        log.info("Worker Name: {}", worker.getName());
                        // 추가 Worker 필드 접근...
                        
                        builder
                                .id(worker.getId())
                                .name(worker.getName())
                                .username(worker.getUsername())
                                .phone(worker.getPhone())
                                .birth(worker.getBirth())
                                .email(worker.getEmail())
                                .vehicleCapacity(worker.getVehicleCapacity())
                                .address(worker.getAddress())
                                .addressDetail(worker.getAddressDetail())
                                .startDate(worker.getStartDate())
                                .endDate(worker.getEndDate());
                    }
                    
                    // Admin 정보 처리
                    if (adminInfo instanceof Admin) {
                        Admin admin = (Admin) adminInfo;
                        log.info("Admin Name: {}", admin.getName());
                        // 추가 Admin 필드 접근...
                        
                        builder
                                .workGroup(admin.getWorkPlace() + " " + admin.getDepartment())
                                .managerName(admin.getName())
                                .managerContact(admin.getContact());
                    }
                } else {
                    log.info("Result array does not contain expected number of elements");
                }
            } else {
                log.info("Unexpected result type: {}", find.getClass().getName());
            }
        });
        
        return builder.build();
    }
    
    @Override
    public AdminDetailResponseDTO getAdminById(Long adminId) {
        
        Admin admin = (Admin) memberRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + adminId));
        
        return AdminDetailResponseDTO.builder()
                .id(admin.getId())
                .name(admin.getName())
                .username(admin.getUsername())
                .phone(admin.getPhone())
                .contact(admin.getContact())
                .email(admin.getEmail())
                .address(admin.getAddress())
                .addressDetail(admin.getAddressDetail())
                .workCity(admin.getWorkCity())
                .workGroup(admin.getWorkPlace() + " " + admin.getDepartment())
                .build();
    }
    
    @Override
    public void updateWorkerProfile(Long workerId, WorkerUpdateDTO workerUpdateDTO) {
        
        Worker findWorker = (Worker) memberRepository.findById(workerId)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found with id: " + workerId));
        
        // 더티체킹으로 바꾸면 바로 업데이트 됨
        // 이름은 Worker의 경우 개명을 할 경우 새로 파주는게 나을듯
        findWorker.updatePhone(workerUpdateDTO.getPhone());
        findWorker.updateEmail(workerUpdateDTO.getEmail());
        findWorker.updateVehicleCapacity(workerUpdateDTO.getVehicleCapacity());
        
    }
    
    @Override
    public void updateAdminProfile(Long adminId, AdminUpdateDTO adminUpdateDTO) {
        
        Admin findAdmin = (Admin) memberRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + adminId));
        
        // 더티체킹으로 바꾸면 바로 업데이트 됨
        findAdmin.updateName(adminUpdateDTO.getName());
        findAdmin.updatePhone(adminUpdateDTO.getPhone());
        findAdmin.updateEmail(adminUpdateDTO.getEmail());
        findAdmin.updateAddress(adminUpdateDTO.getAddress());
        findAdmin.updateAddressDetail(adminUpdateDTO.getAddressDetail());
        findAdmin.updateDepartment(adminUpdateDTO.getDepartment());
        findAdmin.updatePosition(adminUpdateDTO.getPosition());
        findAdmin.updateContact(adminUpdateDTO.getContact());
    }
    
    @Override
    public void confirmOldPassword(Long memberId, String oldPassword) {
        
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("해당 Member의 id를 찾을 수 없습니다. " + memberId));
        
        if (!passwordEncoder.matches(oldPassword, findMember.getPassword())) {
            throw new PasswordMismatchException();
        }
    }
    
    @Override
    public void changePassword(Long memberId, String newPassword, String newConfirmPassword) {
        
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("해당 Member의 id를 찾을 수 없습니다. " + memberId));
        
        if (!newPassword.equals(newConfirmPassword)) {
            throw new CustomException("두 패스워드가 일치하지 않습니다.");
        }
        
        if (!isValidPassword(newPassword)) {
            throw new CustomException("비밀번호는 최소 8자 이상이어야 하며, 숫자, 문자, 특수문자를 포함해야 합니다.");
        }
        
        findMember.changePassword(passwordEncoder.encode(newPassword));
    }
    
    private boolean isValidPassword(String password) {
        // 비밀번호 복잡성 검증 로직 구현
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
    
}
