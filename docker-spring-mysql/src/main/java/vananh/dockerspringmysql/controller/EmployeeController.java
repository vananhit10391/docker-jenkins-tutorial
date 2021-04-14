package vananh.dockerspringmysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vananh.dockerspringmysql.model.Employee;
import vananh.dockerspringmysql.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping("/")
    public ResponseEntity<Page<Employee>> getAll(Pageable pageable) {
        Page<Employee> employees = service.getAll(pageable);
        return ResponseEntity.ok(employees);
    }
}
