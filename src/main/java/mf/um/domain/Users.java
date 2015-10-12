package mf.um.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

@Entity
@Table(name = "Users")
public class Users implements Serializable, UserDetails, Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    //    @Column(name = "department")
    @ManyToMany
    private List<Department> department;

    @Column(name = "password")
    private String password;

    //@Column(name = "module")
    //private String module;

    //@Column(name = "usertype")
    //private String userType;

    @ManyToMany
    private List<Module> modules;

    @ManyToMany
    private List<Role> roles;

    @ManyToMany
    @Column(name = "permissions")
    private List<Permission> permissions;


    public Users() {
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//----------------------------------------------------------------------------------------------

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

//-----------------------------------------------------------------------------------------------

//    public String getModule() {
//        return module;
//    }
//
//    public void setModule(String module) {
//        this.module = module;
//    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

//    public String getUserType() {
//        return userType;
//    }
//
//    public void setUserType(String userType) {
//        this.userType = userType;
//    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public String getName() {
        return username;
    }
}
