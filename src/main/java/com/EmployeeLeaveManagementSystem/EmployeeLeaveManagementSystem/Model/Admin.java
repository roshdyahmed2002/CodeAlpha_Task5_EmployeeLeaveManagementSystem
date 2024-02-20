package com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    @Entity
    public class Admin {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int admin_ID;
        private String name;
        private String password;

    }




