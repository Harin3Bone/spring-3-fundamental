package com.tutorial.springfundamental.repository;

import com.tutorial.springfundamental.entity.Keyboard;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class KeyboardSpecification {

    public Specification<Keyboard> nameSpec(String inputName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + inputName + "%");
    }

    public Specification<Keyboard> quantitySpec(int quantity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("quantity"), quantity);
    }

    public Specification<Keyboard> priceSpec(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), min, max);
    }

}
