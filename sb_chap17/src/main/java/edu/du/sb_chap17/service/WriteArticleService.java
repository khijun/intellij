package edu.du.sb_chap17.service;

import edu.du.sb_chap17.dao.ArticleDao;
import edu.du.sb_chap17.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

@Service
public class WriteArticleService {

	@Autowired
	IdGenerator idGenerator;
	@Autowired
	ArticleDao articleDao;

	public Article write(WritingRequest writingRequest)
			throws IdGenerationFailedException {

		try{
		int groupId = idGenerator.generateNextId("article");

		Article article = writingRequest.toArticle();

		article.setGroupId(groupId);
		article.setPostingDate(new Date());
		DecimalFormat decimalFormat = new DecimalFormat("0000000000");
		article.setSequenceNumber(decimalFormat.format(groupId) + "999999");

			int articleId = articleDao.insert(article);
			if (articleId == -1) {
				throw new RuntimeException("DB ���� ����: " + articleId);
			}

			article.setId(articleId);
			return article;
		} catch (SQLException e) {
			throw new RuntimeException("DB ����: " + e.getMessage(), e);
		}
	}
}
