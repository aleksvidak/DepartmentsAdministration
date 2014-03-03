package com.fon.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.hibernate.validator.constraints.NotEmpty;

public class PodaciPrijava {

	@NotEmpty
	private String korisnickaSifra;
	@NotEmpty
	private String korisnickoIme;
	
	public String getKorisnickaSifra() {
		return korisnickaSifra;
	}

	public void setKorisnickaSifra(String korisnickaSifra) {
		this.korisnickaSifra = korisnickaSifra;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	private String Prezime;
	private String Ime;
	private String Privilegija;
	public String getPrezime() {
		return Prezime;
	}

	public void setPrezime(String prezime) {
		Prezime = prezime;
	}

	public String getIme() {
		return Ime;
	}

	public void setIme(String ime) {
		Ime = ime;
	}

	public String getPrivilegija() {
		return Privilegija;
	}

	public void setPrivilegija(String privilegija) {
		Privilegija = privilegija;
	}

	public int getID_Nastavnika() {
		return ID_Nastavnika;
	}

	public void setID_Nastavnika(int iD_Nastavnika) {
		ID_Nastavnika = iD_Nastavnika;
	}

	private int ID_Nastavnika;
	
	public static PodaciPrijava login(String KorisnickoIme, String Sifra){
		
		PodaciPrijava korisnik=new PodaciPrijava();		
		try {
			 String url = "jdbc:sqlserver://localhost;databaseName=FON_MASTER_1;integratedSecurity=true";
			  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	          Connection conn = DriverManager.getConnection(url);
	          CallableStatement st = conn.prepareCall("{call LOGIN_proc(?,?)}");
	          st.setString(1, KorisnickoIme);
	          st.setString(2, Sifra);
	          ResultSet rs=st.executeQuery();
	          while(rs.next()){
	        	 korisnik.setID_Nastavnika(Integer.parseInt(rs.getString("Id_nastavnika")));
	        	 korisnik.setPrivilegija(rs.getString("Privilegija")); 
	        	 korisnik.setIme(rs.getString("Ime"));
	        	 korisnik.setPrezime(rs.getString("Prezime"));
	          }
			
		} catch (Exception e) {
			 System.out.println(e);
		}
		
		return korisnik;
	}


	
	
}
