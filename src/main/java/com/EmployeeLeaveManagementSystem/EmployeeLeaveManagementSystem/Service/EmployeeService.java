package com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Service;


import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Model.Credentials;
import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Model.Employee;
import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Repository.AdminRepo;
import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    private EmployeeRepo employeeRepo;
    private AdminRepo adminRepo;


    public EmployeeService(EmployeeRepo employeeRepo, AdminRepo adminRepo) {
        this.employeeRepo = employeeRepo;
        this.adminRepo = adminRepo;
    }



    public Employee login(Credentials credentials){

        Employee e= employeeRepo.findByNameAndPassword(credentials.getUsername(),credentials.getPassword());
        return e;
    }

    public void requestLeave(int employeeId){
       employeeRepo.updateLeaveRequestToTrue(employeeId);
    }

    public List<Employee> requestingLeaveEmployees(){
        return employeeRepo.requestingLeaveEmployees();
    }

    public void updateLeaveRequestAcceptedToTrue(int employeeId){
        employeeRepo.updateLeaveRequestAcceptedToTrue(employeeId);
        employeeRepo.updateLeaveBalance(employeeId);
    }

    public Employee getEmployee(int employeeID){
       return employeeRepo.getById((long) employeeID);
    }



















}
