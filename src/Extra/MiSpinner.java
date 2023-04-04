package Extra;
import java.awt.Color;
import javax.swing.JSpinner;
/**
 * @author keiss
 */

public class MiSpinner extends JSpinner{

    public MiSpinner() {
        super();
    }

    public Color getColorTexto() {
        return ((JSpinner.DefaultEditor) getEditor()).getTextField().getForeground();
    }

    public void setColorTexto(Color colorTexto) {
        ((JSpinner.DefaultEditor) getEditor()).getTextField().setForeground(colorTexto);
    }

    public Color getColorFondo() {
        return ((JSpinner.DefaultEditor) getEditor()).getTextField().getBackground();
    }

    public void setColorFondo(Color colorFondo) {
        ((JSpinner.DefaultEditor) getEditor()).getTextField().setBackground(colorFondo);
    }
}