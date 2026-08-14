package dev_davisantos.spring_security_studies.repository;

import dev_davisantos.spring_security_studies.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);

}
