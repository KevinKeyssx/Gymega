package Extra;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 * @author KevinKeyss
 */
public class MiRender extends DefaultTableCellHeaderRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasfocusm, int row, int column){
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasfocusm, row, column);
        
        try {
            if (column > 0) {
                cell.setText("");
                
                if (Integer.valueOf(value.toString()) == 1){
                    cell.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\build\\classes\\Complementos\\Botones\\Btn-Check.png"));
                }
                else{
                    cell.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\build\\classes\\Complementos\\Botones\\Btn_NoCheck.png"));
                }
            }
            
            cell.setFont(new java.awt.Font("Mairy Oblicua", 0, 20));
            cell.setForeground(new Color(0, 204, 255));
            
        } catch (NumberFormatException e) {
            cell.setBackground(Color.GRAY);
        }

        return cell;
    }
}
