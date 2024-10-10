package edu.du.sb_chap17;

import edu.du.sb_chap17.dao.ArticleDao;
import edu.du.sb_chap17.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SbChap17ApplicationTests {

	@Autowired
	ArticleDao articleDao;

	@Test
	void test1() {
		System.out.println(articleDao.selectCount() + 1);
	}

	@Test
	void test2() {
		List<Article> list = articleDao.select(1, 10);
		for (Article article : list) {
			System.out.println(article);
		}
	}

	@Test
	void test3() throws  Exception{
		Article article = articleDao.selectById(1);
		System.out.println(article);
	}

}
