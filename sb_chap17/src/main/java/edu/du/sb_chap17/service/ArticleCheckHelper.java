package edu.du.sb_chap17.service;

import edu.du.sb_chap17.dao.ArticleDao;
import edu.du.sb_chap17.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class ArticleCheckHelper {

	@Autowired
	ArticleDao articleDao;

	public Article checkExistsAndPassword(int articleId,
										  String password) throws SQLException, ArticleNotFoundException,
			InvalidPasswordException {

		Article article = articleDao.selectById(articleId);
		if (article == null) {
			throw new ArticleNotFoundException(
					"존재하지 않는 계정 : " + articleId);
		}
		if (!checkPassword(article.getPassword(), password)) {
			throw new InvalidPasswordException("비밀번호 불일치");
		}
		return article;
	}

	private boolean checkPassword(String realPassword, 
			String userInputPassword) {
		if (realPassword == null) {
			return false;
		}
		if (realPassword.length() == 0) {
			return false;
		}
		return realPassword.equals(userInputPassword);
	}

}
