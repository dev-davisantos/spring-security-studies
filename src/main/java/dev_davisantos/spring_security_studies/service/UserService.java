package dev_davisantos.spring_security_studies.service;

import dev_davisantos.spring_security_studies.model.UserEntity;
import dev_davisantos.spring_security_studies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity createUser(UserEntity request) {
        return userRepository.save(request);
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
