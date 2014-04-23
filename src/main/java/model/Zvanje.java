package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Zvanje database table.
 * 
 */
@Entity
@NamedQuery(name="Zvanje.findAll", query="SELECT z FROM Zvanje z")
public class Zvanje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_zvanja;

	@Column(name="Naziv_zvanja")
	private String naziv_zvanja;

	//bi-directional many-to-one association to Nastavnik_Zvanje
	@OneToMany(mappedBy="zvanje")
	private List<Nastavnik_Zvanje> nastavnikZvanjes;

	public Zvanje() {
	}

	public int getID_zvanja() {
		return this.ID_zvanja;
	}

	public void setID_zvanja(int ID_zvanja) {
		this.ID_zvanja = ID_zvanja;
	}

	public Object getNaziv_zvanja() {
		return this.naziv_zvanja;
	}

	public void setNaziv_zvanja(String naziv_zvanja) {
		this.naziv_zvanja = naziv_zvanja;
	}

	public List<Nastavnik_Zvanje> getNastavnikZvanjes() {
		return this.nastavnikZvanjes;
	}

	public void setNastavnikZvanjes(List<Nastavnik_Zvanje> nastavnikZvanjes) {
		this.nastavnikZvanjes = nastavnikZvanjes;
	}

	public Nastavnik_Zvanje addNastavnikZvanje(Nastavnik_Zvanje nastavnikZvanje) {
		getNastavnikZvanjes().add(nastavnikZvanje);
		nastavnikZvanje.setZvanje(this);

		return nastavnikZvanje;
	}

	public Nastavnik_Zvanje removeNastavnikZvanje(Nastavnik_Zvanje nastavnikZvanje) {
		getNastavnikZvanjes().remove(nastavnikZvanje);
		nastavnikZvanje.setZvanje(null);

		return nastavnikZvanje;
	}

}