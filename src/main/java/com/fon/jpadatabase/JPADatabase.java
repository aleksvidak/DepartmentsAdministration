package com.fon.jpadatabase;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Kabinet;
import model.Katedra;
import model.Laboratorija;
import model.Login;
import model.Nastavnik;
import model.Nastavnik_Zvanje;
import model.Predmeti;
import model.Pripadnost_Nastavnika_katedri;
import model.Pripadnost_predmeta_katedri;
import model.Vrsta_nastavnika;
import model.Zvanje;

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
    
    public String dajTrenutnoZvanje(int idTrenutnoZvanje) {
        EntityManager em = emf.createEntityManager();
        String zvanje="";
        try {
        	 Nastavnik_Zvanje nasZvanje=em.find(Nastavnik_Zvanje.class, idTrenutnoZvanje);
        	 zvanje= nasZvanje.getZvanje().getNaziv_zvanja().toString();
        	
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return zvanje;
    }
    public String dajPrivilegije(int idNastavnika) {
        EntityManager em = emf.createEntityManager();
        String privilegija="";
        try {
        	List<Login> l=em.createQuery("FROM Login l WHERE l.ID_nastavnika= :id").setParameter("id", idNastavnika).getResultList();
        	 privilegija= l.get(0).getID_Login()+";"+ l.get(0).getPrivilegija();
        	
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return privilegija;
    }
    
    public  List<Vrsta_nastavnika> listaVrsteNastavnika() {
        EntityManager em = emf.createEntityManager();
       List<Vrsta_nastavnika> listaVrsteNastavnika = null;
      
        try {
        	
        	listaVrsteNastavnika = em.createQuery("FROM Vrsta_nastavnika vrn").getResultList();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return listaVrsteNastavnika;
    }
    public  List<Kabinet> listaKabineta() {
        EntityManager em = emf.createEntityManager();
       List<Kabinet> listaKabinet = null;
      
        try {
        	
        	listaKabinet = em.createQuery("FROM Kabinet vrn").getResultList();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return listaKabinet;
    }
    public  List<Zvanje> listaZvanje() {
        EntityManager em = emf.createEntityManager();
       List<Zvanje> listaZvanje = null;
      
        try {
        	
        	listaZvanje = em.createQuery("FROM Zvanje z").getResultList();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            em.close();
        }
        return listaZvanje;
    }
    
    public String sacuvajNastavnika(String ime, String prezime, String email, String telefon, String vrsta, String kabinet, String licPrez, String zvanje) {
        String idNastavnik = "";
        EntityManager em = emf.createEntityManager();
        try {
           // Laboratorija l = em.find(Laboratorija.class, lab.getId_laboratorije());
            //if (l == null) {
                em.getTransaction().begin();
                	Nastavnik n=new Nastavnik();
                	n.setIme(ime);
                	n.setPrezime(prezime);
                	n.setEmail(email);
                	n.setTelefon(telefon);
                	n.setLicna_prezentacija(licPrez);
                	
//                	Zvanje z=new Zvanje();
//                	z.setID_zvanja(Integer.parseInt(zvanje));
                	Vrsta_nastavnika vr=new Vrsta_nastavnika();
                	vr.setId_vrste_nastavnika(Integer.parseInt(vrsta));
                	Kabinet k=new Kabinet();
                	k.setId_kabineta(Integer.parseInt(kabinet));
                	
                	n.setKabinet(k);
                	n.setVrstaNastavnika(vr);
                	n.setId_zvanja_trenutno(Integer.parseInt(zvanje));
                	em.persist(n);
                	em.flush();
                	
                em.getTransaction().commit();
                
                idNastavnik =""+ n.getId_nastavnika();
          //  } else {
          //      poruka = "VEC POSTOJI";
          //  }


        } catch (Exception e) {
        	idNastavnik = e.getMessage();
        } finally {
            em.close();
        }
        return idNastavnik;
    }
    
    public  String sacuvajNastavnik_Zvanje(String idNas, String zvanje) {
        String idNasZvanje = "";
        EntityManager em = emf.createEntityManager();
        try {
           // Laboratorija l = em.find(Laboratorija.class, lab.getId_laboratorije());
            //if (l == null) {
        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        	Date date =new Date();
        	
                em.getTransaction().begin();
                	Nastavnik_Zvanje nsz=new Nastavnik_Zvanje();
                	Nastavnik n=new Nastavnik();
                	n.setId_nastavnika(Integer.parseInt(idNas));
                	
                	Zvanje z=new Zvanje();
                	z.setID_zvanja(Integer.parseInt(zvanje));
                	
                	nsz.setNastavnik(n);
                	nsz.setZvanje(z);
                	nsz.setDatum_Postavljenja(dateFormat.format(date));
                	em.persist(nsz);
                	
                em.getTransaction().commit();
                
                idNasZvanje ="ok";
          //  } else {
          //      poruka = "VEC POSTOJI";
          //  }


        } catch (Exception e) {
        	idNasZvanje = e.getMessage();
        } finally {
            em.close();
        }
        return idNasZvanje;
    }
    public String obrisiNastavnika(int idNastavnik) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
            Nastavnik n = em.find(Nastavnik.class, idNastavnik);
           // if (l == null) {
                em.getTransaction().begin();
	              em.remove(n);
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
    public String izmeniNastavnika(String idNastavnika,String ime, String prezime, String email, String telefon, String vrsta, String kabinet, String licPrez, String zvanje) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        try {
            Nastavnik n = em.find(Nastavnik.class, Integer.parseInt(idNastavnika));
           // if (l == null) {
        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        	Date date =new Date();
                em.getTransaction().begin();
                
	             n.setIme(ime);
	             n.setPrezime(prezime);
	             n.setTelefon(telefon);
	             n.setEmail(email);
	             n.setLicna_prezentacija(licPrez);
	             Kabinet k=new Kabinet();
	             k.setId_kabineta(Integer.parseInt(kabinet));
	             n.setKabinet(k);	             
	             Vrsta_nastavnika v=new Vrsta_nastavnika();
	             v.setId_vrste_nastavnika(Integer.parseInt(vrsta));
	             n.setVrstaNastavnika(v);
	             
	             //provera da li mu je trenutno zvanje ovo koje sad pokusavam da upisem
	             if(Integer.parseInt(zvanje)!=n.getId_zvanja_trenutno()){//onda treba da se upise i u tabelu zvanja za istoriju
	            	 n.setId_zvanja_trenutno(Integer.parseInt(zvanje));
	            	 em.persist(n);
	            	 	Nastavnik_Zvanje nsz=new Nastavnik_Zvanje();
	                	
	                	Zvanje z=new Zvanje();
	                	z.setID_zvanja(Integer.parseInt(zvanje));
	                	
	                	nsz.setNastavnik(n);
	                	nsz.setZvanje(z);
	                	nsz.setDatum_Postavljenja(dateFormat.format(date));
	                	
	                	em.persist(nsz);
	            	
	             }
	             else{
	             em.persist(n);
	             }
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

    public  Nastavnik dajNastavnika(String idNas) {
    	 Nastavnik n=null;
        EntityManager em = emf.createEntityManager();
        try {
          n = em.find(Nastavnik.class, Integer.parseInt(idNas));
          
        
        } catch (Exception e) {
        	
        } finally {
            em.close();
        }
        return n;
    }
    
    public String sacuvajNastavnikPripadaKatedri(String idNastavnika, String idKat) {
    	 
    	String poruka = "";
        EntityManager em = emf.createEntityManager();
        List<Pripadnost_Nastavnika_katedri> lnk=null;
        try {
        	Nastavnik nas=em.find(Nastavnik.class, Integer.parseInt(idNastavnika));
        	Katedra kat=em.find(Katedra.class, Integer.parseInt(idKat));
        	lnk = em.createQuery("FROM Pripadnost_Nastavnika_katedri k WHERE k.katedra = :kat AND k.nastavnik = :nas").setParameter("kat",kat).setParameter("nas",nas).getResultList();
            if (lnk.size()==0) {
            	Pripadnost_Nastavnika_katedri nk=new Pripadnost_Nastavnika_katedri();
            	nk.setKatedra(kat);
            	nk.setNastavnik(nas);
                em.getTransaction().begin();
                em.persist(nk); 
              
                em.getTransaction().commit();
                
                poruka ="SACUVANO";
            }
            else {
            
                poruka = "NASTAVNIK VEC PRIPADA KATEDRI "+ kat.getNaziv_katedre();
            }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    } 
    public String obrisiNastavnikPripadaKatedri(String idNastavnika, String idKat) {
        String poruka = "";
        EntityManager em = emf.createEntityManager();
        List<Pripadnost_Nastavnika_katedri> lnk=null;
        try {
        	Nastavnik nas=em.find(Nastavnik.class, Integer.parseInt(idNastavnika));
        	Katedra kat=em.find(Katedra.class, Integer.parseInt(idKat));
        	lnk = em.createQuery("FROM Pripadnost_Nastavnika_katedri k WHERE k.katedra = :kat AND k.nastavnik = :nas").setParameter("kat",kat).setParameter("nas",nas).getResultList();
            if (lnk.size()!=0) {
            	Pripadnost_Nastavnika_katedri nk=em.find(Pripadnost_Nastavnika_katedri.class, lnk.get(0).getId_pripadnosti());
            	nk.setKatedra(kat);
            	nk.setNastavnik(nas);
                em.getTransaction().begin();
                em.remove(nk);                
                em.getTransaction().commit();
                
                poruka ="OBRISANO";
            } else {
            
                poruka = "GRESKA PRI PRONALAZENJU PODATKA U TABELI";
            }


        } catch (Exception e) {
        	poruka = e.getMessage();
        } finally {
            em.close();
        }
        return poruka;
    } 
    
}
