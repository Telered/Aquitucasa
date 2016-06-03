package aquitu_casa;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristina
 */
public final class VentanaListado extends JFrame {

    JPanel contenedor;
    JTable table;
    DefaultTableModel modelo;
    ResultSet rs;

    public VentanaListado(ResultSet rs) {
        this.rs = rs;
        this.setTitle("Listado de Propietarios");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(1200, 300);
        this.setLocation(35, 250);
    }
    
    
    

    public void initComponents() {
        contenedor = (JPanel) this.getContentPane();
        modelo = new DefaultTableModel();
        // se crea la Tabla con el modelo DefaultTableModel
        table = new JTable(modelo);
        //Creamos un JscrollPane y le agregamos la JTable
        JScrollPane scrollPane = new JScrollPane(table);
        //Agregamos el JScrollPane al contenedor
        contenedor.add(scrollPane, BorderLayout.CENTER);
        // insertamos los nombres de las celdas cabecera.
        modelo.addColumn("Nombre");
        modelo.addColumn("Nif");
        modelo.addColumn("Profesión");
        modelo.addColumn("Dirección");
        modelo.addColumn("Estado civil");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Móvil");
        modelo.addColumn("e-mail");
        modelo.addColumn("Web");
        modelo.addColumn("Fax");
        //modelo.addColumn("Localidad");
        muestraFilas();
    }

    public void muestraFilas() {
        String fila[] = new String[11];
        try {
            while (rs.next()) {
                //tenemos que crear un array
                fila[0] = rs.getString(2);
                fila[1] = rs.getString(3);
                fila[2] = rs.getString(4);
                fila[3] = rs.getString(5);
                fila[4] = rs.getString(6);
                if(rs.getString(6).equals("S")){fila[4]="Soltero";}
                if(rs.getString(6).equals("C")){fila[4]="Casado";}
                if(rs.getString(6).equals("V")){fila[4]="Viudo";}
                if(rs.getString(6).equals("D")){fila[4]="Divorciado";}
                fila[5] = rs.getString(7);
                fila[6] = rs.getString(8);
                fila[7] = rs.getString(9);
                fila[8] = rs.getString(11);
                fila[9] = rs.getString(12);
              /*
                
                Utilizaría esto para poner la localidad pero no me termina de funcionar
                       
                fila[10] = DataBase.saberLocalidad(rs.getString(13));
                
                */
                
                
                
                
                
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}


