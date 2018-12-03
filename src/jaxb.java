
import java.io.File;
import java.util.List;
import generated.Pilotos;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xp
 */
public class jaxb {
     
    Pilotos mispiloto;
    List<Pilotos.Piloto> pilotos;
    
 public int abrir_XML_JAXB (File fichero) {
        JAXBContext contexto;
        
        try{
            //creamos una instancia JAXB
            contexto = JAXBContext.newInstance(Pilotos.class);
            
            //creamos un objeto Ubnaesheller
            
            Unmarshaller u = contexto.createUnmarshaller();
            
            //desalizamos el fichero
            mispiloto = (Pilotos)u.unmarshal(fichero);
            pilotos = mispiloto.getPiloto();
            
            
            return 0;
            
            
            
        }
        catch (Exception ex){
            ex.printStackTrace();
            return -1;
        }
        
        
        
    }
 
 
 
 
 
 
  public int guardar_XML_JAXB (File fichero) {
        JAXBContext contexto;
        
        try{
            //creamos una instancia JAXB
            contexto = JAXBContext.newInstance(Pilotos.class);
            
            //creamos un objeto Ubnaesheller
            
            Marshaller u = contexto.createMarshaller();
            
        
            u.setProperty(u.JAXB_FORMATTED_OUTPUT, true);
            u.marshal(mispiloto, fichero);
            
            
            return 0;
            
            
            
        }
        catch (Exception ex){
            ex.printStackTrace();
            return -1;
        }
        
        
        
    }
  
 
    
    
    public String recorrerJAXByMostrar(){
String datos_nodo[]=null;
String cadena_resultado="";
//Crea una lista con objetos de tipo libro.
pilotos = mispiloto.getPiloto();
//Recorre la lista para sacar los valores.
for (int i=0; i<pilotos.size(); i++){
cadena_resultado = cadena_resultado+"\n" + "La escuderia es: "+pilotos.get(i).getEscuderia();
cadena_resultado= cadena_resultado + "\n" +"El coche es: " + pilotos.get(i).getCoche();
cadena_resultado= cadena_resultado + "\n" +"EL numero es: " + pilotos.get(i).getNumero();
cadena_resultado = cadena_resultado+"\n" + "Su nombre es: "+pilotos.get(i).getNombre();
cadena_resultado= cadena_resultado + "\n" +"Su nacionalidad es: " + pilotos.get(i).getNacionalidad();
cadena_resultado= cadena_resultado + "\n" +"SU edad es: " + pilotos.get(i).getEdad();
cadena_resultado = cadena_resultado+"\n" + "Titulos ganados: "+pilotos.get(i).getTitulos();
cadena_resultado= cadena_resultado + "\n" +"Carreras hechas: " + pilotos.get(i).getCarreras();
cadena_resultado= cadena_resultado + "\n" +"Puntos en el carnet: " + pilotos.get(i).getPuntosCarnet();


cadena_resultado = cadena_resultado +"\n ----------------------------";
}
return cadena_resultado;
}
    
    
    

    
    
    
    
    
}
