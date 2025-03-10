package com.banka1.user.bootstrap;

import com.banka1.user.model.Customer;
import com.banka1.user.model.Employee;
import com.banka1.user.model.helper.Department;
import com.banka1.user.model.helper.Gender;
import com.banka1.user.model.helper.Permission;
import com.banka1.user.model.helper.Position;
import com.banka1.user.repository.CustomerRepository;
import com.banka1.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {
    public final EmployeeRepository employeeRepository;
    public final CustomerRepository customerRepository;

    @Autowired
    public BootstrapData(EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("============== Loading Data ==============");

        Employee admin = new Employee();

        String rawPassword = "admin";
        String salt = "salt";
        String hashedPassword = BCrypt.hashpw(rawPassword + salt, BCrypt.gensalt());

        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("admin@admin.com");
        admin.setPassword(hashedPassword);
        admin.setIsAdmin(true);
        admin.setPhoneNumber("1234567890");
        admin.setBirthDate("2000-01-01");
        admin.setGender(Gender.MALE);
        admin.setDepartment(Department.HR);
        admin.setPosition(Position.DIRECTOR);
        admin.setActive(true);
        admin.setAddress("Admin Address");
        admin.setSaltPassword(salt);
        admin.setUsername("admin123");

        admin.setPermissions(List.of(Permission.CREATE_EMPLOYEE, Permission.READ_EMPLOYEE));

        employeeRepository.save(admin);

        var musterija = new Customer();

        musterija.setFirstName("musterija");
        musterija.setLastName("musterija");
        musterija.setEmail("musterija@musterija.com");
        musterija.setPassword("musterija");
        musterija.setPhoneNumber("1234567890");
        musterija.setBirthDate(20000101L);
        musterija.setGender(Gender.MALE);
        musterija.setAddress("musterija Address");
        musterija.setSaltPassword("salt");
        musterija.setUsername("musterija123");

        customerRepository.save(musterija);

        System.out.println("============== Data Loaded ==============");
    }
}
