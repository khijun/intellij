package edu.du.sb1023;

import edu.du.sb1023.entity.Member;
import edu.du.sb1023.entity.Team;
import org.apache.tomcat.util.http.parser.EntityTag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;

@SpringBootTest
class Sb1023ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void test_순수객체() {
        Member member1 = new Member("member1", "홍길동");
        Member member2 = new Member("member2", "강하나");
        Team team = new Team("team1", "팀1");

        member1.setTeam(team);
        member2.setTeam(team);
        System.out.println(member1);
        System.out.println(member2);
    }

    @Test
    void test_save(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member1 = new Member("member1", "홍길동");
        Member member2 = new Member("member2", "강하나");
        Team team = new Team("team1", "팀1");
        member1.setTeam(team);
        member2.setTeam(team);

        em.persist(member1);
        em.persist(member2);
        em.persist(team);

        tx.commit();
    }
    @Test
    void test_find(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String sql = "select m from Member m join m.team t where t.name = :teamName";
        List<Member> resultList = em.createQuery(sql, Member.class).setParameter("teamName", "팀1").getResultList();
        System.out.println(resultList);

        tx.commit();
    }

    @Test
    void test_save2(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member1 = new Member("member1", "홍길동");
        Member member2 = new Member("member2", "강하나");
        Team team = new Team("team1", "팀1");
        member1.setTeam(team);
        member2.setTeam(team);

        em.persist(member1);
        em.persist(member2);
        em.persist(team);

        tx.commit();
    }

    @Test
    void test_find2(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = em.find(Member.class, "member1");
        System.out.println(member);
        System.out.println(member.getTeam());

        tx.commit();
    }

    @Test
    void test_update(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(team2);

        tx.commit();
    }

    @Test
    void test_연관관계제거(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);

        tx.commit();
    }

    @Test
    void test_연관엔티티삭제(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        String sql = "select m from Member m where m.team.name = :teamName";
        List<Member> members = em.createQuery(sql, Member.class).setParameter("teamName", "팀2").getResultList();
        members.forEach(member -> member.setTeam(null));

        Team team2 = em.find(Team.class, "team2");
        em.remove(team2);

        tx.commit();
    }

    @Test
    void test_양방향_탐색(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = em.find(Team.class, "team1");
        team.getMembers().forEach(System.out::println);

        tx.commit();
    }
}
