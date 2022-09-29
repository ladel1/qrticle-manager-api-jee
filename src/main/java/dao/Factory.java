package dao;

public class Factory {
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOImpl();
	}
	
}
