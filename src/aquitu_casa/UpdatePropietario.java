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
import java.sql.Statement;
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

public class UpdatePropietario extends JFrame implements ActionListener, WindowListener {
    String posicion;
    JPanel contenedor;
    JButton botonAlta, botonFin, botonBorrar;
    JTextField nombre, nif, profesion, direccion, estado, telefono, movil, email, password, rpass, pagina_web, fax, localidad_id_loc;
    JLabel etiquetaNombre, etiquetaNif, etiquetaProfesion, etiquetaDireccion, etiquetaEstado, etiquetaTelefono, etiquetaMovil, etiquetaEmail, etiquetaPassword, etiquetaRpass, etiquetaPagina_web, etiquetaFax, etiquetaComunidad, etiquetaProvincia, etiquetaLocalidad;
    DataBase db;
    JComboBox menucc, menuprov, menu;

    public UpdatePropietario(ResultSet rs,DataBase db) throws SQLException {
        //El título lo recibirá como parámetro para modificar ese Propietario
        this.db = db;
        this.setTitle("Modificación de Propietario");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(500, 500);
        this.setLocation(400, 300);
        rs.next();
        posicion = rs.getString(1);
        nombre.setText(rs.getString(2));
        nif.setText(rs.getString(3));
        profesion.setText(rs.getString(4));
        direccion.setText(rs.getString(5));
        estado.setText(rs.getString(6));
        telefono.setText(rs.getString(7));
        movil.setText(rs.getString(8));
        email.setText(rs.getString(9));
        password.setText(rs.getString(10));
        rpass.setText(rs.getString(10));
        pagina_web.setText(rs.getString(11));
        fax.setText(rs.getString(12));
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Utilizo todo el fondo del JFrame
        contenedor = (JPanel) this.getContentPane();
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(13, 2));
        //Inicializo los objetos
        etiquetaNombre = new JLabel("Razón social: ");
        nombre = new JTextField();
        etiquetaNif = new JLabel("NIF: ");
        nif = new JTextField();
        etiquetaProfesion = new JLabel("Profesión: ");
        profesion = new JTextField();
        etiquetaDireccion = new JLabel("Dirección: ");
        direccion = new JTextField();
        etiquetaEstado = new JLabel("Estado civil (S,C,V): ");
        estado = new JTextField();
        etiquetaTelefono = new JLabel("Teléfono: ");
        telefono = new JTextField();
        etiquetaMovil = new JLabel("Móvil: ");
        movil = new JTextField();
        etiquetaEmail = new JLabel("e-mail: ");
        email = new JTextField();
        etiquetaPassword = new JLabel("Password: ");
        password = new JTextField();
        etiquetaRpass = new JLabel("Repite password: ");
        rpass = new JTextField();
        etiquetaPagina_web = new JLabel("Página Web: ");
        pagina_web = new JTextField();
        etiquetaFax = new JLabel("Fax: ");
        fax = new JTextField();

        etiquetaComunidad = new JLabel("Comunidad Autónoma: ");
        etiquetaProvincia = new JLabel("Provincia: ");

        etiquetaLocalidad = new JLabel("Localidad: ");
        localidad_id_loc = new JTextField();
        botonAlta = new JButton("Modificar");
        botonAlta.addActionListener(this);
        botonAlta.setActionCommand("alta");
        botonFin = new JButton("Fin");
        botonFin.addActionListener(this);
        botonFin.setActionCommand("fin");
        botonBorrar = new JButton("Borrar");
        botonBorrar.addActionListener(this);
        botonBorrar.setActionCommand("borrar");
        //los pongo en el contendor
        contenedor.add(etiquetaNombre);
        contenedor.add(nombre);
        contenedor.add(etiquetaNif);
        contenedor.add(nif);
        contenedor.add(etiquetaProfesion);
        contenedor.add(profesion);
        contenedor.add(etiquetaDireccion);
        contenedor.add(direccion);
        contenedor.add(etiquetaEstado);
        contenedor.add(estado);
        contenedor.add(etiquetaTelefono);
        contenedor.add(telefono);
        contenedor.add(etiquetaMovil);
        contenedor.add(movil);
        contenedor.add(etiquetaEmail);
        contenedor.add(email);
        contenedor.add(etiquetaPassword);
        contenedor.add(password);
        contenedor.add(etiquetaRpass);
        contenedor.add(rpass);
        contenedor.add(etiquetaPagina_web);
        contenedor.add(pagina_web);
        contenedor.add(etiquetaFax);
        contenedor.add(fax);
        
