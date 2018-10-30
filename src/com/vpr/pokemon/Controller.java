package com.vpr.pokemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class Controller implements ActionListener, MouseListener, ListSelectionListener{
	
	//constantes
	final String DEFAULT_IMG = System.getProperty("user.dir") + File.separator + "icons" + File.separator + "pokeball.png";
	
	//atributos
	private View view;
	private Model model;
	private File ficheroSeleccionado;
	
	public Controller(Model model, View view) {
		this.view = view;
		this.model = model;
		
		addListeners(); //añado el listener
		poblarTiposPokemon();
		refrescarLista();
		modoEdicion(false);
		
	}
	
	//Metodos
	//metodo para rellenar el combo box de la ventana
	private void poblarTiposPokemon() {
		for(Tipo tipo: Tipo.values())
			view.cbTipo.addItem(tipo);
	}
	
	//escucho que han clickado en el elemento
	public void addListeners() {
		//botones
		view.btNuevo.addActionListener(this);
		view.btGuardar.addActionListener(this);
		view.btEditar.addActionListener(this);
		view.btCancelar.addActionListener(this);
		view.btBorrar.addActionListener(this);
		
		//pulsar etiqueta imagen
		view.lbImagen.addMouseListener(this);
		
		//evento cuando se pulsa en la lista
		view.listPokemones.addListSelectionListener(this);
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
		
		case "nuevo":
			view.btNuevo.setEnabled(false);
			limpiarTexto();
			modoEdicion(true);
			break;
			
		
		//click en boton añadir
		case "editar":
			
			break;
		case "guardar":
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
				
			//almacen el nombre de la imagen
			String nombreImagen;
			if(ficheroSeleccionado != null) //si el fichero seleccionado no es null
				nombreImagen = ficheroSeleccionado.getName();
			else
				nombreImagen = DEFAULT_IMG; //pongo una imagen por defecto si no selecciona ninguna
			
			String nombre = view.tfNombre.getText();
			Tipo tipo = (Tipo) view.cbTipo.getSelectedItem();
			int nivel = Integer.parseInt(view.tfNivel.getText());
			float peso = Float.parseFloat(view.tfPeso.getText());
			
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
			
			//limpio todo y pongo el modo edicion false
			limpiarTexto();
			modoEdicion(false);
			
			break;
		case "cancelar":
			modoEdicion(false);
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
			view.tfNombre.setEditable(!view.tfNombre.isEditable());
			view.tfNivel.setEditable(!view.tfNivel.isEditable());
			view.tfPeso.setEditable(!view.tfPeso.isEditable());
		if(activo) {
			view.btNuevo.setEnabled(!activo);
			view.btEditar.setEnabled(!activo);
			view.btGuardar.setEnabled(activo);
			view.btCancelar.setEnabled(activo);
			view.btBorrar.setEnabled(!activo);
			view.listPokemones.setEnabled(!activo);
		}
		else {
			view.btNuevo.setEnabled(!activo);
			view.btEditar.setEnabled(activo);
			view.btGuardar.setEnabled(activo);
			view.btCancelar.setEnabled(activo);
			view.btBorrar.setEnabled(activo);
			view.listPokemones.setEnabled(!activo);
		}
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			view.tfNombre.setText(view.listPokemones.getSelectedValue().getNombre());
			view.cbTipo.setSelectedItem(view.listPokemones.getSelectedValue().getTipo());
			view.tfNivel.setText(String.valueOf(view.listPokemones.getSelectedValue().getNivel()));
			view.tfPeso.setText(String.valueOf(view.listPokemones.getSelectedValue().getPeso()));
			view.lbImagen.setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator + "icons" + File.separator + view.listPokemones.getSelectedValue().getImagen()));
		}
	}
	
	//metodo que limpia los text field 
	public void limpiarTexto() {
		view.tfNombre.setText("");
		view.cbTipo.setSelectedIndex(0);
		view.lbImagen.setIcon(new ImageIcon(DEFAULT_IMG));
		view.tfNivel.setText("");
		view.tfPeso.setText("");
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
