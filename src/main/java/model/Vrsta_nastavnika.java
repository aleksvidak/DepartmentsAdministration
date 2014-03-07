package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Vrsta_nastavnika database table.
 * 
 */
@Entity
@NamedQuery(name="Vrsta_nastavnika.findAll", query="SELECT v FROM Vrsta_nastavnika v")
public class Vrsta_nastavnika implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id_vrste_nastavnika")
	private int id_vrste_nastavnika;

	@Column(name="Naziv_vrste_nastavnika")
	private String naziv_vrste_nastavnika;

	//bi-directional many-to-one association to Nastavnik
	@OneToMany(mappedBy="vrstaNastavnika")
	private List<Nastavnik> nastavniks;

	public Vrsta_nastavnika() {
	}

	public int getId_vrste_nastavnika() {
		return this.id_vrste_nastavnika;
	}

	public void setId_vrste_nastavnika(int id_vrste_nastavnika) {
		this.id_vrste_nastavnika = id_vrste_nastavnika;
	}

	public Object getNaziv_vrste_nastavnika() {
		return this.naziv_vrste_nastavnika;
	}

	public void setNaziv_vrste_nastavnika(String naziv_vrste_nastavnika) {
		this.naziv_vrste_nastavnika = naziv_vrste_nastavnika;
	}

	public List<Nastavnik> getNastavniks() {
		return this.nastavniks;
	}

	public void setNastavniks(List<Nastavnik> nastavniks) {
		this.nastavniks = nastavniks;
	}

	public Nastavnik addNastavnik(Nastavnik nastavnik) {
		getNastavniks().add(nastavnik);
		nastavnik.setVrstaNastavnika(this);

		return nastavnik;
	}

	public Nastavnik removeNastavnik(Nastavnik nastavnik) {
		getNastavniks().remove(nastavnik);
		nastavnik.setVrstaNastavnika(null);

		return nastavnik;
	}

}