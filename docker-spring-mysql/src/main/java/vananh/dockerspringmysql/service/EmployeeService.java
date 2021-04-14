package vananh.dockerspringmysql.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vananh.dockerspringmysql.model.Employee;

public interface EmployeeService {

    Page<Employee> getAll(Pageable pageable);

}
