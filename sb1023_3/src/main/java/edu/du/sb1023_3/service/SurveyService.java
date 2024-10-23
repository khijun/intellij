package edu.du.sb1023_3.service;

import edu.du.sb1023_3.entity.AnsweredData;
import edu.du.sb1023_3.entity.Respondent;
import edu.du.sb1023_3.entity.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Service
@RequiredArgsConstructor
public class SurveyService {

    final EntityManagerFactory emf;

    public void save(AnsweredData ans) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(ans);

        Response res1 = ans.getResponses().get(0);
        Response res2 = ans.getResponses().get(1);
        Response res3 = ans.getResponses().get(2);

        em.persist(res1);
        em.persist(res2);
        em.persist(res3);

        Respondent resp = ans.getRes();

        em.persist(resp);

        res1.setAnswerId(ans);
        res2.setAnswerId(ans);
        res3.setAnswerId(ans);

        ans.setRes(resp);

//        resp.setAnswerId(ans);

        System.out.println("결과: "+ ans);

        tx.commit();
    }
}
