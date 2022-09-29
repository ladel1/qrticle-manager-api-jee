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
		return null;
	}

	@Override
	public void insert(Article article) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement  stmt =cnx.prepareStatement("INSERT INTO articles (nom,price) VALUES(?,?)");
		stmt.setString(1, article.getNom());
		stmt.setDouble(2, article.getPrix());
		stmt.executeUpdate();		
	}

}
