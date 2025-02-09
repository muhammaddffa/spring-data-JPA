package spring_data_jpa.belajar_spring_data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data_jpa.belajar_spring_data_jpa.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Where name = ?
    Optional<Category> findFirstByNameEquals(String name);

    // where name like ?
    List<Category> findAllByNameLike(String name);
}
