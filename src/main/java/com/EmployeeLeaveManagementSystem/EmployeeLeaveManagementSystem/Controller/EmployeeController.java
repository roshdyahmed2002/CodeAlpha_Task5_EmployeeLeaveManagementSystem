package com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Controller;


import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Model.Admin;
import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Model.Credentials;
import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Model.Employee;
import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Repository.AdminRepo;
import com.EmployeeLeaveManagementSystem.EmployeeLeaveManagementSystem.Service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model; // Ensure this import statement is correct

import java.util.List;
import java.util.Optional;


@Controller
public class EmployeeController {

    private EmployeeService employeeService;
    private AdminRepo adminRepo;

    public EmployeeController(EmployeeService employeeService,AdminRepo adminRepo) {
        this.employeeService = employeeService;
        this.adminRepo=adminRepo;
    }

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("credentials", new Credentials());
        return "login"; // Assuming your Thymeleaf login page is named "login.html" or "login.html"
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("credentials") Credentials credentials, HttpSession session) {
        // Validate credentials and authenticate user
        Employee e = employeeService.login(credentials);
        if(e != null) {
            System.out.println("INN");
            System.out.println(e.getEmployee_ID() + " " + e.getName());

            // Successful login, store employee object in session
            session.setAttribute("employee", e);

            return "redirect:/dashboard";
        } else {
            Admin admin= adminRepo.findByNameAndPassword(credentials.getUsername(), credentials.getPassword());
            if(admin!=null){
                return "redirect:/dashboardAdmin";
            }
            else{
                return "redirect:/";
            }
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        // Retrieve the logged-in employee from the session
        Employee employee = (Employee) session.getAttribute("employee");

        if(employee != null) {
            System.out.println(employee.getEmployee_ID() + " 222 " + employee.getName());
            model.addAttribute("employee", employee);
            return "dashboard"; // Assuming your Thymeleaf dashboard page is named "dashboard.html"
        } else {
            // If the employee object is null, redirect back to the login page
            return "redirect:/";
        }
    }

    @PostMapping("/requestLeave")
    public String requestLeave(@RequestParam("employeeId") int employeeId,Model model) {
        // Call your service method to handle the leave request for the employee with the provided ID
        employeeService.requestLeave(employeeId);
        model.addAttribute("employee",employeeService.getEmployee(employeeId));
        // Redirect back to the dashboard or any other page
        return "dashboard";
    }

    @GetMapping("/dashboardAdmin")
    public String dashboardAdmin(Model model) {

        List<Employee>employees=employeeService.requestingLeaveEmployees();

        model.addAttribute("employees",employees);
        return "dashboardAdmin";
    }

    @PostMapping("/acceptLeave")
    public String acceptLeave(@RequestParam("employeeId") int employeeId) {
        employeeService.updateLeaveRequestAcceptedToTrue(employeeId);
        // Return a response message indicating success
        return "redirect:/dashboardAdmin";
    }

}
