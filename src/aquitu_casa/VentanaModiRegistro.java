package aquitu_casa;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
/**
 *
 * @author Cristina
 */
public final class VentanaModiRegistro extends JFrame implements ActionListener, WindowListener {

    JPanel contenedor;
    JTable table;
    DefaultTableModel modelo;
    ResultSet rs;
    DataBase bd;
    Object[][] dtPersona;
    
    public VentanaModiRegistro(ResultSet rs) {
        this.rs = rs;
        this.setTitle("Listado de Propietarios");
        this.setVisible(true);
        this.setSize(1200, 300);
        this.setLocation(35, 250);
        initComponents();
         this.pack();
    }
    
    

    
    public void Actualizar_Tabla(){
              
        //actualiza los datos de la tabla realizando una consulta a la base de datos
        String[] columNames = {"ID_PROP","RAZON_SOCIAL","NIF","PROFESION","DIRECCION","ESTADO_CIVIL","TELEFONO","MOVIL","EMAIL","PASSWORD","PAGINA_WEB","FAX","LOCALIDAD_ID_LOC"};
        dtPersona = bd.SelectPropietario();
        // se colocan los datos en la tabla
        DefaultTableModel datos = new DefaultTableModel(dtPersona,columNames);
        table.setModel(datos);
        
         //oculta columna ID
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        //editor de caldas
        table.getColumnModel().getColumn( 1 ).setCellEditor(new MyTableCellEditor(bd,"ID_PROP"));//Columna Nombre
        table.getColumnModel().getColumn( 2 ).setCellEditor(new MyTableCellEditor(bd,"RAZON_SOCIAL"));//Columna Apellido
        table.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor(bd,"NIF"));//Columna Edad
    
        table.getColumnModel().getColumn( 4 ).setCellEditor(new MyTableCellEditor(bd,"PROFESION"));//Columna Nombre
        table.getColumnModel().getColumn( 5 ).setCellEditor(new MyTableCellEditor(bd,"DIRECCION"));//Columna Apellido
        table.getColumnModel().getColumn( 6 ).setCellEditor(new MyTableCellEditor(bd,"ESTADO_CIVIL"));//Columna Edad
   
        table.getColumnModel().getColumn( 7 ).setCellEditor(new MyTableCellEditor(bd,"TELEFONO"));//Columna Nombre
        table.getColumnModel().getColumn( 8 ).setCellEditor(new MyTableCellEditor(bd,"MOVIL"));//Columna Apellido
        table.getColumnModel().getColumn( 9 ).setCellEditor(new MyTableCellEditor(bd,"EMAIL"));//Columna Edad
   
        table.getColumnModel().getColumn( 10 ).setCellEditor(new MyTableCellEditor(bd,"PASSWORD"));//Columna Nombre
        table.getColumnModel().getColumn( 11 ).setCellEditor(new MyTableCellEditor(bd,"PAGINA_WEB"));//Columna Nombre
        table.getColumnModel().getColumn( 12 ).setCellEditor(new MyTableCellEditor(bd,"FAX"));//Columna Apellido
        table.getColumnModel().getColumn( 13 ).setCellEditor(new MyTableCellEditor(bd,"LOCALIDAD_ID_LOC"));//Columna Edad
   
    }
        
        
        
        
   

    public void initComponents() {
        
       
        
        
        contenedor = (JPanel) this.getContentPane();
           modelo = new DefaultTableModel();
           table = new JTable(modelo);
           contenedor.add(table);
           
     
        // se crea la Tabla con el modelo DefaultTableModel
     
        
        
         System.out.println("YAAAAAAAAAAAAAAA");
        //Creamos un JscrollPane y le agregamos la JTable
        JScrollPane scrollPane = new JScrollPane(table);
        //Agregamos el JScrollPane al contenedor
        contenedor.add(scrollPane, BorderLayout.CENTER);
        // insertamos los nombres de las celdas cabecera.
      /*  modelo.addColumn("Nombre");
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
        muestraFilas(); */
         Actualizar_Tabla();
     
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

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


