package com.vpr.pokemon;

import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

public class View {

	JFrame frame;

	public JTextField tfNombre;
	public JTextField tfNivel;
	public JTextField tfPeso;
	public JComboBox<Pokemon.Tipo> cbTipo;
	public JButton btEditar;
	public JScrollPane scrollPane;
	public JList<Pokemon> listPokemones; //especifico que la lista es de la clase Pokemon
	public DefaultListModel<Pokemon> modelPokemones;
	public JLabel lbImagen;
	public JButton btBorrar;
	public JButton btNuevo;
	public JButton btGuardar;
	public JButton btCancelar;
	
	public View() {
		frame = new JFrame();	
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre*");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 33, 60, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo");
		lblNewLabel_1.setBounds(10, 61, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nivel");
		lblNewLabel_2.setBounds(10, 92, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Peso");
		lblNewLabel_3.setBounds(10, 117, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(66, 30, 86, 20);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		tfNivel = new JTextField();
		tfNivel.setBounds(66, 89, 86, 20);
		frame.getContentPane().add(tfNivel);
		tfNivel.setColumns(10);
		
		tfPeso = new JTextField();
		tfPeso.setBounds(66, 114, 86, 20);
		frame.getContentPane().add(tfPeso);
		tfPeso.setColumns(10);
		
		btEditar = new JButton("Editar");
		btEditar.setActionCommand("editar");
		btEditar.setBounds(139, 168, 89, 23);
		frame.getContentPane().add(btEditar);
		
		cbTipo = new JComboBox<>();
		cbTipo.setBounds(66, 58, 86, 20);
		frame.getContentPane().add(cbTipo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 11, 149, 166);
		frame.getContentPane().add(scrollPane);
		
		modelPokemones = new DefaultListModel(); //instancio el modelo que contiene los objetos Pokemon
		listPokemones = new JList<>();
		scrollPane.setViewportView(listPokemones);
		listPokemones.setModel(modelPokemones);
		
		lbImagen = new JLabel("");
		lbImagen.setIcon(new ImageIcon("C:\\Users\\AlumnoT\\Desktop\\pokemon-icon\\pokeball.png"));
		lbImagen.setBorder(new TitledBorder(null, "Imagen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lbImagen.setBounds(191, 30, 60, 64);
		frame.getContentPane().add(lbImagen);
		
		btBorrar = new JButton("Borrar");
		btBorrar.setActionCommand("borrar");
		btBorrar.setBounds(335, 191, 89, 23);
		frame.getContentPane().add(btBorrar);
		
		btNuevo = new JButton("Nuevo");
		btNuevo.setActionCommand("nuevo");
		btNuevo.setBounds(24, 168, 89, 23);
		frame.getContentPane().add(btNuevo);
		
		btGuardar = new JButton("Guardar");
		btGuardar.setActionCommand("guardar");
		btGuardar.setBounds(24, 215, 89, 23);
		frame.getContentPane().add(btGuardar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setActionCommand("cancelar");
		btCancelar.setBounds(139, 215, 89, 23);
		frame.getContentPane().add(btCancelar);
		
		frame.setVisible(true);
		frame.repaint();
	}
}

