/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.reponsitory.impl;

import com.ntt.hibernatedemo.hibernateUtils;
import com.ntt.pojo.Category;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class CategoryReponsitoryImpl {
    public List<Category> getCate(){
        try(Session s= hibernateUtils.getFACTORY().openSession()){
            Query q= s.createQuery("FROM Category", Category.class);
            return q.getResultList();
        }
    }
}
