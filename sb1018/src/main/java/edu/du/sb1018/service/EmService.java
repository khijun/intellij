package edu.du.sb1018.service;

import edu.du.sb1018.entity.Dept;
import edu.du.sb1018.entity.Emp;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
@Log4j2

public class EmService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    public Integer add(Dept dept) throws Exception{
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(dept);
        tx.commit();
        return dept.getDeptno();
    }

    public List<Emp> getAll() throws Exception{
        return em.createQuery("select e from Emp e", Emp.class).getResultList();
    }

    public Dept set(Dept deptDto){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Dept dept = em.find(Dept.class, deptDto.getDeptno());
        dept.setDname(deptDto.getDname());
        dept.setLoc(deptDto.getLoc());
        return dept;
    }

    public int del(int deptNo) {
        return 0;
    }

}
