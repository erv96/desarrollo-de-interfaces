package application;

import javafx.beans.property.SimpleStringProperty;

public class Editorial {
	
	private int id;
	private SimpleStringProperty nombre;
	private SimpleStringProperty fundador;
	private SimpleStringProperty fecha;
	

	public Editorial(String nombre, String fundador, String fecha) {
		super();
		this.nombre = new SimpleStringProperty(nombre);
		this.fundador = new SimpleStringProperty(fundador);
		this.fecha = new SimpleStringProperty(fecha);
	}
	
	public Editorial(int id,String nombre, String fundador, String fecha) {
		super();
		this.id = id;
		this.nombre = new SimpleStringProperty(nombre);
		this.fundador = new SimpleStringProperty(fundador);
		this.fecha = new SimpleStringProperty(fecha);
	}
	
	

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre.get();
	}


	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}


	public String getFundador() {
		return fundador.get();
	}


	public void setFundador(String fundador) {
		this.fundador = new SimpleStringProperty(fundador);
	}


	public String getFecha() {
		return fecha.get();
	}


	public void setFecha(String fecha) {
		this.fecha = new SimpleStringProperty(fecha);
	}
	
	
	
	
	
}
