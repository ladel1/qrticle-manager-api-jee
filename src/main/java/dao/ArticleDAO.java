package dao;

import java.sql.SQLException;

import api.bo.Article;

public interface ArticleDAO {
	public Article selectOne(int pk) throws SQLException;
	public void insert(Article article) throws SQLException;
	public void delete(int pk)throws SQLException;
	public void update(Article article)throws SQLException;
}
