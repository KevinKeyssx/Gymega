package Extra;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author keiss
 */
public class ImgTabla extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (value instanceof JLabel) {
            JLabel lbl_Foto = (JLabel) value;
            return lbl_Foto;
        }
                
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
