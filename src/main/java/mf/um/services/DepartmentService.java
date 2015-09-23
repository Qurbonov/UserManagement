package mf.um.services;

import mf.um.domain.Department;
import mf.um.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qurbonov on 9/3/2015.
 */
@Service
@Transactional(readOnly = true)
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional(readOnly = false)
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional(readOnly = false)
    public Department update(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    public Department findOne(Long departmentId) {
        return departmentRepository.findOne(departmentId);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    @Transactional(readOnly = false)
    public void delete(Long departmentID) {
        departmentRepository.delete(departmentID);
    }
}
