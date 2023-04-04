package Extra;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

/**
 * @author KevinKeyss
 */
public class MiCheckBox extends JCheckBox{

    public MiCheckBox() {
        this.setIcon(new ImageIcon(getClass().getResource("uncheck-blue.png")));
        this.setSelectedIcon(new ImageIcon(getClass().getResource("check-blue.png")));
        this.setOpaque(false);
    }
    
}
