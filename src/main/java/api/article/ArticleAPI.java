package api.article;

import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import api.bo.Article;
import dao.Factory;

@Path("/articles")
public class ArticleAPI {
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getArticles() {
//		Article [] articles = {
//				new Article(1, 800,"Samsung" ),
//				new Article(2, 900,"Iphone" ),
//				new Article(3, 600,"Huawei" )
//		};
//		return Response.ok(articles);
//	}
	
	
	@GET 
	@Path("/{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticles(@PathParam("id") int id) {		
		try {			
			// 200
			/**
			 * HTTP
			 * Headers 
			 * Body json
			 */
			return Response.ok(Factory.getArticleDAO().selectOne(id)).header("Access-Control-Allow-Origin", "*").build();
		} catch (SQLException e) {
			return null;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response ajouter(@FormParam("nom") String nom,@FormParam("prix") String prix) {
		try {
			Factory.getArticleDAO().insert(new Article(Double.valueOf(prix),nom));
			return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
		}
		
	}
	
	@DELETE @Path("/{id: \\d+}")
	public Response supprimerArticle(@PathParam("id") int id) {
		try {
			Factory.getArticleDAO().delete(id);
			return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	@PUT
	public Response modifierArticle(@FormParam("id") int id,@FormParam("nom") String nom,@FormParam("prix") String prix) {
		try {
			Factory.getArticleDAO().update(new Article(id,Double.valueOf(prix),nom));
			return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
}
