package com.study.jpa;

import com.study.jpa.entity.Customer;
import jakarta.persistence.*;

import java.util.List;

public class CustomerJpaExam {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
//            Customer customer = new Customer("ID0003", "Kim");
//            em.persist(customer); // 1차 캐싱
//            Customer cus01 = em.find(Customer.class, "ID0002");
//            System.out.println("cus01 = " + cus01);
//            Customer foundCustomer = em.find(Customer.class, "ID0001");
//            foundCustomer.setName("Park");
//            em.remove(foundCustomer);
//            System.out.println(foundCustomer.toString());
//            tx.commit(); // commit 시점에 insert 가 된다

//            Customer customer = new Customer("ID0003", "Hong"); // 비영속 상태(new)
//            em.persist(customer); // 영속 상태(Managed)
//
////            em.flush();
//
//            Customer customer1 = em.find(Customer.class, "ID0001"); // 영속 상태(Managed)
//
//            Query query = em.createQuery("SELECT c FROM Customer c", Customer.class);
//            List<Customer> customers = query.getResultList();
//            System.out.println("customers = " + customers);
//
//            tx.commit();
            String id = "ID0006";
            Customer customer = new Customer(id, "Jin");
            em.persist(customer); // 영속상태
            Customer foundCustomer = em.find(Customer.class, id); // 1차캐시에 존재(SELECT X)
            System.out.println(foundCustomer);
            em.detach(customer); // 준영속 상태(Detached)
            Customer foundCustomer2 = em.find(Customer.class, id); // 1차캐시에 존재 X(SELECT X)

            System.out.println(foundCustomer2);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
