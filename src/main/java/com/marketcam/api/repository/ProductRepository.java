package com.marketcam.api.repository;

import com.marketcam.api.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    default List<Product> findAllByFilter(ProductFilterDTO filter) {
        return findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getName() != null && !filter.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
            }

            if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + filter.getDescription().toLowerCase() + "%"));
            }

            if (filter.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
            }

            if (filter.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
            }

            if (filter.getSize() != null) {
                predicates.add(criteriaBuilder.equal(root.get("size"), filter.getSize()));
            }

            if (filter.getQuantity() != null) {
                predicates.add(criteriaBuilder.equal(root.get("quantity"), filter.getQuantity()));
            }

            if (filter.getCategoryName() != null && !filter.getCategoryName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("category").get("name")), "%" + filter.getCategoryName().toLowerCase() + "%"));
            }

            if (filter.getColorName() != null && !filter.getColorName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("color").get("name")), "%" + filter.getColorName().toLowerCase() + "%"));
            }

            if (filter.getBrandName() != null && !filter.getBrandName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("brand").get("name")), "%" + filter.getBrandName().toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
