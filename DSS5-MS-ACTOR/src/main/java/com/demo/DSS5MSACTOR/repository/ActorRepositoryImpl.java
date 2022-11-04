package com.demo.DSS5MSACTOR.repository;

import com.demo.DSS5MSACTOR.model.Actor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorRepositoryImpl {
    public static Specification<Actor> createCriteria(Actor criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getFirstName()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), criteria.getFirstName()));
            }
            if (criteria.getLastName()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), criteria.getLastName()));
            }
            if (criteria.getGender()!= '\0') {
                predicates.add(criteriaBuilder.equal(root.get("gender"), criteria.getGender()));
            }
            if (criteria.getAge()!=0) {
                predicates.add(criteriaBuilder.equal(root.get("age"), criteria.getAge()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
