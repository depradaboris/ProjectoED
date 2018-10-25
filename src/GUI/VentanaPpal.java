package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;

import main.Logica;

import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class VentanaPpal {

	private Logica logica;
	private JFrame frmSistemaDeArchivos;
	private JTextField txtAddPadre;
	private JTextField textRuta;
	private JTextField textMuestraRuta;
	private JTextField textRaiz;
	private JTextField textAddNew;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPpal window = new VentanaPpal();
					window.frmSistemaDeArchivos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPpal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logica = new Logica();
		frmSistemaDeArchivos = new JFrame();
		frmSistemaDeArchivos.setResizable(false);
		frmSistemaDeArchivos.setTitle("Sistema de archivos");
		frmSistemaDeArchivos.setBounds(100, 100, 520, 400);
		frmSistemaDeArchivos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeArchivos.getContentPane().setLayout(null);
		
		textRaiz = new JTextField();
		textRaiz.setBounds(12, 55, 146, 20);
		frmSistemaDeArchivos.getContentPane().add(textRaiz);
		textRaiz.setColumns(10);
		
		JLabel lblRaiz = new JLabel("Raiz");
		lblRaiz.setBounds(12, 42, 46, 14);
		frmSistemaDeArchivos.getContentPane().add(lblRaiz);

		JButton btnAgregar = new JButton("Agregar nodo");
		btnAgregar.setEnabled(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAddPadre.getText().equals("")||textAddNew.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Ninguno de los campos debe estar vacio", "Error", JOptionPane.WARNING_MESSAGE);
				else {
					logica.AgregarNodo(txtAddPadre.getText(), textAddNew.getText());
				}
			}
		});
		btnAgregar.setBounds(12, 86, 146, 23);
		frmSistemaDeArchivos.getContentPane().add(btnAgregar);
		
		txtAddPadre = new JTextField();
		txtAddPadre.setEnabled(false);
		txtAddPadre.setBounds(13, 130, 145, 20);
		frmSistemaDeArchivos.getContentPane().add(txtAddPadre);
		txtAddPadre.setColumns(10);
		
		JTextPane textConsole = new JTextPane();
		textConsole.setEnabled(false);
		textConsole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textConsole.setEditable(false);
		textConsole.setBounds(194, 75, 310, 166);
		frmSistemaDeArchivos.getContentPane().add(textConsole);
		
		JButton btnPreOrden = new JButton("Pre Orden");
		btnPreOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux="";
				textConsole.setText(aux);
				for(String i:logica.mostrarPreOrden()) {
					aux+=i;
				}
				textConsole.setText(aux);
			}
		});
		btnPreOrden.setEnabled(false);
		btnPreOrden.setBounds(194, 41, 102, 23);
		frmSistemaDeArchivos.getContentPane().add(btnPreOrden);
		
		JButton btnPostOrden = new JButton("Post Orden");
		btnPostOrden.setEnabled(false);
		btnPostOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux="";
				textConsole.setText(aux);
				for(String i:logica.mostrarPostOrden()) {
					aux+=i;
				}
				textConsole.setText(aux);
			}
		});
		btnPostOrden.setBounds(298, 41, 102, 23);
		frmSistemaDeArchivos.getContentPane().add(btnPostOrden);
		
		JButton btnNiveles = new JButton("Por niveles");
		btnNiveles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aux="";
				textConsole.setText(aux);
				for(String i:logica.mostrarPorNiveles()) {
					aux+=i;
				}
				textConsole.setText(aux);
			}
		});
		btnNiveles.setEnabled(false);
		btnNiveles.setBounds(402, 41, 102, 23);
		frmSistemaDeArchivos.getContentPane().add(btnNiveles);
		
		JLabel lblVer = new JLabel("Ver rotulos");
		lblVer.setEnabled(false);
		lblVer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVer.setBounds(307, 11, 93, 23);
		frmSistemaDeArchivos.getContentPane().add(lblVer);
		
		JButton btnMostrarRuta = new JButton("Mostrar Ruta");
		btnMostrarRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s="";
				try{
					if(textRuta.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Ninguno de los campos debe estar vacio", "Error", JOptionPane.WARNING_MESSAGE);
					else {
						s=logica.mostrarRuta();
					}
				}catch(InvalidOperationException e1) {
					JOptionPane.showMessageDialog(null, "El archivo no existe", "Error", JOptionPane.ERROR_MESSAGE);
				}catch(InvalidFileException e2){
					JOptionPane.showMessageDialog(null, "El archivo indicado corresponde a una carpeta", "Error", JOptionPane.WARNING_MESSAGE);
				}
				textMuestraRuta.setText(s);
			}
		});
		btnMostrarRuta.setEnabled(false);
		btnMostrarRuta.setBounds(13, 271, 145, 23);
		frmSistemaDeArchivos.getContentPane().add(btnMostrarRuta);
		
		textRuta = new JTextField();
		textRuta.setEnabled(false);
		textRuta.setBounds(12, 299, 146, 20);
		frmSistemaDeArchivos.getContentPane().add(textRuta);
		textRuta.setColumns(10);
		
		textMuestraRuta = new JTextField();
		textMuestraRuta.setEditable(false);
		textMuestraRuta.setBounds(13, 344, 470, 20);
		frmSistemaDeArchivos.getContentPane().add(textMuestraRuta);
		textMuestraRuta.setColumns(10);
		
		JLabel lblRuta = new JLabel("Ruta");
		lblRuta.setEnabled(false);
		lblRuta.setBounds(14, 328, 46, 14);
		frmSistemaDeArchivos.getContentPane().add(lblRuta);
		
		textAddNew = new JTextField();
		textAddNew.setEnabled(false);
		textAddNew.setColumns(10);
		textAddNew.setBounds(12, 171, 146, 20);
		frmSistemaDeArchivos.getContentPane().add(textAddNew);
		
		JLabel lblPadre = new JLabel("Padre");
		lblPadre.setEnabled(false);
		lblPadre.setBounds(13, 117, 145, 14);
		frmSistemaDeArchivos.getContentPane().add(lblPadre);
		
		JLabel lblNombreNuevo = new JLabel("Nombre nuevo");
		lblNombreNuevo.setEnabled(false);
		lblNombreNuevo.setBounds(12, 156, 146, 14);
		frmSistemaDeArchivos.getContentPane().add(lblNombreNuevo);
		
		JButton btnListarArchivos = new JButton("Listar Archivos");
		btnListarArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux="";
				textConsole.setText(aux);
				for(String i:logica.listarArchivos()) {
					aux+=i;
				}
				textConsole.setText(aux);
			}
		});
		btnListarArchivos.setEnabled(false);
		btnListarArchivos.setBounds(194, 255, 130, 23);
		frmSistemaDeArchivos.getContentPane().add(btnListarArchivos);
		
		JButton btnListarCarpetas = new JButton("Listar Carpetas");
		btnListarCarpetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux="";
				textConsole.setText(aux);
				for(String i:logica.listarArchivos()) {
					aux+=i;
				}
				textConsole.setText(aux);
			}
		});
		btnListarCarpetas.setEnabled(false);
		btnListarCarpetas.setBounds(374, 255, 130, 23);
		frmSistemaDeArchivos.getContentPane().add(btnListarCarpetas);
		
		JButton btnCargarArbol = new JButton("Cargar Arbol");
		btnCargarArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textRaiz.getText().equals(""))
					JOptionPane.showMessageDialog(null, "La Raiz no debe ser vacio", "Error", JOptionPane.WARNING_MESSAGE);
				else {
					logica.cargarArbol(textRaiz.getText());
					textRaiz.setEnabled(false);
					btnCargarArbol.setEnabled(false);
					btnAgregar.setEnabled(true);
					btnPreOrden.setEnabled(true);
					btnPostOrden.setEnabled(true);
					btnNiveles.setEnabled(true);
					btnMostrarRuta.setEnabled(true);
					btnListarArchivos.setEnabled(true);
					btnListarCarpetas.setEnabled(true);
					lblPadre.setEnabled(true);
					lblNombreNuevo.setEnabled(true);
					lblVer.setEnabled(true);
					lblRuta.setEnabled(true);
					txtAddPadre.setEnabled(true);
					textAddNew.setEnabled(true);
					textRuta.setEnabled(true);
					textConsole.enable(true);
				}
			}
		});
		btnCargarArbol.setBounds(12, 11, 146, 23);
		frmSistemaDeArchivos.getContentPane().add(btnCargarArbol);
	}
}

