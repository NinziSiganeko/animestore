package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.factory.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    private AdminService service;

    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = AdminFactory.createAdmin(
                "John_Doe", "John", "Doe", "veronicapuleng91@gmail.com", "123 Main St", "0672345678");

    }

    @Test
    void a_create() {
        Admin created = service.create(admin);
        assertNotNull(created);
        System.out.println(created);

    }

    @Test
    void b_read() {
        Admin read = service.read(21L);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void d_update() {
        Admin admin1 = new Admin.Builder().copy(this.admin).setUsername("john_doe2")
                .build();
        Admin updated = service.update(admin1);
        assertNotNull(updated);
        System.out.println(updated);
    }


    @Test
    void getAll() {
        System.out.println(service.getAll());
    }
}
