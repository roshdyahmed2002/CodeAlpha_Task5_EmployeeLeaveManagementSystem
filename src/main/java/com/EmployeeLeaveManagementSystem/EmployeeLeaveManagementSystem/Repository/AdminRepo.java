package com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Repository;

import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long>  {

    Admin findByNameAndPassword(String name, String password);

}
