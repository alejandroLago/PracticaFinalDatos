import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xp
 */
public class dom {

    Document doc = null;

   public int guardarDOMcomoFILE(String ruta) {
try{
//Crea un fichero llamado salida.xml
    File archivo_xml = new File(ruta);
//Especifica el formato de salida
OutputFormat format = new OutputFormat(doc);
//Especifica que la salida esté indentada.
format.setIndenting(true);
//Escribe el contenido en el FILE
XMLSerializer serializer = new XMLSerializer(new
FileOutputStream(archivo_xml) , format);
serializer.serialize(doc);
return 0;
}
catch(Exception e) {
return -1;
}
}
    
    
    
    public int abrir_XML_DOM(File fichero) {

        try {
            //crea un objeto DocumentBuiderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = (Document) builder.parse(fichero);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String recorrerDOMyMostrar() {

        String datos_nodo[] = null;
        String salida = "";
        Node node;

        //Obtiene el primer nodo del DOM (primer hijo)
        Node raiz = doc.getFirstChild();

        //Obtiene una lista de nodos con todos los nodos hijo del raiz
        NodeList nodelist = raiz.getChildNodes();

        //Proceso los nodos hijo
        for (int i = 0; i < nodelist.getLength(); i++) {

            node = nodelist.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                //Es un nodo libro
                datos_nodo = procesarPiloto(node);

                salida = salida + "\n" + "Publicado en:" + datos_nodo[0];
                salida = salida + "\n" + "EL autor es:" + datos_nodo[2];
                salida = salida + "\n" + "EL titulo es:" + datos_nodo[1];
                salida = salida + "\n ---------------";

            }
        }
        return salida;

    }

    private String[] procesarPiloto(Node n) {

        String datos[] = new String[3];
        Node ntemp = null;
        int contador = 1;

        //obtiene el valor del primer atributo del nodo(uno en este ejemplo)
        datos[0] = n.getAttributes().item(0).getNodeValue();

        //Obtiene los hijos del libro (titulo y autor)
        NodeList nodos = n.getChildNodes();

        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                //IMPORTANTE: para obtener el texto con el titulo y autor se accede al
                //nodo TEXT hijo de ntemp y se saca su valor.
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }

        return datos;

    }

    public int annadirDOM(String nombre, String nacionalidad,String edad,String titulos,String carreras,String puntosCarnet ,String escuderia,String coche,String numero) {
        try {
            // se crea un nodo tipo Element con nombre 'nombre'(<nombre>)
            Node nNombre = doc.createElement("nombre");

            //Se crea un nodo tipo texto con el nombre del nombre
            Node nNombre_text = doc.createTextNode(nombre);

            //se añade el nodo de texto con el nombre como hijo del elemento nombre
            nNombre.appendChild(nNombre_text);

            // (<nacionalidad>)
            Node nNacionalidad = doc.createElement("nacionalidad");
            Node nNacionalidad_text = doc.createTextNode(nacionalidad);
            nNacionalidad.appendChild(nNacionalidad_text);
            
            // (<edad>)
            Node nEdad = doc.createElement("edad");
            Node nEdad_text = doc.createTextNode(edad);
            nEdad.appendChild(nEdad_text);
            
            // (<titulos>)
            Node nTitulos = doc.createElement("titulos");
            Node nTitulos_text = doc.createTextNode(titulos);
            nTitulos.appendChild(nTitulos_text);
            
            // (<carreras>)
            Node nCarreras = doc.createElement("carreras");
            Node nCarreras_text = doc.createTextNode(carreras);
            nCarreras.appendChild(nCarreras_text);
            
            // (<puntosCarnet>)
            Node nPuntosCarnet = doc.createElement("puntosCarnet");
            Node nPuntosCarnet_text = doc.createTextNode(puntosCarnet);
            nPuntosCarnet.appendChild(nPuntosCarnet_text);
            
            
            
            
            

            //Se crea un nodo de tipo elemento (<piloto>)
            Node nPiloto = doc.createElement("piloto");

            //Al nuevo nodo libro se le añade los atributos
            ((Element) nPiloto).setAttribute("escuderia", escuderia);
            ((Element) nPiloto).setAttribute("coche", coche);
            ((Element) nPiloto).setAttribute("numero", numero);

            //Se añade a piloto los nodos creados anteriormente
            nPiloto.appendChild(nNombre);
            nPiloto.appendChild(nNacionalidad);
            nPiloto.appendChild(nEdad);
            nPiloto.appendChild(nTitulos);
            nPiloto.appendChild(nCarreras);
            nPiloto.appendChild(nPuntosCarnet);

            //Se obtiene el primer nodo del documento y a ek se ke añade como
            //hijo el nodo libro que ya tiene colgando todos sus hijos y atributos creados antes.
            Node raiz = doc.getChildNodes().item(0);
            raiz.appendChild(nPiloto);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

 

}