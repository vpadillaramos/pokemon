package com.vpr.pokemon;

import java.io.*;

import javax.swing.JOptionPane;

public class Aplicacion {
	public static void main(String[] args) {
		View view = new View();
		
		try{
			Model model = new Model();
			Controller controller = new Controller(model, view);
		}
		catch(IOException ioe) {
			JOptionPane.showMessageDialog(null, "Error de acceso al fichero", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		catch(ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "Error en el formato de guardado", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
}
