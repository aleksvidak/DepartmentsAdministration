package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Nastavnik_Zvanje database table.
 * 
 */
@Entity
@NamedQuery(name="Nastavnik_Zvanje.findAll", query="SELECT n FROM Nastavnik_Zvanje n")
public class Nastavnik_Zvanje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_NastavnikZvanje;

	@Column(name="Datum_Postavljenja")
	private String datum_Postavljenja;

	//bi-directional many-to-one association to Nastavnik
	@ManyToOne
	@JoinColumn(name="Id_nastavnika")
	private Nastavnik nastavnik;

	//bi-directional many-to-one association to Zvanje
	@ManyToOne
	@JoinColumn(name="ID_zvanja")
	private Zvanje zvanje;

	public Nastavnik_Zvanje() {
	}

	public int getID_NastavnikZvanje() {
		return this.ID_NastavnikZvanje;
	}

	public void setID_NastavnikZvanje(int ID_NastavnikZvanje) {
		this.ID_NastavnikZvanje = ID_NastavnikZvanje;
	}

	public Object getDatum_Postavljenja() {
		return this.datum_Postavljenja;
	}

	public void setDatum_Postavljenja(String datum_Postavljenja) {
		this.datum_Postavljenja = datum_Postavljenja;
	}

	public Nastavnik getNastavnik() {
		return this.nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}

	public Zvanje getZvanje() {
		return this.zvanje;
	}

	public void setZvanje(Zvanje zvanje) {
		this.zvanje = zvanje;
	}

}