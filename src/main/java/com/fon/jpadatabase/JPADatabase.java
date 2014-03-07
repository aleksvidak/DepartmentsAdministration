package com.fon.jpadatabase;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Katedra;
import model.Laboratorija;
import model.Login;

public class JPADatabase {
	private static JPADatabase jpaDatabase;
    EntityManagerFactory emf;

    private JPADatabase() {
        emf = Persistence.createEntityManagerFactory("AdministracijaKatedri");

    }

    public static JPADatabase dajObjekat() {
        if (jpaDatabase == null) {
            jpaDatabase = new JPADatabase();
        }

        return jpaDatabase;
    }

    public  List<Login> PrijavanaSistem(String KorisnickoIme, String Lozinka) {
        EntityManager em = emf.createEntityManager();
       List<Login> ListaKorisnika = null;
      
        try {
        	ListaKorisnika = em.createQuery("FROM Login k WHERE k.korisnickoIme = :KorisnickoIme AND k.lozinka = :Lozinka").setParameter("KorisnickoIme", KorisnickoIme).setParameter("Lozinka", Lozinka).getResultList();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return ListaKorisnika;
    }
    
    public  List<Laboratorija> listaLaboratorija() {
        EntityManager em = emf.createEntityManager();
       List<Laboratorija> listaLaboratorija = null;
      
        try {
        	
        	listaLaboratorija = em.createQuery("FROM Laboratorija l").getResultList();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return listaLaboratorija;
    }
    
    public  List<Katedra> listaKatedri() {
        EntityManager em = emf.createEntityManager();
       List<Katedra> listaKatedra = null;
      
        try {
        	
        	listaKatedra = em.createQuery("FROM Katedra k").getResultList();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return listaKatedra;
    }

}
