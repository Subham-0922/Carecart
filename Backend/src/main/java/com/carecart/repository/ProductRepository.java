package com.carecart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carecart.models.Category;
import com.carecart.models.Products;
@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
	List<Products> findByCategoryId(Category categoryId);
}
