package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import article.model.ArticleContent;
import util.JdbcUtil;

public class ArticleContentDao {
	public ArticleContent insert(Connection conn, ArticleContent content) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into article_content (article_no, content) values ( ?,?)");
			ps.setLong(1, content.getNumber());
			ps.setString(2, content.getContent());
			int insertedCount = ps.executeUpdate();
			if (insertedCount > 0) {
				return content;
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(ps);
		}
	}

	public ArticleContent insert(Connection conn, int no) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from article_content where article_no = ?");
			ps.setInt(1, no);
			rs = ps.executeQuery();
			ArticleContent content = null;
			if (rs.next()) {
				content = new ArticleContent(rs.getInt("article_no"), rs.getString("content"));
			}
			return content;
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
	}

	public int update(Connection conn, int no, String content) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update article_content set content = ? where article_no = ?")) {
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	public int delete(Connection conn, int no, String content) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("delete from article_content where article_no = ?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}

	public ArticleContent selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select * from article_content where article_no= ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ArticleContent content = null;

			while (rs.next()) {
				content = new ArticleContent(rs.getInt("article_no"), rs.getString("content"));
			}
			return content;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	

}
