package com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    @Entity
    public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int employee_ID;

        private String name;
        private String password;
        private boolean leaveRequest;
        private boolean leaveRequestAccepted;
        private int leaveBalance;


    }


