package spring_data_jpa.belajar_spring_data_jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data_jpa.belajar_spring_data_jpa.entity.Product;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    int deleteByName(String name);

    // Exist method untuk mencari data nya apakah ada atau tidak (tidak mengselect semua data nya (lebih simple))
    boolean existsByName(String name);

    Long countByCategory_Name(String name);

    List<Product> findAllByCategory_Name(String name);

    List<Product> findAllByCategory_Name(String name, Sort sort);

    Page<Product> findAllByCategory_Name(String name, Pageable pageable);
}
