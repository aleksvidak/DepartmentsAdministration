package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Nastavnik database table.
 * 
 */
@Entity
@NamedQuery(name="Nastavnik.findAll", query="SELECT n FROM Nastavnik n")
public class Nastavnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id_nastavnika")
	private int id_nastavnika;

	private String email;

	@Column(name="Id_zvanja_trenutno")
	private int id_zvanja_trenutno;

	@Column(name="Ime")
	private String ime;

	@Column(name="Licna_prezentacija")
	private String licna_prezentacija;

	@Column(name="Prezime")
	private String prezime;

	@Column(name="Telefon")
	private String telefon;
	

	//bi-directional many-to-one association to Katedra
	@OneToMany(mappedBy="nastavnik1")
	private List<Katedra> katedras1;

	//bi-directional many-to-one association to Katedra
	@OneToMany(mappedBy="nastavnik2")
	private List<Katedra> katedras2;

	//bi-directional many-to-one association to Kabinet
	@ManyToOne
	@JoinColumn(name="Id_kabineta_FK")
	private Kabinet kabinet;

	//bi-directional many-to-one association to Vrsta_nastavnika
	@ManyToOne
	@JoinColumn(name="Id_vrste_nastavnika")
	private Vrsta_nastavnika vrstaNastavnika;

	//bi-directional many-to-one association to Nastavnik_Zvanje
	@OneToMany(mappedBy="nastavnik")
	private List<Nastavnik_Zvanje> nastavnikZvanjes;

	//bi-directional many-to-one association to Pripadnost_Nastavnika_katedri
	@OneToMany(mappedBy="nastavnik",fetch=FetchType.EAGER)
	private List<Pripadnost_Nastavnika_katedri> pripadnostNastavnikaKatedris;

	//bi-directional many-to-one association to Pripadnost_predmeta_katedri
	@OneToMany(mappedBy="nastavnik")
	private List<Pripadnost_predmeta_katedri> pripadnostPredmetaKatedris;

	public Nastavnik() {
	}

	public int getId_nastavnika() {
		return this.id_nastavnika;
	}

	public void setId_nastavnika(int id_nastavnika) {
		this.id_nastavnika = id_nastavnika;
	}

	public Object getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId_zvanja_trenutno() {
		return this.id_zvanja_trenutno;
	}

	public void setId_zvanja_trenutno(int id_zvanja_trenutno) {
		this.id_zvanja_trenutno = id_zvanja_trenutno;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getLicna_prezentacija() {
		return this.licna_prezentacija;
	}

	public void setLicna_prezentacija(String licna_prezentacija) {
		this.licna_prezentacija = licna_prezentacija;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Katedra> getKatedras1() {
		return this.katedras1;
	}

	public void setKatedras1(List<Katedra> katedras1) {
		this.katedras1 = katedras1;
	}

	public Katedra addKatedras1(Katedra katedras1) {
		getKatedras1().add(katedras1);
		katedras1.setNastavnik1(this);

		return katedras1;
	}

	public Katedra removeKatedras1(Katedra katedras1) {
		getKatedras1().remove(katedras1);
		katedras1.setNastavnik1(null);

		return katedras1;
	}

	public List<Katedra> getKatedras2() {
		return this.katedras2;
	}

	public void setKatedras2(List<Katedra> katedras2) {
		this.katedras2 = katedras2;
	}

	public Katedra addKatedras2(Katedra katedras2) {
		getKatedras2().add(katedras2);
		katedras2.setNastavnik2(this);

		return katedras2;
	}

	public Katedra removeKatedras2(Katedra katedras2) {
		getKatedras2().remove(katedras2);
		katedras2.setNastavnik2(null);

		return katedras2;
	}

	public Kabinet getKabinet() {
		return this.kabinet;
	}

	public void setKabinet(Kabinet kabinet) {
		this.kabinet = kabinet;
	}

	public Vrsta_nastavnika getVrstaNastavnika() {
		return this.vrstaNastavnika;
	}

	public void setVrstaNastavnika(Vrsta_nastavnika vrstaNastavnika) {
		this.vrstaNastavnika = vrstaNastavnika;
	}

	public List<Nastavnik_Zvanje> getNastavnikZvanjes() {
		return this.nastavnikZvanjes;
	}

	public void setNastavnikZvanjes(List<Nastavnik_Zvanje> nastavnikZvanjes) {
		this.nastavnikZvanjes = nastavnikZvanjes;
	}

	public Nastavnik_Zvanje addNastavnikZvanje(Nastavnik_Zvanje nastavnikZvanje) {
		getNastavnikZvanjes().add(nastavnikZvanje);
		nastavnikZvanje.setNastavnik(this);

		return nastavnikZvanje;
	}

	public Nastavnik_Zvanje removeNastavnikZvanje(Nastavnik_Zvanje nastavnikZvanje) {
		getNastavnikZvanjes().remove(nastavnikZvanje);
		nastavnikZvanje.setNastavnik(null);

		return nastavnikZvanje;
	}

	public List<Pripadnost_Nastavnika_katedri> getPripadnostNastavnikaKatedris() {
		return this.pripadnostNastavnikaKatedris;
	}

	public void setPripadnostNastavnikaKatedris(List<Pripadnost_Nastavnika_katedri> pripadnostNastavnikaKatedris) {
		this.pripadnostNastavnikaKatedris = pripadnostNastavnikaKatedris;
	}

	public Pripadnost_Nastavnika_katedri addPripadnostNastavnikaKatedri(Pripadnost_Nastavnika_katedri pripadnostNastavnikaKatedri) {
		getPripadnostNastavnikaKatedris().add(pripadnostNastavnikaKatedri);
		pripadnostNastavnikaKatedri.setNastavnik(this);

		return pripadnostNastavnikaKatedri;
	}

	public Pripadnost_Nastavnika_katedri removePripadnostNastavnikaKatedri(Pripadnost_Nastavnika_katedri pripadnostNastavnikaKatedri) {
		getPripadnostNastavnikaKatedris().remove(pripadnostNastavnikaKatedri);
		pripadnostNastavnikaKatedri.setNastavnik(null);

		return pripadnostNastavnikaKatedri;
	}

	public List<Pripadnost_predmeta_katedri> getPripadnostPredmetaKatedris() {
		return this.pripadnostPredmetaKatedris;
	}

	public void setPripadnostPredmetaKatedris(List<Pripadnost_predmeta_katedri> pripadnostPredmetaKatedris) {
		this.pripadnostPredmetaKatedris = pripadnostPredmetaKatedris;
	}

	public Pripadnost_predmeta_katedri addPripadnostPredmetaKatedri(Pripadnost_predmeta_katedri pripadnostPredmetaKatedri) {
		getPripadnostPredmetaKatedris().add(pripadnostPredmetaKatedri);
		pripadnostPredmetaKatedri.setNastavnik(this);

		return pripadnostPredmetaKatedri;
	}

	public Pripadnost_predmeta_katedri removePripadnostPredmetaKatedri(Pripadnost_predmeta_katedri pripadnostPredmetaKatedri) {
		getPripadnostPredmetaKatedris().remove(pripadnostPredmetaKatedri);
		pripadnostPredmetaKatedri.setNastavnik(null);

		return pripadnostPredmetaKatedri;
	}

}