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
			return Response.ok(Factory.getArticleDAO().selectOne(id)).header("Access-Control-Allow-Origin", "*").build();
		} catch (SQLException e) {
			return null;
		}
	}
	
	@POST
	public String ajouter(@FormParam("title") String title,@FormParam("price") String price) {
		try {
			Factory.getArticleDAO().insert(new Article(Double.valueOf(price),title));
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "notok";
		}
		
	}
	
	@DELETE @Path("/{id: \\d+}")
	public String supprimerArticle(@PathParam("id") int id) {
		return "ok";
	}
	
	@PUT @Path("/{id: \\d+}")
	public String modifierArticle(@PathParam("id") int id,@FormParam("nom") String nom) {
		return "ok "+nom;
	}
	
}
