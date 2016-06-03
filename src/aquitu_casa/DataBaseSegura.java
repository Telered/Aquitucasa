/*
 * PROYECTO FINAL Programación JAVA + BBDD + Entornos de desarrollo
 * Félix Baltanás 1ºDAM -- felixbaltanas@hotmail.com
 * Centro Integral de Formación Los Enlaces 
 * Zaragoza, España
 */
package aquitu_casa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseSegura extends DataBase {

    // Esta clase está extendida a DataBase (extends DataBase...{ )
    
    // Este método eleva los parámetros a método en Clase padre
    
    public DataBaseSegura(String bd, String login, String password, String servidorMysql) {
        super(bd, login, password, servidorMysql);
    }
    
    @Override
    public boolean buscaRegistro(String nombreBuscar) {
        ResultSet rs;
            PreparedStatement st;
            //Sustituimos la variable por un ?
            String sentencia = "SELECT * from propietario where RAZON_SOCIAL like ? limit 100" ;
            System.out.println(sentencia);
        try {
            
            
            st = conexion.prepareStatement(sentencia);
            //Pasamos los valores a cada uno de los interrogantes
            //comenzamos numerando el 1
            st.setString(1,"%"+ nombreBuscar + "%");
            //st.setInt(2,20);  asi seria si tuvieramos un segundo interrogante donde esperamos un numero entero
            rs = st.executeQuery();
            if (rs.isBeforeFirst()) { //es la manera de capturar tanto si devuelve algun valor como si no
                VentanaListado vL = new VentanaListado(rs);
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
        return true;
    }
    
    
}
