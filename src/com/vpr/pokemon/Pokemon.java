package com.vpr.pokemon;

import java.io.Serializable;

public class Pokemon implements Serializable{
	//Atributos
	public enum Tipo{
		PLANTA, TIERRA, AGUA, FUEGO
	}
	private String nombre;
	private Tipo tipo;
	private int nivel;
	private float peso;
	private String imagen;
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public String toString() {
		return nombre+" "+tipo;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	/*
	public boolean comparaPokemon(Pokemon pk) {
		if(
		this.nombre.equals(pk.getNombre())
		)
	}*/
}
