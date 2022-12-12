package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Videojuego {
	private int id;
	private SimpleStringProperty titulo;
	private SimpleIntegerProperty precio;
	private SimpleStringProperty consola;
	private SimpleIntegerProperty pegi;
	
	
	public Videojuego(String titulo, int precio, String consola,
			int pegi) {
		super();
		this.titulo = new SimpleStringProperty(titulo);
		this.precio = new SimpleIntegerProperty(precio);
		this.consola = new SimpleStringProperty(consola);
		this.pegi = new SimpleIntegerProperty(pegi);
	}
	
	public Videojuego(int id,String titulo, int precio, String consola,
			int pegi) {
		super();
		this.id = id;
		this.titulo = new SimpleStringProperty(titulo);
		this.precio = new SimpleIntegerProperty(precio);
		this.consola = new SimpleStringProperty(consola);
		this.pegi = new SimpleIntegerProperty(pegi);
	}


	public String getTitulo() {
		return titulo.get();
	}
	
	


	public int getId() {
		return id;
	}

	public void setTitulo(String titulo) {
		this.titulo = new SimpleStringProperty(titulo);
	}


	public int getPrecio() {
		return precio.get();
	}


	public void setPrecio(int precio) {
		this.precio = new SimpleIntegerProperty(precio);
	}


	public String getConsola() {
		return consola.get();
	}


	public void setConsola(String consola) {
		this.consola = new SimpleStringProperty(consola);
	}


	public int getPegi() {
		return pegi.get();
	}


	public void setPegi(int pegi) {
		this.pegi = new SimpleIntegerProperty(pegi);
	}
	
	
	
	
	
}
