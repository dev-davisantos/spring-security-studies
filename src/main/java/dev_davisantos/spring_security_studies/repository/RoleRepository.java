package dev_davisantos.spring_security_studies.repository;

import dev_davisantos.spring_security_studies.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
