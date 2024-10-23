package com.truonglq.demo.services.user;

import com.truonglq.demo.models.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> usernameContains(String username) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("username"), "%" + username + "%");
//                criteriaBuilder.equal(root.get("username"), username);
    }
}
