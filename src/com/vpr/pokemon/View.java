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
	public JButton btAnadir;
	public JScrollPane scrollPane;
	public JList<Pokemon> listPokemones; //especifico que la lista es de la clase Pokemon
	public DefaultListModel<Pokemon> modelPokemones;
	public JLabel lbImagen;
	
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
		lblNewLabel_1.setBounds(10, 72, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nivel");
		lblNewLabel_2.setBounds(10, 116, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Peso");
		lblNewLabel_3.setBounds(10, 163, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(66, 30, 86, 20);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		tfNivel = new JTextField();
		tfNivel.setBounds(66, 113, 86, 20);
		frame.getContentPane().add(tfNivel);
		tfNivel.setColumns(10);
		
		tfPeso = new JTextField();
		tfPeso.setBounds(66, 160, 86, 20);
		frame.getContentPane().add(tfPeso);
		tfPeso.setColumns(10);
		
		btAnadir = new JButton("A\u00F1adir");
		btAnadir.setActionCommand("anadir");
		btAnadir.setBounds(63, 210, 89, 23);
		frame.getContentPane().add(btAnadir);
		
		cbTipo = new JComboBox<>();
		cbTipo.setBounds(66, 69, 86, 20);
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
		
		frame.setVisible(true);
		frame.repaint();
	}
}

