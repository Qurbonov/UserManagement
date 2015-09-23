package mf.um.repositories;

import mf.um.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qurbonov on 9/3/2015.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
