package Extra;

import javax.swing.ImageIcon;

public class PantallaCargandoMain {

    PantallaCargando screen;

    public PantallaCargandoMain() {
        inicioPantalla();
        screen.velocidadDeCarga();
    }

    private void inicioPantalla() {
        ImageIcon myImage = new ImageIcon("C:\\Users\\keiss\\Documents\\NetBeans\\Gimnasio\\src\\Complementos\\Logo.jpg");
        screen = new PantallaCargando(myImage);
        screen.setLocationRelativeTo(null);
        screen.setProgresoMax(100);
        screen.setVisible(true);
    }

    public static void main(String[] args){
        new PantallaCargandoMain();
    }
}