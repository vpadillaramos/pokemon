package com.vpr.pokemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import com.vpr.pokemon.Pokemon.Tipo;
import com.vpr.pokemon.util.Util;;

public class Controller implements ActionListener, MouseListener{
	private View view;
	private Model model;
	private File ficheroSeleccionado;
	
	public Controller(Model model, View view) {
		this.view = view;
		this.model = model;
		
		addListeners(); //añado el listener
		poblarTiposPokemon();
		refrescarLista();
		
	}
	
	//Metodos
	//metodo para rellenar el combo box de la ventana
	private void poblarTiposPokemon() {
		for(Tipo tipo: Tipo.values())
			view.cbTipo.addItem(tipo);
	}
	
	//escucho que han clickado en el elemento
	public void addListeners() {
		view.btAnadir.addActionListener(this);
		view.lbImagen.addMouseListener(this);
		view.listPokemones.addMouseListener(this);
	}
	
	public void refrescarLista() {
		view.modelPokemones.removeAllElements();
		
		for(Pokemon p: model.getPokemones())
			view.modelPokemones.addElement(p);
	}
	
	//con esto le asigno a cada boton una accion
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		//click en boton añadir
		case "anadir":
			//si el nombre esta vacio se acaba la accion
			if(view.tfNombre.getText().equals("")) {
				//salta una ventana indicando que el nombre es obligatorio
				//con el ERROR_MESSAGE sale un icono
				JOptionPane.showMessageDialog(null, "El campo nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				return; //con esto ya se acaba la accion
			}
			
			if(view.tfNivel.getText().equals(""))
				view.tfNivel.setText("0");
			if(!model.isNumeric(view.tfNivel.getText())) {
				JOptionPane.showMessageDialog(null, "El nivel debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
				view.tfNivel.selectAll(); //selecciono el texto para que pueda escribir directamente
				view.tfNivel.requestFocus(); //el cursor se situa directamente en el campo
				return;
			}
			if(view.tfPeso.getText().equals(""))
				view.tfPeso.setText("0");
			
			if(!model.isNumeric(view.tfPeso.getText())) {
				JOptionPane.showMessageDialog(null, "El peso debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
				view.tfPeso.selectAll();
				view.tfPeso.requestFocus();
				return;
			}
				
			
			Tipo tipo = (Tipo) view.cbTipo.getSelectedItem();
			int nivel = Integer.parseInt(view.tfNivel.getText());
			float peso = Float.parseFloat(view.tfPeso.getText());
			
			//almacen el nombre de la imagen
			String nombreImagen;
			if(ficheroSeleccionado != null) //si el fichero seleccionado no es null
				nombreImagen = ficheroSeleccionado.getName();
			else
				nombreImagen = "pokeball.png"; //pongo una imagen por defecto si no selecciona ninguna
			
			String nombre = view.tfNombre.getText();
			Pokemon pokemon = new Pokemon();
			pokemon.setNombre(nombre);
			pokemon.setTipo(tipo);
			pokemon.setNivel(nivel);
			pokemon.setPeso(peso);
			pokemon.setImagen(nombreImagen);
			
			
			//control repeticion de pokemon
			if(model.buscaPokemon(nombre)) {
				JOptionPane.showMessageDialog(null, "Pokemon ya existente","Registro existente", JOptionPane.INFORMATION_MESSAGE);
			}
				
			else {
				
				try {
					try {
						Util.copiarImagen(ficheroSeleccionado.getAbsolutePath(), nombreImagen);
					}catch(IOException ioe) {
						ioe.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					pokemon.setImagen(ficheroSeleccionado.getAbsolutePath() + File.separator + nombreImagen);
					
					//GUARDAR POKEMON
					model.guardar(pokemon);
					refrescarLista();
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al guardar el Pokemon", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				//mensaje pokemon añadido
				JOptionPane.showMessageDialog(null, "Pokemon añadido", "Completo", JOptionPane.INFORMATION_MESSAGE);
			}
			
			break;
			/*
		case "borrar":
			ListSelectionListener borrar = new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting()) {
						Pokemon pk = view.listPokemones.getSelectedValue();
					}
				}
				
			};
			break;*/
			
		default:
			
			break;
		}
	}
	
	private void modoEdicion(boolean activo) {
		if(activo) {
			view.tfNombre.setEditable(true);
			view.tfNivel.setEditable(true);
			view.tfPeso.setEditable(true);
		}
		else {
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//con esto detecto que el origen del evento clickado es el label de la imagen
		if(e.getSource() == view.lbImagen) {
			//creo una ventana para buscar el fichero, es una ventana modal
			JFileChooser jfc = new JFileChooser();
			
			//con el siguiente metodo y esta constante sabremos si el usuario le ha dado a cancelar
			if(jfc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
				return;
			
			ficheroSeleccionado = jfc.getSelectedFile(); //guardo el fichero seleccionado
			view.lbImagen.setIcon(new ImageIcon(ficheroSeleccionado.getAbsolutePath())); //seteo la imagen al icono de la imagen
		}
		else if(e.getSource() == view.listPokemones) {
			view.lbImagen.setIcon(new ImageIcon());
		}
		
	}
	
	//Otros metodos del MouseListener
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
