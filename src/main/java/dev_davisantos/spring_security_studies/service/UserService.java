package dev_davisantos.spring_security_studies.service;

import dev_davisantos.spring_security_studies.dto.UserRequestDTO;
import dev_davisantos.spring_security_studies.model.RoleEntity;
import dev_davisantos.spring_security_studies.model.UserEntity;
import dev_davisantos.spring_security_studies.repository.RoleRepository;
import dev_davisantos.spring_security_studies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity createUser(UserRequestDTO dto) {
        Set<RoleEntity> roles = dto.roles()
                .stream()
                .map(role ->
                        roleRepository.findByName(role).orElse(null))
                .collect(Collectors.toSet());

        if (roles.isEmpty() || roles.size() < 1) {
            throw new IllegalArgumentException("Roles was not found");
        }

        UserEntity newUser = UserEntity.builder()
                .name(dto.name())
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .roles(roles)
                .build();

        return userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
