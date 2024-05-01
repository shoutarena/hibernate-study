package com.study.jpa;

import com.study.jpa.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CustomerJpaExam {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Customer customer = new Customer("ID0003", "Kim");
            em.persist(customer); // 1차 캐싱
            Customer cus01 = em.find(Customer.class, "ID0002");
            System.out.println("cus01 = " + cus01);
//            Customer foundCustomer = em.find(Customer.class, "ID0001");
//            foundCustomer.setName("Park");
//            em.remove(foundCustomer);
//            System.out.println(foundCustomer.toString());
            tx.commit(); // commit 시점에 insert 가 된다
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
