package capaNegocio;

import java.util.ArrayList;
import java.util.List;
import org.orm.*;

public class Contacto {

    private int uid;
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;
    private Empresa empresa;
    private Ciudad ciudad;

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Ciudad getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @param contacto
     */
    public int agregarContactoCapaNegocio(Contacto contacto) throws PersistentException {
        int respuesta = 0;
        PersistentTransaction t = orm.EstructuraPaginaWebPersistentManager.instance().getSession().beginTransaction();
        try {
            orm.Ciudad lormCiudad = orm.CiudadDAO.createCiudad();           
            lormCiudad.setNombre(contacto.getCiudad().getNombre());
            orm.CiudadDAO.save(lormCiudad);               
            
            orm.Empresa lormEmpresa = orm.EmpresaDAO.createEmpresa();                              
            lormEmpresa.setNombre(contacto.getEmpresa().getNombre());
            orm.EmpresaDAO.save(lormEmpresa);            
        
            orm.Contacto lormContacto = orm.ContactoDAO.createContacto();   
            lormContacto.setApellido(contacto.getApellido());           
            lormContacto.setNombre(contacto.getNombre());            
            lormContacto.setMail(contacto.getMail());            
            lormContacto.setTelefono(contacto.getTelefono());  
            lormContacto.setId_ciudad(lormCiudad);            
            lormContacto.setId_empresa(lormEmpresa);            
            
            orm.ContactoDAO.save(lormContacto);
            t.commit();
            respuesta = lormContacto.getUid();
        } catch (Exception e) {
            t.rollback();
        }
        return respuesta;
    }

    /**
     *
     * @param contacto
     */
    public int eliminarContactoCapaNegocio(Contacto contacto) throws PersistentException {
        int respuesta = 0;
        PersistentTransaction t = orm.EstructuraPaginaWebPersistentManager.instance().getSession().beginTransaction();
        try {
            orm.Contacto lormContacto = orm.ContactoDAO.loadContactoByORMID(contacto.getUid());
            orm.ContactoDAO.delete(lormContacto);
            

            t.commit();

            return lormContacto.getUid();
        } catch (Exception e) {
            t.rollback();
        }
        return respuesta;
    }

