package edu.du.sb1023_3;

import edu.du.sb1023_3.entity.AnsweredData;
import edu.du.sb1023_3.entity.Respondent;
import edu.du.sb1023_3.entity.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@SpringBootTest
class Sb10233ApplicationTests {

    @Autowired
    private EntityManagerFactory emf;

    @Test
    void save_test() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        AnsweredData ad = new AnsweredData();
        em.persist(ad);

        Response res1 = new Response("질문1", "대답1");
        Response res2 = new Response("질문2", "대답2");
        em.persist(res1);
        em.persist(res2);

        Respondent resp1 = new Respondent(24, "서울");
        em.persist(resp1);

        ad.addResponse(res1);
        ad.addResponse(res2);

        res1.setAnswerId(ad);
        res2.setAnswerId(ad);

        ad.setRes(resp1);
//        resp1.setAnswerId(ad);

        System.out.println("결과: "+ad);

        tx.commit();

    }

    @Test
    void find_test(){
        emf.createEntityManager().createQuery("select a from AnsweredData a").getResultList().forEach(System.out::println);

    }

}
