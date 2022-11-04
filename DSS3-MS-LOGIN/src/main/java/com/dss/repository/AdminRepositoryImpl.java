package com.dss.repository;

import com.dss.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepositoryImpl{

    public static Specification<User> createCriteria(User criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getEmailId()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("emailId"), criteria.getEmailId()));
            }
            if (criteria.getFirstName()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), criteria.getFirstName()));
            }
            if (criteria.getLastName()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), criteria.getLastName()));
            }
            if (criteria.getPhoneNumber()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), criteria.getPhoneNumber()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
