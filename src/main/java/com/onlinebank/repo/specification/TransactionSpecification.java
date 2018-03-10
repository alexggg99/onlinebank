package com.onlinebank.repo.specification;

import com.onlinebank.model.accounts.PrimaryTransaction;
import com.onlinebank.model.accounts.SavingTransaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TransactionSpecification {
    public static Specification<PrimaryTransaction> findAll(String username, String name) {
        return new Specification<PrimaryTransaction>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<PrimaryTransaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                String val = name;
                if (TransactionSpecification.checknumeric(val)) {
                    return cb.and(cb.equal(root.get("amount"), val));
                } else {
                    return cb.or(cb.like(root.get("description"), "%" + val + "%"),
                            cb.like(root.get("type"), "%" + val + "%"),
                            cb.like(root.get("status"), "%" + val + "%"));
                }
            }
        };
    }

    public static Specification<SavingTransaction> findAll2(String username, String name) {
        return new Specification<SavingTransaction>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<SavingTransaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                String val = name;
                if (TransactionSpecification.checknumeric(val)) {
                    return cb.and(cb.equal(root.get("amount"), val));
                } else {
                    return cb.or(cb.like(root.get("description"), "%" + val + "%"),
                            cb.like(root.get("type"), "%" + val + "%"),
                            cb.like(root.get("status"), "%" + val + "%"));
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
