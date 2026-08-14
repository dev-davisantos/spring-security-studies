package dev_davisantos.spring_security_studies.service;

import dev_davisantos.spring_security_studies.model.RoleEntity;
import dev_davisantos.spring_security_studies.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity createRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    @Transactional(readOnly = true)
    public RoleEntity findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }
}