    /**
     *
     * @param contacto
     */
    public int editarContactoCapaNegocio(Contacto contacto) throws PersistentException {
        int respuesta = 0;
        PersistentTransaction t = orm.EstructuraPaginaWebPersistentManager.instance().getSession().beginTransaction();
        try {
           
            
            orm.Contacto lormContacto = orm.ContactoDAO.loadContactoByORMID(contacto.getUid());
            
            if (contacto.getApellido().equalsIgnoreCase("")) {
                
            }else   {
                lormContacto.setApellido(contacto.getApellido());
            }
            if (contacto.getMail().equalsIgnoreCase("")) {
                
            }else{
                lormContacto.setMail(contacto.getMail());
            }
            if (contacto.getNombre().equalsIgnoreCase("")) {
                
            }else{
                lormContacto.setNombre(contacto.getNombre());
            }
            if (contacto.getTelefono() != ("")) {
                
            }else{
                lormContacto.setTelefono(contacto.getTelefono());
            }
            
            
            orm.ContactoDAO.save(lormContacto);
            t.commit();
            respuesta = lormContacto.getUid();
        } catch (Exception e) {
            t.rollback();
        }
        return respuesta;
    }
    
    
    public List<Contacto> busquedaSimple (String busqueda) throws PersistentException{
        
        List<Contacto> listaContacto = new  ArrayList<Contacto>();
        List<orm.Contacto> listaContactos = new ArrayList<orm.Contacto>();
        if (busqueda != null || !busqueda.equals("")) {
            listaContactos=orm.ContactoDAO.queryContacto("Contacto.nombre='" + busqueda + "'or Contacto.apellido='"
            + busqueda+ "' or Contacto.telefono='"+busqueda+"'  or Contacto.mail='"+busqueda+"' ", null);
            }
        if (listaContactos!=null) {
            for (orm.Contacto contactoOrm : listaContactos) {
                Contacto contactoNegocio = new Contacto();
                Empresa empresaNegocio = new Empresa();
                orm.Empresa empresaOrm = orm.EmpresaDAO.loadEmpresaByORMID(contactoOrm.getId_empresa().getUid());
                empresaNegocio.setNombre(empresaOrm.getNombre());
                empresaNegocio.setUid(empresaOrm.getUid());
                
                orm.Ciudad ciudadOrm=orm.CiudadDAO.loadCiudadByORMID(contactoOrm.getId_ciudad().getUid());
                Ciudad ciudadNegocio = new Ciudad();
                ciudadNegocio.setNombre(ciudadOrm.getNombre());
                ciudadNegocio.setUid(ciudadOrm.getUid());
                
                contactoNegocio.setCiudad(ciudadNegocio);
                contactoNegocio.setEmpresa(empresaNegocio);
                contactoNegocio.setNombre(contactoOrm.getNombre());
                contactoNegocio.setApellido(contactoOrm.getApellido());
                contactoNegocio.setTelefono(contactoOrm.getTelefono());
                contactoNegocio.setMail(contactoOrm.getMail());
                contactoNegocio.setUid(contactoOrm.getUid());
                listaContacto.add(contactoNegocio);
            }
            
        }
        return listaContacto;
    }

    
    public  List<Contacto> busquedaAvanzada(Contacto contacto) throws PersistentException{
        
        List<Contacto> listaContacto = new  ArrayList<Contacto>();
        List<orm.Contacto> listaContactos = new ArrayList<orm.Contacto>();
        String query="";
        if (contacto.getNombre()!=null && !contacto.getNombre().equals("")) {
            query+="Contacto.nombre='"+contacto.getNombre()+"' ";
            
        }
        if ((contacto.getNombre()!=null && !contacto.getNombre().equals("")) && (contacto.getApellido()!=null && !contacto.getApellido().equals("")) ) {
            query+="and ";
        }
        if ((contacto.getApellido()!=null && !contacto.getApellido().trim().equals(""))) {
            query+="Contacto.apellido='" + contacto.getApellido()+"' ";
                        
        }
        if (((contacto.getNombre()!=null && !contacto.getNombre().trim().equals("")) || contacto.getApellido()!=null && !contacto.getApellido().trim().equals("")) && 
            (contacto.getTelefono()!=null && !contacto.getTelefono().trim().equals(""))){
            query+="and ";
        }
        if (contacto.getTelefono()!=null && !contacto.getTelefono().trim().equals("")) {
            query+="Contacto.telefono='" + contacto.getTelefono()+ "' ";
            
        }
        if (((contacto.getNombre()!=null && !contacto.getNombre().trim().equals("")) || contacto.getApellido()!=null && !contacto.getApellido().trim().equals("") || 
            contacto.getTelefono()!=null && !contacto.getTelefono().trim().equals("")) && contacto.getMail()!=null && !contacto.getMail().trim().equals("")){
            query+="and ";
        }
        if (contacto.getMail()!=null && !contacto.getMail().trim().equals("")) {
            query+="Contacto.mail='"+contacto.getMail()+"'";
        }
        listaContactos = orm.ContactoDAO.queryContacto(query,null);
        if (listaContactos!=null) {
            for (orm.Contacto contactoOrm : listaContactos) {
                Contacto contactoNegocio = new Contacto();
                
                Empresa empresaNegocio = new Empresa();
                orm.Empresa empresaOrm = orm.EmpresaDAO.loadEmpresaByORMID(contactoOrm.getId_empresa().getUid());
                empresaNegocio.setNombre(empresaOrm.getNombre());
                empresaNegocio.setUid(empresaOrm.getUid());
                
                Ciudad ciudadNegocio = new Ciudad() ;
                orm.Ciudad ciudadOrm = orm.CiudadDAO.loadCiudadByORMID(contactoOrm.getId_ciudad().getUid());
                ciudadNegocio.setNombre(ciudadOrm.getNombre());
                ciudadNegocio.setUid(ciudadOrm.getUid());
                
                contactoNegocio.setCiudad(ciudadNegocio);
                contactoNegocio.setEmpresa(empresaNegocio);
                contactoNegocio.setUid(contactoOrm.getUid());
                contactoNegocio.setNombre(contactoOrm.getNombre());
                contactoNegocio.setApellido(contactoOrm.getApellido());
                contactoNegocio.setTelefono(contactoOrm.getTelefono());
                contactoNegocio.setMail(contactoOrm.getMail());
                listaContacto.add(contactoNegocio);
            }
            
        }
        return listaContacto;
    }
}
