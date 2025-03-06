/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.reponsitory.impl;

import com.ntt.hibernatedemo.hibernateUtils;
import com.ntt.pojo.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author admin
 */
public class ProductReponsitoryImpl {

    private static final int PAGE_SIZE = 6;

    public List<Product> getProducts(Map<String, String> params) {
        try (Session session = hibernateUtils.getFACTORY().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery q = builder.createQuery(Product.class);
            Root root = q.from(Product.class);
            q.select(root);

            if (params != null) {

                List<Predicate> predicates = new ArrayList<>();

                String kw = params.get("kw");
                if (kw != null && !kw.isEmpty()) {

                    predicates.add(builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));

                }

                String fromPrice = params.get("fromPrice");
                if (fromPrice != null && !fromPrice.isEmpty()) {
                    predicates.add(builder.greaterThan(root.get("price").as(Double.class), Double.valueOf(fromPrice)));

                    String toPrice = params.get("toPrice");
                    if (toPrice != null && !toPrice.isEmpty()) {
                        predicates.add(builder.lessThan(root.get("price").as(Double.class), Double.valueOf(toPrice)));
                    }

                    String cateId = params.get("cateId");
                    if (cateId != null && !cateId.isEmpty()) {
                        predicates.add(builder.equal(root.get("categoryId").as(Integer.class), Integer.valueOf(cateId)));

                        

                    }
                    q.orderBy(builder.desc(root.get("id")));

                }
                q.where(predicates.toArray(Predicate[]::new));
            }
            Query query = session.createQuery(q);
            if (params != null) {
                String page = params.get("page");
                if (page != null && !page.isEmpty()) {
                    int p = Integer.parseInt(page);
                    int start = (p - 1) * PAGE_SIZE;
                    query.setFirstResult(start);
                    query.setMaxResults(PAGE_SIZE);
                }
            }

            return query.getResultList();

        }

    }
}
