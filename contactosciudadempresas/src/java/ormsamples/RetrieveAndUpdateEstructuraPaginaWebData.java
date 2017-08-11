/**
 * Licensee: Universidad de La Frontera
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateEstructuraPaginaWebData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = orm.EstructuraPaginaWebPersistentManager.instance().getSession().beginTransaction();
		try {
			orm.Contacto lormContacto = orm.ContactoDAO.loadContactoByQuery(null, null);
			// Update the properties of the persistent object
			orm.ContactoDAO.save(lormContacto);
			orm.Ciudad lormCiudad = orm.CiudadDAO.loadCiudadByQuery(null, null);
			// Update the properties of the persistent object
			orm.CiudadDAO.save(lormCiudad);
			orm.Empresa lormEmpresa = orm.EmpresaDAO.loadEmpresaByQuery(null, null);
			// Update the properties of the persistent object
			orm.EmpresaDAO.save(lormEmpresa);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Contacto by ContactoCriteria");
		orm.ContactoCriteria lormContactoCriteria = new orm.ContactoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lormContactoCriteria.uid.eq();
		System.out.println(lormContactoCriteria.uniqueContacto());
		
		System.out.println("Retrieving Ciudad by CiudadCriteria");
		orm.CiudadCriteria lormCiudadCriteria = new orm.CiudadCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lormCiudadCriteria.uid.eq();
		System.out.println(lormCiudadCriteria.uniqueCiudad());
		
		System.out.println("Retrieving Empresa by EmpresaCriteria");
		orm.EmpresaCriteria lormEmpresaCriteria = new orm.EmpresaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lormEmpresaCriteria.uid.eq();
		System.out.println(lormEmpresaCriteria.uniqueEmpresa());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateEstructuraPaginaWebData retrieveAndUpdateEstructuraPaginaWebData = new RetrieveAndUpdateEstructuraPaginaWebData();
			try {
				retrieveAndUpdateEstructuraPaginaWebData.retrieveAndUpdateTestData();
				//retrieveAndUpdateEstructuraPaginaWebData.retrieveByCriteria();
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
