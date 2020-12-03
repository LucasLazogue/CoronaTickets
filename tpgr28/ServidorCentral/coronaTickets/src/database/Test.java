package database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Test {
	
	static void cargarDatos(EntityManager em) {
		em.getTransaction().begin();
		ArtistasDB art = new ArtistasDB();
		art.setEmail("Pepe@miail.com");
		art.setNombre("Pepe");
		art.setNickname("Pepe");
		FuncionesDB fun = new FuncionesDB();
		fun.setId_artista(art);
		fun.setNombre("Los village volvieron - 1");
		EspectaculosDB esp = new EspectaculosDB();
		esp.setCosto(10);
		esp.setNombrePlataforma("Twitter Live");
		esp.setNombre("Los village volvieron");
		List<FuncionesDB> funcs = new ArrayList<FuncionesDB>();
		funcs.add(fun);
		esp.setFunciones(funcs);
		em.persist(esp);
		em.flush();
		em.getTransaction().commit();
	}

	static void borrarCosasCUIDADO(EntityManager em) {
		TypedQuery<EspectaculosDB> sel = em.createQuery("SELECT a FROM EspectaculosDB a", EspectaculosDB.class);
		for (EspectaculosDB esp: sel.getResultList()) {
			esp.setFunciones(new ArrayList<FuncionesDB>());
			em.persist(esp);
			em.flush();
			em.getTransaction().commit();
			em.getTransaction().begin();
		}
		TypedQuery<FuncionesDB> sel2 = em.createQuery("SELECT a FROM FuncionesDB a", FuncionesDB.class);
		for (FuncionesDB fun: sel2.getResultList()) {
			fun.setId_espectaculo(null);
			fun.setId_artista(null);
			em.persist(fun);
			em.flush();
			em.getTransaction().commit();
			em.getTransaction().begin();
		}
		TypedQuery<ArtistasDB> sel3 = em.createQuery("SELECT a FROM ArtistasDB a", ArtistasDB.class);
		for (ArtistasDB usu: sel3.getResultList()) {
			usu.setFunciones(new ArrayList<FuncionesDB>());
			em.persist(usu);
			em.flush();
			em.getTransaction().commit();
			em.getTransaction().begin();
		}
		TypedQuery<EspectaculosDB> select2 = em.createQuery("DELETE FROM EspectaculosDB", EspectaculosDB.class);		
		TypedQuery<FuncionesDB> select1 = em.createQuery("DELETE FROM FuncionesDB", FuncionesDB.class);
		TypedQuery<ArtistasDB> select = em.createQuery("DELETE FROM ArtistasDB", ArtistasDB.class);
		select2.executeUpdate();
		select1.executeUpdate();
		select.executeUpdate();
	}
	
	static void consultaDB(EntityManager em) {
		TypedQuery<EspectaculosDB> select = em.createQuery("SELECT a FROM EspectaculosDB a", EspectaculosDB.class);
		System.out.println("Base de datos vacia: " + select.getResultList().isEmpty());
		for (EspectaculosDB esp: select.getResultList()) {
			System.out.println("ESPECTACULO: " + esp.getNombre() + " - DATOS:");
			for (FuncionesDB fun: esp.getFunciones()) {
				System.out.println("--- FUNCION: " + fun.getNombre() + " - ARTISTA: " + fun.getId_artista().getNombre());				
			}
		}
	}
	
	public static void main(String[] args) {
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("ctUy-ServidorCentral");
	  EntityManager em = emf.createEntityManager();
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	  try {
		em.getTransaction().begin();
		String res = "0";
		while (res.equals("0")) {
			System.out.println("Presione 1 para borrar, 2 para consultar.");
			res = reader.readLine(); 
			System.out.println(res);
			if (res.equals("1")) borrarCosasCUIDADO(em);
			if (res.equals("2")) consultaDB(em);
			System.out.println("Volver a iniciar -> 0. Salir cualquier int.");
			res = reader.readLine(); 
		}
		em.getTransaction().commit();
		
	  } catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback();
	  } finally {
		em.close();
		emf.close();
	  }
	}

}
