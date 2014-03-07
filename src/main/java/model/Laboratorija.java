package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Laboratorija database table.
 * 
 */
@Entity
@NamedQuery(name="Laboratorija.findAll", query="SELECT l FROM Laboratorija l")
public class Laboratorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id_laboratorije")
	private int id_laboratorije;

	@Column(name="Naziv_laboratorije")
	private String naziv_laboratorije;

	@Column(name="Sajt")
	private String sajt;

	//bi-directional many-to-one association to Katedra
	@ManyToOne
	@JoinColumn(name="ID_katedre_FK")
	private Katedra katedra;

	public Laboratorija() {
	}

	public int getId_laboratorije() {
		return this.id_laboratorije;
	}

	public void setId_laboratorije(int id_laboratorije) {
		this.id_laboratorije = id_laboratorije;
	}

	public String getNaziv_laboratorije() {
		return this.naziv_laboratorije;
	}

	public void setNaziv_laboratorije(String naziv_laboratorije) {
		this.naziv_laboratorije = naziv_laboratorije;
	}

	public String getSajt() {
		return this.sajt;
	}

	public void setSajt(String sajt) {
		this.sajt = sajt;
	}

	public Katedra getKatedra() {
		return this.katedra;
	}

	public void setKatedra(Katedra katedra) {
		this.katedra = katedra;
	}

}