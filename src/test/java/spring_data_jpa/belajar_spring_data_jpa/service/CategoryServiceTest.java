package spring_data_jpa.belajar_spring_data_jpa.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void success(){
        assertThrows(RuntimeException.class, () -> {
            categoryService.create();
        });
    }

    @Test
    void failed(){
        assertThrows(RuntimeException.class, () -> {
            categoryService.test();
        });
    }

}
