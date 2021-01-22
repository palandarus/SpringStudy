package com.geekbrains.spring.lesson8.repositories.specifications;

import com.geekbrains.spring.lesson8.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> nameLike(String namePart) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart)); // where p.title like %titlePart%
    }

    public static Specification<User> addressLike(String addressPart) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("address"), String.format("%%%s%%", addressPart));
    }

    public static Specification<User> usernameLike(String usernamePart) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), String.format("%%%s%%", usernamePart));
    }

    public static Specification<User> phoneLike(String phonePart) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("phone"), String.format("%%%s%%", phonePart));
    }

    public static Specification<User> emailLike(String emailPart) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), String.format("%%%s%%", emailPart));
    }


}
