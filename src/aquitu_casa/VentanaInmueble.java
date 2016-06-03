/*
 * Cambia tipo de moneda
 */
package aquitu_casa;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaInmueble extends JFrame implements ActionListener, WindowListener {

    JPanel contenedor;
    JButton botonBusca, botonFin;
    JTextField nombre;
    JLabel etiquetaNombre;
    DataBase db;
    JComboBox menu;

    public VentanaInmueble(DataBase db) {
        //El título lo recibirá como parámetro para dar de alta alumnos o profesores
        this.db = db;
        this.setTitle("Buscar Inmueble por Propetario");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300, 150);
        this.setLocation(500, 400);
    }

    private void initComponents() {
        //Utilizo todo el fondo del JFrame
        contenedor = (JPanel) this.getContentPane();
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(2, 2, 5, 5));
        //Inicializo los objetos
        etiquetaNombre = new JLabel("Razón social: ");
        nombre = new JTextField();
        botonBusca = new JButton("Busca");
        botonBusca.addActionListener(this);
        botonBusca.setActionCommand("busca");
        botonFin = new JButton("Fin");
        botonFin.addActionListener(this);
        botonFin.setActionCommand("fin");
        //los pongo en el contendor
        contenedor.add(etiquetaNombre);
        
        String sql;
        sql = "select * from propietario";
        ResultSet rs = db.ejecutaConsulta(sql);

        ArrayList<String> propietarios = new ArrayList<>();
        int x = 0;
        try {
            while (rs.next()) {
                propietarios.add(rs.getString(2));

            }
        } catch (SQLException ex) {
            System.out.println("Fallo: " + ex);
        }

        String[] propietariosx = new String[propietarios.size()];
        propietariosx = propietarios.toArray(propietariosx);

        menu = new JComboBox<>(propietariosx);
        //menu.addActionListener(this);
        //menu.setActionCommand("busca");
        contenedor.add(menu);

       
        contenedor.add(botonBusca);
        contenedor.add(botonFin);
    }

    private void limpiaPantalla() {
        nombre.setText(null);
    }

    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    private void busca() throws Exception {
       // System.out.println("EL NOMBRE ES " + menu.getSelectedItem()) ;
       // System.out.println("ya");
        //le pasamos al método de la base de datos el nombre a buscar
        if (!db.buscaCasaDePro((String) menu.getSelectedItem())) {
            ventanaError("Registro no encontrado");
        } else {
            limpiaPantalla();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "busca":
                
        {
            try {
                // VentanaListado vL=new VentanaListado(db.ejecutaConsulta("SELECT * from propietario where "));
                busca();
            } catch (Exception ex) {
                Logger.getLogger(VentanaInmueble.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
                
                
            default:
                break;
                // this.dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
       
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {       
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
