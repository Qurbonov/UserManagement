package mf.um.spec;

import mf.um.domain.Users;
import mf.um.domain.Users_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpec {
    public static Specification<Users> byUsername(final String username) {
        return new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(cb.lower(root.get(Users_.username)), username.toLowerCase());
            }
        };
    }
}