        /*
        contenedor.add(etiquetaComunidad);

        String sql;
        sql = "select * from comunidad_autonoma";
        ResultSet rs = db.ejecutaConsulta(sql);

        ArrayList<String> comunidades = new ArrayList<>();
        int x = 0;
        try {
            while (rs.next()) {
                comunidades.add(rs.getString(2));

            }
        } catch (SQLException ex) {
            System.out.println("Fallo: " + ex);
        }

        String[] comunidadesx = new String[comunidades.size()];
        comunidadesx = comunidades.toArray(comunidadesx);

        menucc = new JComboBox<>(comunidadesx);
        menucc.addActionListener(this);
        menucc.setActionCommand("comunidad");
        contenedor.add(menucc);

        contenedor.add(etiquetaProvincia);

        String sql2;
        int g = menucc.getSelectedIndex() + 1;
        sql2 = "SELECT * FROM PROVINCIA WHERE COMUNIDAD_AUTONOMA_ID_COM = " + g;
        rs = db.ejecutaConsulta(sql2);

        ArrayList<String> provincias = new ArrayList<>();
        x = 0;
        try {
            while (rs.next()) {
                provincias.add(rs.getString(2));

            }
        } catch (SQLException ex) {
            System.out.println("Fallo: " + ex);
        }

        String[] provinciasx = new String[provincias.size()];
        provinciasx = provincias.toArray(provinciasx);

        menuprov = new JComboBox<>(provinciasx);
        menuprov.addActionListener(this);
        menuprov.setActionCommand("provincia");
        contenedor.add(menuprov);

        contenedor.add(etiquetaLocalidad);

        String sql4;
        sql4 = "select * from LOCALIDAD where provincia_id_prov = 4 order by NOMBRE asc ";
        rs = db.ejecutaConsulta(sql4);

        ArrayList<String> localidades = new ArrayList<>();
        x = 0;
        try {
            while (rs.next()) {
                localidades.add(rs.getString(4));

            }
        } catch (SQLException ex) {
            System.out.println("Fallo: " + ex);
        }

        String[] localidadesx = new String[localidades.size()];
        localidadesx = localidades.toArray(localidadesx);

        menu = new JComboBox<>(localidadesx);
        menu.addActionListener(this);
        menu.setActionCommand("localidad");
        contenedor.add(menu);
*/
        contenedor.add(botonAlta);
        contenedor.add(botonFin);
        //contenedor.add(botonBorrar);
        
        

    }

    private void limpiaPantalla() {
        nombre.setText(null);
        nif.setText(null);
        profesion.setText(null);
        direccion.setText(null);
        estado.setText(null);
        telefono.setText(null);
        movil.setText(null);
        email.setText(null);
        password.setText(null);
        rpass.setText(null);
        pagina_web.setText(null);
        fax.setText(null);
        localidad_id_loc.setText(null);

    }

    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    private void modificacion(DataBase bd) {
        //Creo un objeto de la clase persona, pero lo instancio como
        //alumno, de esta manero
        if (compruebaCadena20(nombre.getText())) {
            if (compruebaTelefono(telefono.getText())) {
                if (compruebaNif(nif.getText())) {
                    //lo guardo en la base de datos
                    String cadena = "UPDATE propietario SET RAZON_SOCIAL='"+ nombre.getText()+ "', NIF='" + nif.getText() 
                            + "',PROFESION='"+profesion.getText()+"', DIRECCION='"+direccion.getText()+"', ESTADO_CIVIL='"+estado.getText()+"', TELEFONO='"
                            +telefono.getText()+"', MOVIL='"+movil.getText()+"', EMAIL='"+email.getText()+"', PASSWORD='"+password.getText()+"', PAGINA_WEB='"
                            +pagina_web.getText()+"', FAX='"+fax.getText()+"' where ID_PROP="+posicion;
                    System.out.println(cadena);
                
               bd.ejecutaConsulta(cadena);
                    System.out.println("RESUELTO");
               
                } else {
                    ventanaError("El NIF tiene que tener entre 6 y 15 car.");
                }
            } else {
                ventanaError("El teléfono tiene que tener entre 9 y 15 car.");
            }
        } else {
            ventanaError("El nombre tiene que tener entre 1 y 20 car.");
        }

    }

    private boolean compruebaCadena20(String cadena) {
        return cadena.length() > 0 && cadena.length() <= 20;
    }

    private boolean compruebaTelefono(String cadena) {
        return cadena.length() > 8 && cadena.length() <= 15;
    }
    private boolean compruebaNif(String cadena) {
        return cadena.length() > 6 && cadena.length() <= 15;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "alta":
                modificacion(db);
                break;
            case "borrar":
                limpiaPantalla();
                break;

            case "comunidad":
                menuprov.removeAllItems();
                int g;
                String sql;
                g = menucc.getSelectedIndex() + 1;
                sql = "SELECT * FROM PROVINCIA WHERE COMUNIDAD_AUTONOMA_ID_COM = " + g;
                ResultSet rs = db.ejecutaConsulta(sql);

                ArrayList<String> provincias = new ArrayList<>();

                try {
                    while (rs.next()) {
                        menuprov.addItem(rs.getString(2));

                    }
                } catch (SQLException ex) {
                    System.out.println("Fallo en provincia + " + ex);
                }

                // ------------------------------------------------------------------------------------------------
                menu.removeAllItems();

                int j;
                String sql3,
                 sql4;
                j = menucc.getSelectedIndex() + 1;
                sql3 = "select min(id_prov) from provincia where COMUNIDAD_AUTONOMA_ID_COM= " + j;
                System.out.println(sql3);
                rs = db.ejecutaConsulta(sql3);
                String u = "";
                try {
                    while (rs.next()) {
                        u = rs.getString(1);
                    }
                } catch (SQLException ex) {
                    System.out.println("error + " + ex);
                }

                sql4 = "SELECT * FROM localidad WHERE PROVINCIA_ID_PROV = " + u;
                System.out.println(sql4);
                rs = db.ejecutaConsulta(sql4);

                ArrayList<String> localidades = new ArrayList<>();

                try {
                    while (rs.next()) {

                        menu.addItem(rs.getString(4));

                    }
                } catch (SQLException ex) {
                    System.out.println("Fallo en provincia + " + ex);
                }

                // ------------------------------------------------------------------------------------------------
                break;

            case "provincia":
                menu.removeAllItems();
                String h;
                String sql1,
                 sql2;
                h = (String) menuprov.getSelectedItem();
                System.out.println("h es" + h);
                sql1 = "select id_prov from provincia where nombre = '" + h + "'";
                System.out.println(sql1);
                rs = db.ejecutaConsulta(sql1);
                String y = "";
                try {
                    while (rs.next()) {
                        y = rs.getString(1);
                    }
                } catch (SQLException ex) {
                    System.out.println("error + " + ex);
                } catch (NullPointerException npe) {
                    
                }

                sql2 = "SELECT * FROM localidad WHERE PROVINCIA_ID_PROV = " + y;
                System.out.println(sql2);
                rs = db.ejecutaConsulta(sql2);

                ArrayList<String> localidades2 = new ArrayList<>();

                try {
                    while (rs.next()) {
                        String t = rs.getString(4);
                        localidades2.add(t);
                        menu.addItem(t);

                    }
                } catch (SQLException ex) {
                    System.out.println("Fallo en provincia + " + ex);
                } catch (NullPointerException npe) {
                    
                }

                break;

            case "fin":
                this.dispose();
                break;

            default:
             this.dispose();
                break;
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
