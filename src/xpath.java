import java.io.File;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alexm
 */
public class xpath {

    public String consulta(File archivo, String codigo) {
        String salida = "";
        
        try {
            //Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Crear un árbol DOM (parsear) con el archivo LibrosXML.xml
            Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(new FileInputStream(archivo)));
            //Crea el objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();
            //Crea un XPathExpression con la consulta deseada
            XPathExpression exp = xpath.compile(codigo);
            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá
            //el resultado como una lista de nodos.
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;

            //recorre la lista para sacar los resultados
            if(codigo.equals("/pilotos/piloto")){
                for(int i =0; i < nodeList.getLength(); i++){
                salida = salida + "\n Escuderia: "+ nodeList.item(i).getAttributes().getNamedItem("escuderia").getNodeValue();
                
                        }
            }
            
            
            

            return salida;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            return "ERROR";
        }
    }

}