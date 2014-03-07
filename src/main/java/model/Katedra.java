package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Katedra database table.
 * 
 */
@Entity
@NamedQuery(name="Katedra.findAll", query="SELECT k FROM Katedra k")
public class Katedra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_katedre;

	@Column(name="Naziv_katedre")
	private String naziv_katedre;

	//bi-directional many-to-one association to Nastavnik
	@ManyToOne
	@JoinColumn(name="ID_nast_Rukovodilac_FK")
	private Nastavnik nastavnik1;

	//bi-directional many-to-one association to Nastavnik
	@ManyToOne
	@JoinColumn(name="ID_nast_Sekretar_FK")
	private Nastavnik nastavnik2;

	//bi-directional many-to-one association to Laboratorija
	@OneToMany(mappedBy="katedra")
	private List<Laboratorija> laboratorijas;

	//bi-directional many-to-one association to Pripadnost_Nastavnika_katedri
	@OneToMany(mappedBy="katedra")
	private List<Pripadnost_Nastavnika_katedri> pripadnostNastavnikaKatedris;

	//bi-directional many-to-one association to Pripadnost_predmeta_katedri
	@OneToMany(mappedBy="katedra")
	private List<Pripadnost_predmeta_katedri> pripadnostPredmetaKatedris;

	public Katedra() {
	}

	public int getID_katedre() {
		return this.ID_katedre;
	}

	public void setID_katedre(int ID_katedre) {
		this.ID_katedre = ID_katedre;
	}

	public String getNaziv_katedre() {
		return this.naziv_katedre;
	}

	public void setNaziv_katedre(String naziv_katedre) {
		this.naziv_katedre = naziv_katedre;
	}

	public Nastavnik getNastavnik1() {
		return this.nastavnik1;
	}

	public void setNastavnik1(Nastavnik nastavnik1) {
		this.nastavnik1 = nastavnik1;
	}

	public Nastavnik getNastavnik2() {
		return this.nastavnik2;
	}

	public void setNastavnik2(Nastavnik nastavnik2) {
		this.nastavnik2 = nastavnik2;
	}

	public List<Laboratorija> getLaboratorijas() {
		return this.laboratorijas;
	}

	public void setLaboratorijas(List<Laboratorija> laboratorijas) {
		this.laboratorijas = laboratorijas;
	}

	public Laboratorija addLaboratorija(Laboratorija laboratorija) {
		getLaboratorijas().add(laboratorija);
		laboratorija.setKatedra(this);

		return laboratorija;
	}

	public Laboratorija removeLaboratorija(Laboratorija laboratorija) {
		getLaboratorijas().remove(laboratorija);
		laboratorija.setKatedra(null);

		return laboratorija;
	}

	public List<Pripadnost_Nastavnika_katedri> getPripadnostNastavnikaKatedris() {
		return this.pripadnostNastavnikaKatedris;
	}

	public void setPripadnostNastavnikaKatedris(List<Pripadnost_Nastavnika_katedri> pripadnostNastavnikaKatedris) {
		this.pripadnostNastavnikaKatedris = pripadnostNastavnikaKatedris;
	}

	public Pripadnost_Nastavnika_katedri addPripadnostNastavnikaKatedri(Pripadnost_Nastavnika_katedri pripadnostNastavnikaKatedri) {
		getPripadnostNastavnikaKatedris().add(pripadnostNastavnikaKatedri);
		pripadnostNastavnikaKatedri.setKatedra(this);

		return pripadnostNastavnikaKatedri;
	}

	public Pripadnost_Nastavnika_katedri removePripadnostNastavnikaKatedri(Pripadnost_Nastavnika_katedri pripadnostNastavnikaKatedri) {
		getPripadnostNastavnikaKatedris().remove(pripadnostNastavnikaKatedri);
		pripadnostNastavnikaKatedri.setKatedra(null);

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
		pripadnostPredmetaKatedri.setKatedra(this);

		return pripadnostPredmetaKatedri;
	}

	public Pripadnost_predmeta_katedri removePripadnostPredmetaKatedri(Pripadnost_predmeta_katedri pripadnostPredmetaKatedri) {
		getPripadnostPredmetaKatedris().remove(pripadnostPredmetaKatedri);
		pripadnostPredmetaKatedri.setKatedra(null);

		return pripadnostPredmetaKatedri;
	}

}