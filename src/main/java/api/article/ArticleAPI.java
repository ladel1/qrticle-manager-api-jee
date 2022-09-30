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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticles() {
		try {			
			// 200
			/**
			 * HTTP
			 * Headers 
			 * Body json
			 */
			return Response.ok(Factory.getArticleDAO().selectAll()).header("Access-Control-Allow-Origin", "*").build();
		} catch (SQLException e) {
			return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	
	@GET 
	@Path("/{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticle(@PathParam("id") int id) {		
		try {			
			// 200
			/**
			 * HTTP
			 * Headers 
			 * Body json
			 */
			return Response.ok(Factory.getArticleDAO().selectOne(id)).header("Access-Control-Allow-Origin", "*").build();
		} catch (SQLException e) {
			return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response ajouter(@FormParam("nom") String nom,@FormParam("prix") String prix) {
		System.out.println(nom);
		System.out.println(prix);
		try {
			Factory.getArticleDAO().insert(new Article(Double.valueOf(prix),nom));
			return Response.status(201).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
		}
		
	}
	//
	// annotation delete ne fonctionne avec angular (headers)
	@GET 
	@Path("/delete/{id: \\d+}")
	public Response supprimerArticle(@PathParam("id") int id) {
		try {
			System.out.println("in try");
			Factory.getArticleDAO().delete(id);
			return Response.status(204).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			System.out.println("in catch");
			System.out.println(e.getMessage());
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
