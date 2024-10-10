package edu.du.sb_chap17.service;

import edu.du.sb_chap17.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DeleteArticleService {

	@Autowired
	ArticleDao articleDao;
	@Autowired
	ArticleCheckHelper checkHelper;

	public void deleteArticle(DeleteRequest deleteRequest)
            throws ArticleNotFoundException, InvalidPasswordException, SQLException {

			checkHelper.checkExistsAndPassword(deleteRequest
					.getArticleId(), deleteRequest.getPassword());

			articleDao.delete(deleteRequest.getArticleId());

	}
}
