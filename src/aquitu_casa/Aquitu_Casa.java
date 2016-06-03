/*
 * PROYECTO FINAL Programación JAVA + BBDD + Entornos de desarrollo
 * Félix Baltanás 1ºDAM -- felixbaltanas@hotmail.com
 * Centro Integral de Formación Los Enlaces 
 * Zaragoza, España
 */
package aquitu_casa;

public class Aquitu_Casa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Comenzamos la aplicación cargando los parámetros para abrir la base de datos local
        // Creamos las variables en el mismo orden que el método
        
        String bd = "acme";
        String user = "user";
        String password = "1234";
        String servidor = "jdbc:mysql://localhost/";
        
        // DataBaseSegura es una clase MUCHO MÁS PROTEGIDA QUE DataBase
        // Se crea el objeto db DataBaseSegura (hijo de DataBase)
        //
        // es como poner Animal oso = nuevo mamífero (hijo heredado de DataBase)
        //
        // Se utilizarán los métodos de DataBase si no están en DataBaseSegura
        
        DataBase db = new DataBaseSegura(bd, user, password, servidor);
       
        // Se intenta (DEVUELVE UN TRUE O FALSE) abrir la base de datos
        
        if (db.abrirConexion()) {
            Ventana v=new Ventana(db);
        }
    }
     // DATOS INHIBIDOS DE LA bbdd remota de 1 and 1 del cliente
        // String servidor = "jdbc:mysql://db621736249.db.1and1.com";
        // String bd = "db621736249";
        // String user = "dbo621736249";
        // String password = "Alejandro";
       
}
