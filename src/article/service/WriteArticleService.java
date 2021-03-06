package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.connection.ConnectionProvider;
import util.JdbcUtil;

public class WriteArticleService {
private ArticleDao articleDao = new ArticleDao();
private ArticleContentDao contentDao = new ArticleContentDao();

public Integer write( WriteRequest req, int num) {
	Connection conn = null;
	try {
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		Article article = toArticle(req);
		Article savedArticle = null;
		ArticleContent content =null;
		ArticleContent savedContent =null;
		
		if(num == 1) {
			savedArticle=  articleDao.insert(conn, article);
			content =new ArticleContent(savedArticle.getNumber(), req.getContent());
			savedContent = contentDao.insert(conn, content);
		}else {
			for(int i = 0; i<num ; i++) {
				savedArticle=  articleDao.insert(conn, article);
				content =new ArticleContent(savedArticle.getNumber(), req.getContent());
				savedContent = contentDao.insert(conn, content);
			}
		}
		if(savedArticle==null) {
			throw new RuntimeException();
		}
		if(savedContent == null) {
			throw new RuntimeException("fail to insert article_content");
		}
		conn.commit();
		return savedArticle.getNumber();
		
	}catch (SQLException e) {
		JdbcUtil.rollback(conn);
		throw new RuntimeException(e);
	}catch (RuntimeException e) {
		JdbcUtil.rollback(conn);
		throw e;
	}finally {
		JdbcUtil.rollback(conn);
	}
}

private Article toArticle(WriteRequest req) {
	Date now = new Date();
	return new Article(null, req.getWriter(), req.getTitle(), now, now, 0);
}
}
