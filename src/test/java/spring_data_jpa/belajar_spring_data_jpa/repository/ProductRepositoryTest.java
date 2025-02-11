package spring_data_jpa.belajar_spring_data_jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import spring_data_jpa.belajar_spring_data_jpa.entity.Category;
import spring_data_jpa.belajar_spring_data_jpa.entity.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createProduct(){
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        {
            Product product = new Product();
            product.setName("Apple iPhone 14 Pro Max");
            product.setPrice(25_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

        {
            Product product = new Product();
            product.setName("Apple iPhone 13 Pro Max");
            product.setPrice(18_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }
    }

    @Test
    void findByCategoryName(){
        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH");
        assertEquals(2, products.size());
        assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
        assertEquals("Apple iPhone 13 Pro Max", products.get(1).getName());
    }

    @Test
    void sort(){
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", sort);
        assertEquals(2, products.size());
        assertEquals("Apple iPhone 13 Pro Max", products.get(0).getName());
        assertEquals("Apple iPhone 14 Pro Max", products.get(1).getName());
    }

    @Test
    void testFindProductWithPageable(){
        // Page 0
        Pageable pageable = PageRequest.of(0,1, Sort.by(Sort.Order.desc("id")));
        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);

        assertEquals(1, products.size());
        assertEquals("Apple iPhone 13 Pro Max", products.get(0).getName());

        //Page 1
        pageable = PageRequest.of(1,1, Sort.by(Sort.Order.desc("id")));
        products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);

        assertEquals(1, products.size());
        assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
    }
}
