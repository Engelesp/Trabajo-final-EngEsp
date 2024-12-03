package Trabajofinal;

import gui.Inicio;

public class MainGUI {
    public static void main(String[] args) {
        
    	// Inicia la pantalla de Inicio   
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new Inicio().setVisible(true);
            }
        });
    }
}
