package com.fon.jpadatabase;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Katedra;
import model.Laboratorija;
import model.Login;
import model.Nastavnik;
import model.Predmeti;
import model.Pripadnost_predmeta_katedri;

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
    
    public String sacuvajLaboratoriju(Laboratorija lab) {
        String idLab = "";
        EntityManager em = emf.createEntityManager();
        try {
           // Laboratorija l = em.find(Laboratorija.class, lab.getId_laboratorije());
            //if (l == null) {
                em.getTransaction().begin();
                em.persist(lab);
                em.flush();
                em.getTransaction().commit();
                
                idLab =""+ lab.getId_laboratorije();
          //  } else {
          //      poruka = "VEC POSTOJI";
          //  }


        } catch (Exception e) {
            idLab = e.getMessage();
        } finally {
            em.close();
        }
        return idLab;
    } 
    public String izmeniLaboratoriju(Laboratorija lab) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
            Laboratorija l = em.find(Laboratorija.class, lab.getId_laboratorije());
           // if (l == null) {
                em.getTransaction().begin();
	               l.setKatedra(lab.getKatedra());
	               l.setNaziv_laboratorije(lab.getNaziv_laboratorije());
	               l.setSajt(lab.getSajt());
                em.getTransaction().commit();
                
                poruka ="IZMENJENO";
          //  } else {
              
          //  }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    } 
    public String obrisiLaboratoriju(int ID_Lab) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
            Laboratorija l = em.find(Laboratorija.class, ID_Lab);
           // if (l == null) {
                em.getTransaction().begin();
	              em.remove(l);
                em.getTransaction().commit();
                
                poruka ="OBRISANO";
          //  } else {
              
          //  }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    } 
    
    
    public  List<Predmeti> listaPredmeta() {
        EntityManager em = emf.createEntityManager();
       List<Predmeti> listaPredmeta = null;
      
        try {
        	
        	listaPredmeta = em.createQuery("FROM Predmeti p").getResultList();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return listaPredmeta;
    }
    
    
    public String sacuvajPredmet(Predmeti predmet) {
        String idPredmet = "";
        EntityManager em = emf.createEntityManager();
        try {
           // Laboratorija l = em.find(Laboratorija.class, lab.getId_laboratorije());
            //if (l == null) {
                em.getTransaction().begin();
                em.persist(predmet);
                em.flush();
                em.getTransaction().commit();
                
                idPredmet =""+ predmet.getID_predmeta();
          //  } else {
          //      poruka = "VEC POSTOJI";
          //  }


        } catch (Exception e) {
        	idPredmet = e.getMessage();
        } finally {
            em.close();
        }
        return idPredmet;
    }
    public String izmeniPredmet(String idPredmeta ,String nazivPred, String brBodova, String semestar, String brCasovaPredavanja, String brCasovaVezbi, String brCasovaOstalo) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
            Predmeti p = em.find(Predmeti.class, Integer.parseInt(idPredmeta));
           // if (l == null) {
                em.getTransaction().begin();
	              p.setNaziv_predmeta(nazivPred);
	              p.setBr_bodova(Short.parseShort(brBodova));
	              p.setSemestar(Short.parseShort(semestar));
	              p.setBr_casova_predvanja(Short.parseShort(brCasovaPredavanja));
	              p.setBr_casova_vezbi(Short.parseShort(brCasovaVezbi));
	              p.setBr_casova_ostalo(Short.parseShort(brCasovaOstalo));
                em.getTransaction().commit();
                
                poruka ="IZMENJENO";
          //  } else {
              
          //  }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    }
    
    public String obrisiPredmet(int idPredmet) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
            Predmeti p = em.find(Predmeti.class, idPredmet);
           // if (l == null) {
                em.getTransaction().begin();
	              em.remove(p);
                em.getTransaction().commit();
                
                poruka ="OBRISANO";
          //  } else {
              
          //  }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    } 
    
    public  List<Nastavnik> listaNastavnika() {
        EntityManager em = emf.createEntityManager();
       List<Nastavnik> listaNastavnika = null;
      
        try {
        	
        	listaNastavnika = em.createQuery("FROM Nastavnik n").getResultList();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return listaNastavnika;
    }
    
    public String sacuvajKatedru(Katedra k) {
        String idKatedre = "";
        EntityManager em = emf.createEntityManager();
        try {
           // Laboratorija l = em.find(Laboratorija.class, lab.getId_laboratorije());
            //if (l == null) {
                em.getTransaction().begin();
	                em.persist(k);
	                em.flush();
                em.getTransaction().commit();
                
                idKatedre =""+ k.getID_katedre();
          //  } else {
          //      poruka = "VEC POSTOJI";
          //  }


        } catch (Exception e) {
        	idKatedre = e.getMessage();
        } finally {
            em.close();
        }
        return idKatedre;
    }
    
    public String obrisiKatedru(int IdKatedre) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
            Katedra k = em.find(Katedra.class, IdKatedre);
           // if (l == null) {
                em.getTransaction().begin();
	              em.remove(k);
                em.getTransaction().commit();
                
                poruka ="OBRISANO";
          //  } else {
              
          //  }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    }
    public String izmeniKatedru(String idKatedre, String nazivKat, String rukovodilac, String sekretar) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
            Katedra k = em.find(Katedra.class, Integer.parseInt(idKatedre));
           // if (l == null) {
                em.getTransaction().begin();
	              Nastavnik ruko=new Nastavnik();
	              ruko.setId_nastavnika(Integer.parseInt(rukovodilac));
	              
	              Nastavnik sek=new Nastavnik();
	              sek.setId_nastavnika(Integer.parseInt(sekretar));
	              
	              k.setNaziv_katedre(nazivKat);
	              k.setNastavnik1(ruko);
	              k.setNastavnik2(sek);
                em.getTransaction().commit();
                
                poruka ="IZMENJENO";
          //  } else {
              
          //  }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    }
    
    public String sacuvajPripadnos_predmeta_Katedri(Pripadnost_predmeta_katedri prk) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
           // Laboratorija l = em.find(Laboratorija.class, lab.getId_laboratorije());
            //if (l == null) {
                em.getTransaction().begin();
	                em.persist(prk);
	                em.flush();
                em.getTransaction().commit();
                
                poruka =""+prk.getId_pripadnosti();
          //  } else {
          //      poruka = "VEC POSTOJI";
          //  }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    }
    
    public String obrisiPripadnostPredmetaKatedri(int idPripadnost) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
        	Pripadnost_predmeta_katedri prk = em.find(Pripadnost_predmeta_katedri.class, idPripadnost);
           // if (l == null) {
                em.getTransaction().begin();
	              em.remove(prk);
                em.getTransaction().commit();
                
                poruka ="OBRISANO";
          //  } else {
              
          //  }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    }
    public String izmeniPripadnostPredmetaKatedri(String idPripadnost ,String predavac, String katedra,String idPredmeta) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
            Pripadnost_predmeta_katedri prk = em.find(Pripadnost_predmeta_katedri.class, Integer.parseInt(idPripadnost));
           // if (l == null) {
                em.getTransaction().begin();
                	Katedra k=new Katedra();
                	k.setID_katedre(Integer.parseInt(katedra));
                	
                	Predmeti p=new Predmeti();
                	p.setID_predmeta(Integer.parseInt(idPredmeta));
                	
                	Nastavnik n=new Nastavnik();
                	n.setId_nastavnika(Integer.parseInt(predavac));
                	
                	prk.setKatedra(k);
                	prk.setNastavnik(n);
                	prk.setPredmeti(p);
                em.getTransaction().commit();
                
                poruka ="IZMENJENO";
          //  } else {
              
          //  }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    }
    

}
