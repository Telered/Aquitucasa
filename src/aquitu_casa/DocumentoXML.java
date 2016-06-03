/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aquitu_casa;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

/**
 *
 * @author Daniel
 */
public class DocumentoXML {

    public static Document pasarXmlADom(String nombreFichero) {
        Document doc = null;
        try {

            // 1º creamos una nueva instancia de un fabrica de constructores de documentos
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 2º A partir de la instancia anterior, fabricamos un constructor de documentos, que procesara el XML
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 3º Procesamos el documento (almacenado en un archivo) y lo convertimos en un arbol DOM.
            doc = db.parse(new File(nombreFichero));
            return doc;
        } catch (ParserConfigurationException ex) {
            System.out.println("Error");
        } catch (SAXException ex) {
            System.out.println("Error");
        } catch (IOException ex) {
            System.out.println("Error");
        }
        // Es necesario que la variable doc este fuera del try si quiero devolverla en caso de error doc valdra null
        return doc;
    }

    public static void leoDoc(String nombreFichero) {
        Document miDoc = pasarXmlADom(nombreFichero);
        if (miDoc != null) {
            // Seleccionar el elemento raiz raiz
            //PARTE1
            System.out.println("Listado de Propietarios");
            Element raiz = miDoc.getDocumentElement();
            System.out.println("El nodo raiz es: " + raiz.getNodeName());
            NodeList listadeHijos = miDoc.getElementsByTagName("Propietario");
            System.out.println(listadeHijos.getLength());
            for (int x = 0; x < listadeHijos.getLength(); x++) {

                System.out.println("Nombre: " + listadeHijos.item(x).getTextContent());

            }
            //PARTE2
            System.out.println("PARTE 2");
            NodeList actores = miDoc.getElementsByTagName("actor");
            for (int x = 0; x < actores.getLength(); x++) {
                Node actor = actores.item(x);
                System.out.println(actor.getNodeName() + ": " + actor.getTextContent());
            }

            //PARTE3
            //Utilizamos la clase Element que hereda la clase Node.
            System.out.println("PARTE 3");
            NodeList pelis = miDoc.getElementsByTagName("movie");
            for (int x = 0; x < pelis.getLength(); x++) {
                //en peli, tenemos la etiqueta movie entera

                //Lo convertimos en Element
                Element peli = (Element) pelis.item(x);
                System.out.println("Tipo " + peli.getAttribute("type"));
                //podemos ver el titutlo de esa pelicula
                //Es el item(0) porque cada pelicula solo tiene 1
                System.out.println("Titulo: " + peli.getElementsByTagName("title").item(0).getTextContent());
                //para mostrar todos sus actores
                System.out.println("ACTORES");
                actores = peli.getElementsByTagName("actor");
                for (int y = 0; y < actores.getLength(); y++) {
                    Node actor = actores.item(y);
                    System.out.println(actor.getNodeName() + ": " + actor.getTextContent());

                }

            }

            miDoc.getDoctype();
            // mostramos el nodo raiz
        }

    }

    public static void escribo(String nombreDocumento, DataBase base) throws ParserConfigurationException, SQLException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;

        try {

            String sql;
            sql = "select * from propietario ";
            ResultSet rs = base.ejecutaConsulta(sql);
            /* ArrayList<String> propietarios = new ArrayList<>();
             int x = 0;
             try {
             while (rs.next()) {
             propietarios.add(rs.getString(0));
             }
             } catch (SQLException ex) {
             System.out.println("Fallo: " + ex);
             }
             */

            // sin datos, solo el elemento raiz
            db = dbf.newDocumentBuilder();
            //  Creamos el documento XML y le pasamos la etiqueta raiz
            DOMImplementation implementation = db.getDOMImplementation();
            Document document = implementation.createDocument(null, nombreDocumento, null);// null seria espacio de nombres y DTD (opcionales)
            document.setXmlVersion("1.0");
            //Main Node: primer ejemplos, solo con el elemento raiz
            Element raiz = document.getDocumentElement();
            System.out.println("Raiz: " + raiz.getNodeName());
               //  ahora creamos un elemento con los datos del array
            //  por cada nombre crearemos una <persona>

            // ListIterator it = db.listIterator();
            //  int i = 0;
            //rs.isBeforeFirst();
            while (rs != null && rs.isLast()==false) {
 rs.next(); 
               // if (rs.isBeforeFirst()) {System.out.println("SI"); rs.next(); }

                    Element etiquetaPersona = document.createElement("Propietario");
                    etiquetaPersona.setAttribute("Codigo", rs.getString(1));
                    //System.out.println("Nombre " + rs.getString(1));
                    Element etiquetaNombre = document.createElement("Nombre");
                    Text valorNombre = document.createTextNode(rs.getString(2));
                    etiquetaNombre.appendChild(valorNombre);
                    System.out.println("Nombre " + rs.getString(2));
                    Element etiquetaApellidos = document.createElement("Nif");
                    Text valorApellidos = document.createTextNode(rs.getString(3));
                    etiquetaApellidos.appendChild(valorApellidos);

                    Element etiquetaDireccion = document.createElement("Direccion");
                    Text valorDireccion = document.createTextNode(rs.getString(5));
                    etiquetaDireccion.appendChild(valorDireccion);

                    Element etiquetaTelefono = document.createElement("Telefono");
                    Text valorTelefono = document.createTextNode(rs.getString(7));
                    etiquetaTelefono.appendChild(valorTelefono);

                    Element etiquetaMovil = document.createElement("Movil");
                    Text valorMovil = document.createTextNode(rs.getString(8));
                    etiquetaMovil.appendChild(valorMovil);

                    Element etiquetaEmail = document.createElement("email");
                    Text valorEmail = document.createTextNode(rs.getString(9));
                    etiquetaEmail.appendChild(valorEmail);

                    etiquetaPersona.appendChild(etiquetaNombre);
                    etiquetaPersona.appendChild(etiquetaApellidos);
                    etiquetaPersona.appendChild(etiquetaDireccion);
                    etiquetaPersona.appendChild(etiquetaTelefono);
                    etiquetaPersona.appendChild(etiquetaMovil);
                    etiquetaPersona.appendChild(etiquetaEmail);

                    //añadimos la etiqueta persona a la etiqueta raiz
                    raiz.appendChild(etiquetaPersona);//pegamos el elemento a la raiz "Documento"
                      
                      
                     
               
           }
                //Generate XML
                Source source = new DOMSource(document);
                //Indicamos donde lo queremos almacenar
                Result result = new StreamResult(new java.io.File(nombreDocumento + ".xml"));
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(source, result);

            

        } catch (TransformerConfigurationException es) {
            System.out.println("Error en la transformacion: " + es);
        } catch (TransformerException et) {
            System.out.println("Error 2 en la transformacion: " + et);
        } finally {
            System.out.println("Finalmenta, la transformacion");
        }

        System.out.println(
                "DOCUMENTO XML CREADO");
        System.exit(
                0);

    }
}
