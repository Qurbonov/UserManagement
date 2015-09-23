package mf.um.domain;

import javax.persistence.*;

/**
 * Created by qurbonov on 9/3/2015.
 */

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "departmentName")
    private String departmentName;

    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", depName='" + departmentName + '\'' +
                '}';
    }
}
