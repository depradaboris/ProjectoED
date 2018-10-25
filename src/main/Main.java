package main;

import java.awt.EventQueue;

import GUI.VentanaPpal;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new VentanaPpal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
