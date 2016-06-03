/*
 * Propiedad de Félix Baltanás Rubio, estudiante 1DAM
 * Centro Integral de Formación Los Enlaces
 * Zaragoza, España -- felixbaltanas@gmail.com
 */
package aquitu_casa;

import java.sql.ResultSet;

/**
 *
 * Ya con tranquilidad, después de haber entregado el proyecto, primero a las 23:57 por 
 * la vía ONG y después a las 00:03 del día seguiente,  voy a continuar en él,
 * ya que no me tengo por uno de esos que estudian para sacar el "título", sino para aprender
 * 
 * Y si ahora Cristina me considera suspenso, pues seré suspenso, pero el proyecto lo habré acabado
 * para en el futuro, extraer código, como siempre....
 * 
 * 
 * Me decido a crear una clase para ejecutar bien el XML después
 * 
 */
public class Propietario {
    private String razon_social;
    private String telefono;
    private String movil;
    private String email;
 
    public Propietario(ResultSet rs){
        this.razon_social= razon_social;
        this.telefono=telefono;
        this.movil=movil;
        this.email=email;
 
    }

    
    
    /**
     * @return the razon_social
     */
    public String getRazon_social() {
        return razon_social;
    }

    /**
     * @param razon_social the razon_social to set
     */
    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the movil
     */
    public String getMovil() {
        return movil;
    }

    /**
     * @param movil the movil to set
     */
    public void setMovil(String movil) {
        this.movil = movil;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    
    
    
    
    
    
    
}
