/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.hibernatedemo;

import com.ntt.pojo.Category;
import com.ntt.pojo.Comment;
import com.ntt.pojo.OrderDetail;
import com.ntt.pojo.ProdTag;
import com.ntt.pojo.Product;
import com.ntt.pojo.SaleOrder;
import com.ntt.pojo.Tag;
import com.ntt.pojo.User;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author admin
 */
public class hibernateUtils {
    private static final SessionFactory FACTORY;

    /**
     * @return the FACTORY
     */
    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
    static {
        Configuration conf=new Configuration();
        Properties pros =new Properties();
        pros.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        pros.setProperty(Environment.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
        pros.setProperty(Environment.JAKARTA_JDBC_URL, "jdbc:mysql://localhost:3306/saledb");
        pros.setProperty(Environment.JAKARTA_JDBC_USER, "root");
        pros.setProperty(Environment.JAKARTA_JDBC_PASSWORD, "root");
        pros.setProperty(Environment.SHOW_SQL, "True");
        
        conf.setProperties(pros);
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Comment.class);
        conf.addAnnotatedClass(Tag.class);
        conf.addAnnotatedClass(ProdTag.class);
        conf.addAnnotatedClass(SaleOrder.class);
        conf.addAnnotatedClass(OrderDetail.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY=conf.buildSessionFactory(serviceRegistry);
        
        
    }
    
}
