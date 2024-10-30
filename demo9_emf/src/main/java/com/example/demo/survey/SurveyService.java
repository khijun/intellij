package com.example.demo.survey;

import com.example.demo.member.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Service
@Log4j2
public class SurveyService {
    @PersistenceUnit
    private EntityManagerFactory emf;

    public void save(AnsweredData data) {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        log.info(data.toString());
        Respondent respondent = data.getRes();
        em.persist(respondent);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                Member member = em.createQuery("select m from Member m where username = :username",Member.class)
                        .setParameter("username", ((UserDetails) principal).getUsername()).getSingleResult();
//                System.out.println(((UserDetails) principal).getPassword()); 비밀번호 어캐가져옴?ㅋㅋㅅㅂ
                if(member != null
//                        &&(member.getPassword().equals(((UserDetails) principal).getPassword()))
                        )
                    data.setId(member.getId());
                em.persist(data);
                member.setAnsweredData(data);
            }
        }

        transaction.commit();
    }

    public void getMember() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.isAuthenticated()) {
//            Object principal = authentication.getPrincipal();
//
//            if (principal instanceof UserDetails) {
//                UserDetails userDetails = (UserDetails) principal;
//                String username = userDetails.getUsername();
//                System.out.println("유조ㅓ명ㅇ: " + username);
//
//                EntityManager em = emf.createEntityManager();
//                EntityTransaction transaction = em.getTransaction();
//                transaction.begin();
//
//                Member member = em.find(Member.class, username);
//                member.getPassword() ==
//            }
//        }
    }
}
