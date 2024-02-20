package com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Repository;

import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long>  {

    Employee findByNameAndPassword(String name, String password);

    // Method to update leaveRequest attribute to true for a specific employeeId
    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.leaveRequest = true WHERE e.employee_ID = ?1")
    void updateLeaveRequestToTrue(int employeeId);

    @Query("SELECT e FROM Employee e WHERE e.leaveRequest = true AND e.leaveRequestAccepted = false")
    List<Employee> requestingLeaveEmployees();


    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.leaveRequestAccepted = true WHERE e.employee_ID = ?1")
    void updateLeaveRequestAcceptedToTrue(int employeeId);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.leaveBalance = 2500 WHERE e.employee_ID = ?1")
    void updateLeaveBalance(int employeeId);
}
