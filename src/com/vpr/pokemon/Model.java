package com.vpr.pokemon;

import java.io.*;
import java.util.*;

import javax.swing.DefaultListModel;

public class Model {
	private HashMap<String, Pokemon> pokemones;
	
	//constructor
	public Model() throws IOException, ClassNotFoundException{
		if(new File("pokemones.dat").exists())
			deserializaHM();
		else
			pokemones = new HashMap<String, Pokemon>();
		
	}
	
	//Metodos
	public void guardar(Pokemon pokemon) throws IOException {
		pokemones.put(pokemon.getNombre(), pokemon);
		serializaHM();
	}
	
	public boolean hmIsEmpty() {
		return pokemones.isEmpty();
	}
	
	public void eliminar(Pokemon pokemon) {
		
	}
	
	public void modificiar(Pokemon pokemon, String nombre) {
		
	}
	
	public Pokemon getPokemon(String nombre) {
		return null;
	}
	
	public ArrayList<Pokemon> getPokemones(){
		
		return new ArrayList<Pokemon>(pokemones.values());
	}
	
	public void eliminartodo() {
		
	}
	
	public boolean isNumeric(String cadena) {
		boolean resultado = false;
		if(cadena.matches("\\d*"))	//esto es una expresion regular que comprueba si son numeros
			resultado = true;
		return resultado;
	}
	
	//devuelve true si lo encuentra en el hashmap
	public boolean buscaPokemon(String clave){
		return pokemones.containsKey(clave)?true:false;
	}
	
	//todo metodo que compare objetos Pokemon
	public boolean buscaPokemon2(Pokemon valor) {
		return pokemones.containsValue(valor)?true:false;
	}
	
	//serializa el HashMap de pokemones
	public void serializaHM() throws IOException{
		
		ObjectOutputStream serializador = null;
		
		serializador = new ObjectOutputStream(new FileOutputStream("pokemones.dat"));
		serializador.writeObject(pokemones);
		serializador.close();
	}
	
	public void deserializaHM() throws IOException,ClassNotFoundException{
		//Variables
		ObjectInputStream deserializador = null;
		
		//Cuerpo
		deserializador = new ObjectInputStream(new FileInputStream("pokemones.dat"));
		pokemones = (HashMap<String, Pokemon>) deserializador.readObject();
		deserializador.close();
	}
}
