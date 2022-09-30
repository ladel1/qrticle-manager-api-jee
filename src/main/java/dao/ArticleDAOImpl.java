package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import api.bo.Article;
import tools.ConnectionProvider;

public class ArticleDAOImpl implements ArticleDAO {

	@Override
	public Article selectOne(int pk) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement  stmt =cnx.prepareStatement("SELECT * FROM articles WHERE id = ?");
		stmt.setInt(1, pk);
		ResultSet  rs =stmt.executeQuery();
		if(rs.next()) {
			Article a = new Article(rs.getInt("id"),rs.getDouble("price"),rs.getString("nom"));
			cnx.close();
			return a;
		}
		cnx.close();
		return null;
	}

	@Override
	public void insert(Article article) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement  stmt =cnx.prepareStatement("INSERT INTO articles (nom,price) VALUES(?,?)");
		stmt.setString(1, article.getNom());
		stmt.setDouble(2, article.getPrix());
		stmt.executeUpdate();	
		stmt.close();
		cnx.close();
	}

	@Override
	public void delete(int pk) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement  stmt =cnx.prepareStatement("DELETE FROM articles WHERE id = ?");
		stmt.setInt(1, pk);
		stmt.executeUpdate();	
		stmt.close();
		cnx.close();
		
	}

	@Override
	public void update(Article article) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement  stmt =cnx.prepareStatement("UPDATE articles SET nom= ?,price = ? WHERE id = ?");
		stmt.setString(1, article.getNom());
		stmt.setDouble(2, article.getPrix());
		stmt.setDouble(3, article.getId());
		stmt.executeUpdate();	
		stmt.close();
		cnx.close();
		
	}

}
