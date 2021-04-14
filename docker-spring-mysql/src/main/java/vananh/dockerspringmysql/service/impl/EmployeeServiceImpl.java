package vananh.dockerspringmysql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vananh.dockerspringmysql.model.Employee;
import vananh.dockerspringmysql.repository.EmployeeRepository;
import vananh.dockerspringmysql.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
