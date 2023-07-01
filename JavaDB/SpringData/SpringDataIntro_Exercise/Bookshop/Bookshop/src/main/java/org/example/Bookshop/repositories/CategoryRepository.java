package org.example.Bookshop.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.Bookshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoryByName(String name);

    List<Category> findAll();
}
