package com.fon.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import model.Katedra;
import model.Nastavnik;
import model.Predmeti;
import model.Zvanje;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.jpadatabase.JPADatabase;
import com.fon.jsonmodel.PredmetJSON;

@Controller
public class KatedraController {
	
	//ovo je metoda za prikaz stranice katedre
	@RequestMapping(value = "/katedra.html", method=RequestMethod.GET)
	public String katedraPrikaz (@RequestParam(value = "id") String id, Model model) {
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		//System.out.println(id);
		Katedra k=JPADatabase.dajObjekat().dajKatedru(Integer.parseInt(id));
		
		
		
		model.addAttribute("Katedra", k);
		List<Zvanje> lsZvanje=JPADatabase.dajObjekat().listaZvanje();
		String zvanjeRukovodioca="";
		for (Zvanje zvanje : lsZvanje) {
			if(zvanje.getID_zvanja()==k.getNastavnik1().getId_zvanja_trenutno())
				zvanjeRukovodioca=zvanje.getNaziv_zvanja().toString();			
		}
		String zvanjeSekretara="";
		for (Zvanje zvanje : lsZvanje) {
			if(zvanje.getID_zvanja()==k.getNastavnik2().getId_zvanja_trenutno())
				zvanjeSekretara=zvanje.getNaziv_zvanja().toString();			
		}
		model.addAttribute("zvanjeRukovodioca", zvanjeRukovodioca);
		model.addAttribute("zvanjeSekretara", zvanjeSekretara);
		
		System.out.print("gavra");
		for(int i=0;i<k.getLaboratorijas().size();i++){
			System.out.print(k.getLaboratorijas().get(i).getId_laboratorije());
		}
		
		
		
		
		
		return "katedra";
	}
	
	//ovo je za sifarnik katedre
	@RequestMapping(value = "/katedraSifarnik", method=RequestMethod.GET)
	public String katedraPrikaz(Model model) {
		
		List<Katedra> listaKatedri=JPADatabase.dajObjekat().listaKatedri();
		model.addAttribute("listaKatedri", listaKatedri);
		
		List<Nastavnik> listaNastavnik=JPADatabase.dajObjekat().listaNastavnika();
		model.addAttribute("listaNastavnik", listaNastavnik);
		
		return "katedraSifarnik";
	}
	
	@RequestMapping(value = "/dodajKatedru", method=RequestMethod.POST)
	public  @ResponseBody String dodajKatedru(String nazivKat, String rukovodilac, String sekretar) throws FileNotFoundException {
		
		Nastavnik nasRuk=new Nastavnik();
		nasRuk.setId_nastavnika(Integer.parseInt(rukovodilac));
		
		Nastavnik nasSek=new Nastavnik();
		nasSek.setId_nastavnika(Integer.parseInt(sekretar));
		
		Katedra k=new Katedra();
		k.setNaziv_katedre(nazivKat);
		
		k.setNastavnik1(nasRuk);
		k.setNastavnik2(nasSek);
		
		String id=JPADatabase.dajObjekat().sacuvajKatedru(k);
		
		//treba upisati u locale fajlove nazive katedri, prilikom dodavanja novog zapisa
/*		try{
    		String data = " This content will append to the end of the file";
    		URL url = getClass().getResource("messages_rs.properties");
    		File file =new File(url.getPath());
 
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(data);
    	        bufferWritter.close();
 
	        System.out.println("Done");
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}*/
		return id;
	}
	
	
	@RequestMapping(value="/obrisiKatedru", method=RequestMethod.POST)
	public @ResponseBody String obrisiKatedru(String idKatedre){
		String poruka=JPADatabase.dajObjekat().obrisiKatedru(Integer.parseInt(idKatedre));
		
		
		return poruka;
	}
	
	@RequestMapping(value="/izmeniKatedru", method=RequestMethod.POST)
	public @ResponseBody String izmeniKatedru(String idKatedre, String nazivKat, String rukovodilac, String sekretar){
		String poruka=JPADatabase.dajObjekat().izmeniKatedru(idKatedre, nazivKat, rukovodilac, sekretar);
		return poruka;
	}
	
	
	@RequestMapping(value="/detaljiPredmeta", method=RequestMethod.GET)
	public @ResponseBody String predmetDetalji(String idPredmeta){
		PredmetJSON p=new PredmetJSON();
		Predmeti poruka=JPADatabase.dajObjekat().detaljiPredmeta(Integer.parseInt(idPredmeta));
		//List<PredmetJSON> lstPredme=new ArrayList<PredmetJSON>();
		p.setBrojBodova(poruka.getBr_bodova());
		p.setBrojCasovaOstalo(poruka.getBr_casova_ostalo());
		p.setBrojCasovaPredavanja(poruka.getBr_casova_predvanja());
		p.setBrojCasovaVezbe(poruka.getBr_casova_vezbi());
		p.setSemsetar(poruka.getSemestar());
		
		String nastavnik=poruka.getPripadnostPredmetaKatedris().get(0).getNastavnik().getIme()+" "+poruka.getPripadnostPredmetaKatedris().get(0).getNastavnik().getPrezime();
	
		
		return p.getBrojCasovaVezbe()+";"+p.getBrojCasovaOstalo()+";"+p.getBrojBodova()+";"+p.getBrojCasovaPredavanja()+";"+p.getSemsetar()+";"+nastavnik;
	}

	
	
}
