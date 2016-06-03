/*
 * PROYECTO FINAL Programación JAVA + BBDD + Entornos de desarrollo
 * Félix Baltanás 1ºDAM -- felixbaltanas@hotmail.com
 * Centro Integral de Formación Los Enlaces 
 * Zaragoza, España
 */
package aquitu_casa;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DataBase {

    String bd;
    String login;
    String password;
    String servidorMysql;
    Connection conexion;
    Object[][] dtPersona;

    /**
     * Esta Clase sirve para conectar con la base de datos SQL local alojada en
     * el PC
     *
     *
     * Se abre un método que this los parámetros. Se crean 4 parámetros String:
     * bd,login,password y servidorMysql
     *
     * @param bd
     * @param login
     * @param password
     * @param servidorMysql
     */
    public DataBase(String bd, String login, String password, String servidorMysql) {

        this.bd = bd;
        this.login = login;
        this.password = password;
        this.servidorMysql = servidorMysql;
    }

    /**
     * Metodo que abre la conexión con la base de datos. Se crea un boleano con
     * el que contestar a abrirConexión: Si se abre, dará true.
     *
     * @return
     */
    public boolean abrirConexion() {

        boolean estado = false;

        try {
            // Se carga el driver con Class.forName("-------------");
            Class.forName("com.mysql.jdbc.Driver");

            // Se crea la conexión:   --> Si da error --> estado sigue en false
            // Se abre el método de DriveManager getConnection
            conexion = DriverManager.getConnection(servidorMysql + bd, login, password);
            estado = true;
        } catch (SQLException e) {
            System.out.println("SQL Exception:\n" + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception:\n" + e.toString());
        } catch (Exception e) {
            System.out.println("Exception:\n" + e.toString());
        }

        return estado;
    }

    /**
     * Metodo que cierra la conexion con la base de datos.
     */
    public void cerrarConexion() {

        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int ejecutaUpdate(String statement) {
        int n = 0;
        try {
            Statement st = conexion.createStatement();
            System.out.println("La sentencia es: " + statement);
            n = st.executeUpdate(statement);
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n" + ex.getMessage());
        }
        return n;
    }

    public ResultSet ejecutaConsulta(String consulta) {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
//        try {
//            st.close();
//        } catch (SQLException ex) {
//            System.out.println("Error sql: " + ex.getMessage());
//        }
        return rs;
    }

    public boolean buscaCasaDePro(String nombreBuscar) throws Exception {
        
        ResultSet rs;
        /*
        SELECT i.direccion FROM inmueble i join pertenece p on (p.Inmueble_ID_INM = i.ID_INM)
        join propietario o on (o.ID_PROP=p.PROPIETARIO_ID_PROP)  WHERE o.RAZON_SOCIAL LIKE '%lix%';
         */
        String sentencia = "SELECT i.direccion FROM inmueble i join pertenece p on (p.Inmueble_ID_INM = i.ID_INM) "
                + "join propietario o on (o.ID_PROP=p.PROPIETARIO_ID_PROP)  WHERE o.RAZON_SOCIAL = '" + nombreBuscar + "';";
        System.out.println(sentencia);
        rs = ejecutaConsulta(sentencia);
         
        try {
            //VIP primero compruebo que rs no es nullo, si lo es, lo segundo no se ejecuta
            if (rs != null) {
                 if (rs.isBeforeFirst()) {
                     boolean next = rs.next();
                               
                     System.out.println(rs.getString(1));
                   
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
        return true; //aunque puede ser que se haya producido la excepción.  contamos conn el mensaje
    }

public boolean modificaRegistro(String nombreBuscar,DataBase bd) {
        ResultSet rs;
        /*
        Directamente la consulta sería así
        rs=db.ejecutaConsulta("SELECT * from alumnos where nombre='" + nombre.getText()+"';");
        Creo un String con la consulta para ver por consola la inyección de código
        probaremos poniendo en el nombre: Pepe' or 1='1
         */
        String sentencia = "SELECT * from propietario where RAZON_SOCIAL like '%" + nombreBuscar + "%';";
        System.out.println(sentencia);
        rs = ejecutaConsulta(sentencia);
        try {
            //VIP primero compruebo que rs no es nullo, si lo es, lo segundo no se ejecuta
            if (rs != null) {
                if (rs.isBeforeFirst()) {
                    UpdatePropietario vL = new UpdatePropietario(rs,bd);
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
        return true; //aunque puede ser que se haya producido la excepción.  contamos conn el mensaje
    }


    
    
    public boolean buscaRegistro(String nombreBuscar) {
        ResultSet rs;
        /*
        Directamente la consulta sería así
        rs=db.ejecutaConsulta("SELECT * from alumnos where nombre='" + nombre.getText()+"';");
        Creo un String con la consulta para ver por consola la inyección de código
        probaremos poniendo en el nombre: Pepe' or 1='1
         */
        String sentencia = "SELECT * from propietario where RAZON_SOCIAL like '%" + nombreBuscar + "%';";
        System.out.println(sentencia);
        rs = ejecutaConsulta(sentencia);
        try {
            //VIP primero compruebo que rs no es nullo, si lo es, lo segundo no se ejecuta
            if (rs != null) {
                if (rs.isBeforeFirst()) {
                    VentanaListado vL = new VentanaListado(rs);
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
        return true; //aunque puede ser que se haya producido la excepción.  contamos conn el mensaje
    }

    public void cierraResultSet(ResultSet rs) {
        try {
            //cerramos el rs. porque garbage no puede eliminar el heap
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
    }

    public void recorreResultado(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        }
    }

    
    /* ESTE MÉTODO NO CONSIGO PONERLO EN MARCHA EN LISTADO GRAL DE PROPIETARIOS*/
    public  String saberLocalidad(String local){
        String esto="";
         esto="SELECT nombre FROM localidad WHERE ID_LOC= " + local;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(esto);
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        }
        try{
            while (rs.next()){
                esto=rs.getString(1);
            }
            } catch (SQLException ex){
                    System.out.println("Fallo en sacar localidad + " + ex);
                   }
        return esto;
    } 
    
    
    /* Realiza una consulta a la base de datos, retorna un Object[][] con los
 * datos de la tabla persona
 */
    public Object[][] SelectPropietario()
    {
         Statement st = null;
         ResultSet rs = null;
     int registros = 0;
      String consulta = "Select ID_PROP,RAZON_SOCIAL,NIF,PROFESION,DIRECCION, ESTADO_CIVIL, TELEFONO, MOVIL"
              + ",EMAIL,PASSWORD,PAGINA_WEB,FAX,LOCALIDAD_ID_LOC FROM propietario";
      String consulta2 = "Select count(*) as total from propietario";
      //where RAZON_SOCIAL LIKE '%"+razon+"%'";
      //obtenemos la cantidad de registros existentes en la tabla
      try{
            st = conexion.createStatement();
            rs = st.executeQuery(consulta2);

         rs.next();
         registros = rs.getInt("total");
         rs.close();
      }catch(SQLException e){
         System.out.println(e);
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][13];
    //realizamos la consulta sql y llenamos los datos en la matriz "Object"
      try{
         PreparedStatement pstm = conexion.prepareStatement(consulta);
         ResultSet rr = pstm.executeQuery();
         int i = 0;
         while(rr.next()){
            data[i][0] = rr.getString( "ID_PROP");
            data[i][1] = rr.getString( "RAZON_SOCIAL" );
            data[i][2] = rr.getString( "NIF" );
            data[i][3] = rr.getString( "PROFESION" );
            data[i][0] = rr.getString( "DIRECCION" );
            data[i][1] = rr.getString( "ESTADO_CIVIL" );
            data[i][2] = rr.getString( "TELEFONO" );
            data[i][3] = rr.getString( "MOVIL" );
            data[i][0] = rr.getString( "EMAIL" );
            data[i][1] = rr.getString( "PASSWORD" );
            data[i][2] = rr.getString( "PAGINA_WEB" );
            data[i][3] = rr.getString( "FAX" );
            data[i][0] = rr.getString( "LOCALIDAD_ID_LOC" );
            
            i++;
         }
         rr.close();
          }catch(SQLException e){
               System.out.println(e);
        }
    return data;
    }

/* Ejecuta la actualizacion de la tabla persona dado los valores de actualizacion
 * y el ID del registro a afectar
 */
    public boolean update(String valores, String id)
    {
        boolean res = false;
        String q = " UPDATE propietario SET " + valores + " WHERE ID_PROP= " + id;
        try {
             PreparedStatement pstm = conexion.prepareStatement(q);
         ResultSet rx = pstm.executeQuery();
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.out.println(e);
        }
        return res;
    }

    
}
    

