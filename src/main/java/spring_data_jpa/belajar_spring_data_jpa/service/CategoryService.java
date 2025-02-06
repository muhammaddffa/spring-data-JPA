package spring_data_jpa.belajar_spring_data_jpa.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data_jpa.belajar_spring_data_jpa.entity.Category;
import spring_data_jpa.belajar_spring_data_jpa.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void create(){
        for (int i = 0; i < 5; i++){
            Category category = new Category();
            category.setName("Category " + i);
            categoryRepository.save(category);
        }
        throw new RuntimeException("Ups rollback please");
    }

    public void test(){
        create();
    }
}
