/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xp
 */
import java.io.File;
import javax.swing.JFileChooser;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xp
 */
public class sax {
    
    SAXParser parser;
    ManejadorSAX sh;
    File fXML;
    
    public int abrir_XML_SAX(File fichero) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            parser = factory.newSAXParser();

            sh = new ManejadorSAX();
            fXML = fichero;
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    class ManejadorSAX extends DefaultHandler {

        int ultimoelement;
        String cadena_resultado = "";

        public ManejadorSAX() {
            ultimoelement = 0;
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes atts) throws SAXException {
            if (qName.equals("pilotos")){
                cadena_resultado = cadena_resultado + "Se va a mostrar los pilotos de este documento \n" ;
                cadena_resultado = cadena_resultado + "-------------------" ;
            }
            if (qName.equals("piloto")) {
                cadena_resultado = cadena_resultado + "\nEscuderia: " +atts.getValue(atts.getQName(0)) + "\n ";
                ultimoelement = 1;
                cadena_resultado = cadena_resultado + "\nCoche: " +atts.getValue(atts.getQName(1)) + "\n ";
                ultimoelement = 2;
                cadena_resultado = cadena_resultado + "\nNumero: " +atts.getValue(atts.getQName(2)) + "\n ";
                ultimoelement = 3;
            } else if (qName.equals("nombre")) {
                ultimoelement = 4;
                cadena_resultado = cadena_resultado + "\nNombre: ";
            } else if (qName.equals("nacionalidad")) {
                ultimoelement = 5;
                cadena_resultado = cadena_resultado + "\nNacionalidad: ";
            }else if (qName.equals("edad")) {
                ultimoelement = 6;
                cadena_resultado = cadena_resultado + "\nEdad: ";
            }else if (qName.equals("titulos")) {
                ultimoelement = 7;
                cadena_resultado = cadena_resultado + "\nTitulos: ";
            }else if (qName.equals("carreras")) {
                ultimoelement = 8;
                cadena_resultado = cadena_resultado + "\nNumero de carreras: ";
            }else if (qName.equals("puntosCarnet")) {
                ultimoelement = 9;
                cadena_resultado = cadena_resultado + "\nPuntos del carnet: ";
            }
        }

        
//Cuando en este ejemplo se detecta el final de un elemento <libro>,
//se pone un línea discontinua en la salida.
        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            if (qName.equals("piloto")) {
                System.out.println("He encontrado el final de un elemento.");
                cadena_resultado = cadena_resultado + "\n -------------------";
            }
        }
//Cuando se detecta una cadena de texto posterior a uno de los elementos
//<titulo> o <autor> entonces guarda ese texto en la variable correspondiente.

        @Override
        public void characters(char[] ch, int start, int length) throws
                SAXException {
            if (ultimoelement == 4) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 5) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            }else if (ultimoelement == 6) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            }else if (ultimoelement == 7) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            }else if (ultimoelement == 8) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            }else if (ultimoelement == 9) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            }
        }
    }

    public String recorrerSAX() {
        try {
            parser.parse(fXML, sh);
            return sh.cadena_resultado;
        } catch (SAXException e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        }
    }

}