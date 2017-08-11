/**
 * Licensee: Universidad de La Frontera
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateEstructuraPaginaWebData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = orm.EstructuraPaginaWebPersistentManager.instance().getSession().beginTransaction();
		try {
			orm.Contacto lormContacto = orm.ContactoDAO.createContacto();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : id_empresa, id_ciudad
			orm.ContactoDAO.save(lormContacto);
			orm.Ciudad lormCiudad = orm.CiudadDAO.createCiudad();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : contacto
			orm.CiudadDAO.save(lormCiudad);
			orm.Empresa lormEmpresa = orm.EmpresaDAO.createEmpresa();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : contacto
			orm.EmpresaDAO.save(lormEmpresa);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateEstructuraPaginaWebData createEstructuraPaginaWebData = new CreateEstructuraPaginaWebData();
			try {
				createEstructuraPaginaWebData.createTestData();
			}
			finally {
				orm.EstructuraPaginaWebPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
