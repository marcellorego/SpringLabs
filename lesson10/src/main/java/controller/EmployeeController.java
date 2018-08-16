package controller;

import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repository.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Controller
@RequestMapping("/{tenantid}")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Employee> getAllEmployees() {
        // This returns a JSON or XML with the users
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody Employee addNewEmployee (@RequestParam String name,
           @RequestParam String department,
           @RequestParam String office) {

        Employee n = new Employee();
        n.setName(name);
        n.setDepartment(department);
        n.setOffice(office);
        return employeeRepository.save(n);
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Employee> getUserById(@PathVariable final Long employeeId) {
        // This returns a JSON or XML with the users
        Employee result = employeeRepository.findOne(employeeId);
        return ResponseEntity.ok(result);
    }
}