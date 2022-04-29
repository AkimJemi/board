package article.service;

import article.model.Article;
import article.model.ArticleContent;

public class ArticleData {

	private Article article;
	private ArticleContent content;

	public ArticleData(Article acticle, ArticleContent content) {
		this.article = acticle;
		this.content = content;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getContent() {
		return content.getContent();
	}

	public void setContent(ArticleContent content) {
		this.content = content;
	}
}