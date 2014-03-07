package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Predmeti database table.
 * 
 */
@Entity
@NamedQuery(name="Predmeti.findAll", query="SELECT p FROM Predmeti p")
public class Predmeti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_predmeta;

	@Column(name="Br_bodova")
	private short br_bodova;

	@Column(name="Br_casova_ostalo")
	private short br_casova_ostalo;

	@Column(name="Br_casova_predvanja")
	private short br_casova_predvanja;

	@Column(name="Br_casova_vezbi")
	private short br_casova_vezbi;

	@Column(name="Naziv_predmeta")
	private String naziv_predmeta;

	@Column(name="Semestar")
	private short semestar;

	//bi-directional many-to-one association to Pripadnost_predmeta_katedri
	@OneToMany(mappedBy="predmeti")
	private List<Pripadnost_predmeta_katedri> pripadnostPredmetaKatedris;

	public Predmeti() {
	}

	public int getID_predmeta() {
		return this.ID_predmeta;
	}

	public void setID_predmeta(int ID_predmeta) {
		this.ID_predmeta = ID_predmeta;
	}

	public short getBr_bodova() {
		return this.br_bodova;
	}

	public void setBr_bodova(short br_bodova) {
		this.br_bodova = br_bodova;
	}

	public short getBr_casova_ostalo() {
		return this.br_casova_ostalo;
	}

	public void setBr_casova_ostalo(short br_casova_ostalo) {
		this.br_casova_ostalo = br_casova_ostalo;
	}

	public short getBr_casova_predvanja() {
		return this.br_casova_predvanja;
	}

	public void setBr_casova_predvanja(short br_casova_predvanja) {
		this.br_casova_predvanja = br_casova_predvanja;
	}

	public short getBr_casova_vezbi() {
		return this.br_casova_vezbi;
	}

	public void setBr_casova_vezbi(short br_casova_vezbi) {
		this.br_casova_vezbi = br_casova_vezbi;
	}

	public Object getNaziv_predmeta() {
		return this.naziv_predmeta;
	}

	public void setNaziv_predmeta(String naziv_predmeta) {
		this.naziv_predmeta = naziv_predmeta;
	}

	public short getSemestar() {
		return this.semestar;
	}

	public void setSemestar(short semestar) {
		this.semestar = semestar;
	}

	public List<Pripadnost_predmeta_katedri> getPripadnostPredmetaKatedris() {
		return this.pripadnostPredmetaKatedris;
	}

	public void setPripadnostPredmetaKatedris(List<Pripadnost_predmeta_katedri> pripadnostPredmetaKatedris) {
		this.pripadnostPredmetaKatedris = pripadnostPredmetaKatedris;
	}

	public Pripadnost_predmeta_katedri addPripadnostPredmetaKatedri(Pripadnost_predmeta_katedri pripadnostPredmetaKatedri) {
		getPripadnostPredmetaKatedris().add(pripadnostPredmetaKatedri);
		pripadnostPredmetaKatedri.setPredmeti(this);

		return pripadnostPredmetaKatedri;
	}

	public Pripadnost_predmeta_katedri removePripadnostPredmetaKatedri(Pripadnost_predmeta_katedri pripadnostPredmetaKatedri) {
		getPripadnostPredmetaKatedris().remove(pripadnostPredmetaKatedri);
		pripadnostPredmetaKatedri.setPredmeti(null);

		return pripadnostPredmetaKatedri;
	}

}