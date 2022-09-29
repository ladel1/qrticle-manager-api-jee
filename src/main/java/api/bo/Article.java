package api.bo;

import java.io.Serializable;

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -346110690525457941L;
	private int id;	
	private String nom;
	private double prix;
	
	
	public Article() {
		// TODO Auto-generated constructor stub
	}

	public Article(  double prix, String nom) {
		 
		this.prix = prix;
		this.nom = nom;
	}
	
	public Article(int id, double prix, String nom) {
		this.id = id;
		this.prix = prix;
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
