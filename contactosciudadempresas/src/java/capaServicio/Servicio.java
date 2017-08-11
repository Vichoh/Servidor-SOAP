/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaServicio;

import capaNegocio.Ciudad;
import capaNegocio.Contacto;
import capaNegocio.Empresa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.orm.PersistentException;

/**
 *
 * @author Adrian
 */
@WebService(serviceName = "Servicio")
public class Servicio {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "agregarContactoServicioWeb")
    
    public String agregarContactoServicioWeb(@WebParam(name = "nombreContacto") String nombreContacto, 
            @WebParam(name = "apellidoContacto") String apellidoContacto, 
            @WebParam(name = "telefonoContacto") String telefonoContacto, 
            @WebParam(name = "mailContacto") String mailContacto, 
            @WebParam(name = "nombreEmpresa") String nombreEmpresa, 
            @WebParam(name = "nombreCiudad") String nombreCiudad) {
        
        String respuesta ="no se puede almacenar cotacto";
        Empresa empresa = new Empresa();
        empresa.setNombre(nombreEmpresa);
        
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(nombreCiudad);
        
        Contacto contacto = new  Contacto();
        contacto.setNombre(nombreContacto);
        contacto.setApellido(apellidoContacto);
        contacto.setCiudad(ciudad);
        contacto.setEmpresa(empresa);
        contacto.setMail(mailContacto);
        contacto.setTelefono(telefonoContacto);
        try{
            int resultado = contacto.agregarContactoCapaNegocio(contacto);
            if (resultado != 0) {
                respuesta = "creacion  del contacto exitoso, su uid  es " + resultado;
                                
            }
        }catch(PersistentException ex){
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,null, ex);
        }
                
        
        return respuesta;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editarContactoServicioWeb")
    public String editarContactoServicioWeb(@WebParam(name = "uidContacto") int uidContacto, 
            @WebParam(name = "nuevoNombreContacto") String nuevoNombreContacto,
            @WebParam(name = "nuevoApellidoContacto") String nuevoApellidoContacto, 
            @WebParam(name = "nuevoTelefonoContacto") String nuevoTelefonoContacto, 
            @WebParam(name = "nuevoMailContacto") String nuevoMailContacto)
            {
        
        String respuesta ="no se puede editar cotacto";
        
        Contacto contacto = new  Contacto();
        contacto.setNombre(nuevoNombreContacto);
        contacto.setApellido(nuevoApellidoContacto);
        contacto.setMail(nuevoMailContacto);
        contacto.setTelefono(nuevoTelefonoContacto);
        contacto.setUid(uidContacto);
        try{
            int resultado = contacto.editarContactoCapaNegocio(contacto);
            if (resultado != 0) {
                respuesta = "cambio del contacto exitoso, su uid  es " + resultado;
                                
            }
        }catch(PersistentException ex){
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,null, ex);
        }
                
        
        return respuesta;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eliminarContactoServicioWeb")
    public String eliminarContactoServicioWeb(@WebParam(name = "uidContacto") int uidContacto) throws PersistentException {
        
        String respuesta ="no se puede eliminar cotacto";
        Contacto contacto = new  Contacto();
        contacto.setUid(uidContacto);
        
        
        
        try {
            int resultado = contacto.eliminarContactoCapaNegocio(contacto);
            if (resultado != 0) {
                respuesta = "se ha eliminado el contacto cuyo uid  es " + resultado;
            }
        } catch (PersistentException ex){
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,null, ex);
               }
        
        return respuesta;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "busquedaAvanzada")
    public String busquedaAvanzada(@WebParam(name = "nombre") String nombre, 
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "mail") String mail) {
        
        String listaResultado = "";
        List<capaNegocio.Contacto> lista = new ArrayList<capaNegocio.Contacto>();
        capaNegocio.Contacto contacto = new capaNegocio.Contacto();
        if (apellido!=null) {
            contacto.setApellido(apellido);
        }else{
            contacto.setApellido("");
        }
        if (nombre!=null) {
            contacto.setNombre(nombre);
            
        }else{
            contacto.setNombre("");
        }
        if (telefono!=null) {
            contacto.setTelefono(telefono);
        }else{
            contacto.setTelefono("");
        }
        if (mail!=null) {
            contacto.setMail(mail);
        }else{
            contacto.setMail("");
        }
            Gson gson = new GsonBuilder().create();
        
        try {
            lista = contacto.busquedaAvanzada(contacto);
                listaResultado = gson.toJson(lista);
        } catch (PersistentException ex) {
            listaResultado=null;
        }
        return  listaResultado ;
        
            
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "busquedaSimple")
    public String busquedaSimple(@WebParam(name = "textoBusqueda") String textoBusqueda) {
        
        String listaResultado = "";
        List<Contacto> lista = new ArrayList<Contacto>();
            Gson gson = new GsonBuilder().create();
        try {
            Contacto contacto = new Contacto();
            lista = contacto.busquedaSimple(textoBusqueda);
            listaResultado = gson.toJson(lista );
            
        } catch (PersistentException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
            return listaResultado;
                
     }
}
