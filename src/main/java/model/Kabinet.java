package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Kabinet database table.
 * 
 */
@Entity
@NamedQuery(name="Kabinet.findAll", query="SELECT k FROM Kabinet k")
public class Kabinet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id_kabineta")
	private int id_kabineta;

	@Column(name="Broj_kabineta")
	private String broj_kabineta;

	//bi-directional many-to-one association to Nastavnik
	@OneToMany(mappedBy="kabinet")
	private List<Nastavnik> nastavniks;

	public Kabinet() {
	}

	public int getId_kabineta() {
		return this.id_kabineta;
	}

	public void setId_kabineta(int id_kabineta) {
		this.id_kabineta = id_kabineta;
	}

	public Object getBroj_kabineta() {
		return this.broj_kabineta;
	}

	public void setBroj_kabineta(String broj_kabineta) {
		this.broj_kabineta = broj_kabineta;
	}

	public List<Nastavnik> getNastavniks() {
		return this.nastavniks;
	}

	public void setNastavniks(List<Nastavnik> nastavniks) {
		this.nastavniks = nastavniks;
	}

	public Nastavnik addNastavnik(Nastavnik nastavnik) {
		getNastavniks().add(nastavnik);
		nastavnik.setKabinet(this);

		return nastavnik;
	}

	public Nastavnik removeNastavnik(Nastavnik nastavnik) {
		getNastavniks().remove(nastavnik);
		nastavnik.setKabinet(null);

		return nastavnik;
	}

}