package gimnasio;

import Extra.PantallaCargandoMain;
import Interfaz.Login;
import com.sun.awt.AWTUtilities;
/**
 * @author keiss
 */
public class Gimnasio {
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    
    public static void main(String[] args) throws Exception {
        new PantallaCargandoMain();
        Login login = new Login();
        AWTUtilities.setWindowOpaque(login, false);
        login.setVisible(true);
    }
    
}
