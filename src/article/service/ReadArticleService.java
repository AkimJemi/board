package article.service;

import java.sql.Connection;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.connection.ConnectionProvider;

public class ReadArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();

	public ArticleData getArticle(int articleNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			System.out.println("getArticle - articleNum : " + articleNum);
			Article article = articleDao.selectById(conn, articleNum);
			if (article == null) {
				System.out.println("1");
				throw new ArticleNotFoundException();
			}
			ArticleContent content = contentDao.selectById(conn, articleNum);
			if (content == null) {
				System.out.println("2");
				throw new ArticleContentNotFoundException();
			}
			if (increaseReadCount) {
				System.out.println("3");
				articleDao.increaseReadCount(conn, articleNum);
			}
			return new ArticleData(article, content);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
