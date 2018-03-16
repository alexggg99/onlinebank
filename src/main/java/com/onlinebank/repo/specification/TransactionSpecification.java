package com.onlinebank.repo.specification;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.Transaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TransactionSpecification {
    public static Specification<Transaction> findAll(String username, Account account, String name) {
        return new Specification<Transaction>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                String val = name;
                if (TransactionSpecification.checknumeric(val)) {
                    return cb.and(
                            cb.and(cb.equal(root.get("account").get("id"), account.getId()),
                                    cb.equal(root.get("account").get("user").get("username"), username)),
                            cb.equal(root.get("amount"), val)
                    );
                } else {
                    return cb.and(
                        cb.and(cb.equal(root.get("account").get("id"), account.getId()),
                                cb.equal(root.get("account").get("user").get("username"), username)),
                        cb.or(cb.like(root.get("description"), "%" + val + "%"),
                                cb.like(root.get("type"), "%" + val + "%"),
                                cb.like(root.get("status"), "%" + val + "%"))
                    );
                }
            }
        };
    }

    public static boolean checknumeric(String str){
        String temp = str;
        if(str.startsWith("-")){ //checks for negative values
            temp = str.substring(1);
        }
        return temp.matches("[+]?\\d*(\\.\\d+)?");
    }

}
