package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Pripadnost_predmeta_katedri database table.
 * 
 */
@Entity
@NamedQuery(name="Pripadnost_predmeta_katedri.findAll", query="SELECT p FROM Pripadnost_predmeta_katedri p")
public class Pripadnost_predmeta_katedri implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id_pripadnosti")
	private int id_pripadnosti;

	//bi-directional many-to-one association to Katedra
	@ManyToOne
	@JoinColumn(name="Id_katedre_Fk")
	private Katedra katedra;

	//bi-directional many-to-one association to Nastavnik
	@ManyToOne
	@JoinColumn(name="Id_nastavnika_Fk")
	private Nastavnik nastavnik;

	//bi-directional many-to-one association to Predmeti
	@ManyToOne
	@JoinColumn(name="Id_predmeta_Fk")
	private Predmeti predmeti;

	public Pripadnost_predmeta_katedri() {
	}

	public int getId_pripadnosti() {
		return this.id_pripadnosti;
	}

	public void setId_pripadnosti(int id_pripadnosti) {
		this.id_pripadnosti = id_pripadnosti;
	}

	public Katedra getKatedra() {
		return this.katedra;
	}

	public void setKatedra(Katedra katedra) {
		this.katedra = katedra;
	}

	public Nastavnik getNastavnik() {
		return this.nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}

	public Predmeti getPredmeti() {
		return this.predmeti;
	}

	public void setPredmeti(Predmeti predmeti) {
		this.predmeti = predmeti;
	}

}