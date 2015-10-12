package mf.um.repositories;

import mf.um.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qurbonov on 10/6/2015.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
