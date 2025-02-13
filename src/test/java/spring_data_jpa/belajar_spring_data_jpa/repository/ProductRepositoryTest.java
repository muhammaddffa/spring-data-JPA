package spring_data_jpa.belajar_spring_data_jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.support.TransactionOperations;
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

    @Autowired
    private TransactionOperations transactionOperations;

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
    void testFindProductWithPageable() {
        // Page 0
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);
        assertEquals(1, products.getContent().size());// Untuk Mendapatkan List Productnya
        assertEquals(0, products.getNumber()); // Untuk Mendapatkan Page Saat Ini (page ke berapa)
        assertEquals(2, products.getTotalElements());// Untuk Mendapatkan Total Data
        assertEquals(2, products.getTotalPages()); // Untuk Mendapatkan Total Page
        assertEquals("Apple iPhone 13 Pro Max", products.getContent().get(0).getName());

        //Page 1
        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));
        products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);
        assertEquals(1, products.getContent().size());// Untuk Mendapatkan List Productnya
        assertEquals(1, products.getNumber()); // Untuk Mendapatkan Page Saat Ini (page ke berapa)
        assertEquals(2, products.getTotalElements());// Untuk Mendapatkan Total Data
        assertEquals(2, products.getTotalPages()); // Untuk Mendapatkan Total Page
        assertEquals("Apple iPhone 14 Pro Max", products.getContent().get(0).getName());
    }

    @Test
    void count(){
        Long count = productRepository.count();
        assertEquals(2L,count);

        count = productRepository.countByCategory_Name("GADGET MURAH");
        assertEquals(2L, count);

        count = productRepository.countByCategory_Name("GAK ADA");
        assertEquals(0L, count);
    }

    @Test
    void exists(){
        boolean exists = productRepository.existsByName("Apple iPhone 14 Pro Max");
        assertTrue(exists);

        exists = productRepository.existsByName("Apple iPhone 14 Pro Max SALAH");
        assertFalse(exists);
    }

    @Test
    void delete(){
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
                assertNotNull(category);

                Product product = new Product();
                product.setName("Samsung Galaxy S9");
                product.setPrice(10_000_000L);
                product.setCategory(category);
                productRepository.save(product);

            int delete = productRepository.deleteByName("Samsung Galaxy S9");
            assertEquals(1L, delete);

            delete = productRepository.deleteByName("Samsung Galaxy S9");
            assertEquals(0L, delete);


        });
    }
}
