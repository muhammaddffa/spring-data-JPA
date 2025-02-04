package spring_data_jpa.belajar_spring_data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data_jpa.belajar_spring_data_jpa.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
