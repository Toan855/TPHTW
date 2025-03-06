/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.hibernatedemo;

import com.ntt.reponsitory.impl.CategoryReponsitoryImpl;
import com.ntt.reponsitory.impl.ProductReponsitoryImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class hibernatedemo {

    public static void main(String[] args) {
//        CategoryReponsitoryImpl cates = new CategoryReponsitoryImpl();
//        cates.getCate().forEach(c -> System.out.println(c.getName()));
        Map<String, String> params = new HashMap<>();
        params.put("kw", "iPhone");
        params.put("fromPrice", "10000000");
        params.put("toPrice", "20000000");
        params.put("cateId", "1");
//        params.put("page", "2");

        ProductReponsitoryImpl p = new ProductReponsitoryImpl();
        p.getProducts(params).forEach(v -> System.out.printf("ID: %d- Name: %s- Price:%.1f\n", v.getId(), v.getName(), v.getPrice()));
    }
}
