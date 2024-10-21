package edu.du.sb1018;

import edu.du.sb1018.entity.Dept;
import edu.du.sb1018.entity.Emp;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.util.List;

@SpringBootTest
@Aspect
class Sb1018ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Test
    void contextLoads() {
    }

    @Test
    void persistenceFindTest(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 10);
        dept.setDname("서울");
        System.out.println(dept);
        transaction.commit();
    }

    @Test
    void persistenceMergeTest(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 10);
        dept.setDname("서울");
        System.out.println(dept);
        em.detach(dept);
//        em.merge(dept);
        transaction.commit();
    }

    @Test
    void persistencePersistTest(){
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Dept newDept = new Dept();
        newDept.setDeptno(50);
        newDept.setDname("연구소");
        newDept.setLoc("서울");
        em.persist(newDept);
        transaction.commit();
    }

    @Test
    void persistenceRemoveTest(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 50);
        System.out.println(dept);
        em.remove(dept);
        transaction.commit();
    }

    @Test
    void persistenceUpdateTest(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 10);
        dept.setDname("Accounting");
        dept.setLoc("Seoul");
        System.out.println();
        transaction.commit();
    }

    @Test
    void createQueryTest(){
        Dept dept = em.find(Dept.class, 10);
        System.out.println(dept);
        System.out.println("------------------------------------------------");

        TypedQuery<Dept> query = em.createQuery("select d from Dept d", Dept.class);
        List<Dept> depts = query.getResultList();
        depts.forEach(System.out::println);
    }

    @Test
    void createQueryTest2(){
        String jpql = "select d from Dept d where d.dname = :deptName";

        TypedQuery<Dept> query = em.createQuery(jpql, Dept.class);
        query.setParameter("deptName", "Accounting");
        List<Dept> depts = query.getResultList();
        Dept dept = depts.get(0);
        System.out.println(dept);

        String jpql2 = "select e from Emp e where e.deptno = :deptNo";

        TypedQuery<Emp> query2 = em.createQuery(jpql2, Emp.class);
        query2.setParameter("deptNo", dept.getDeptno());
        List<Emp> emps = query2.getResultList();
        emps.forEach(System.out::println);
    }

}
