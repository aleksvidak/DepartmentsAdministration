package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Pripadnost_Nastavnika_katedri database table.
 * 
 */
@Entity
@NamedQuery(name="Pripadnost_Nastavnika_katedri.findAll", query="SELECT p FROM Pripadnost_Nastavnika_katedri p")
public class Pripadnost_Nastavnika_katedri implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id_pripadnosti")
	private int id_pripadnosti;

	//bi-directional many-to-one association to Katedra
	@ManyToOne
	@JoinColumn(name="Id_katedre_FK")
	private Katedra katedra;

	//bi-directional many-to-one association to Nastavnik
	@ManyToOne
	@JoinColumn(name="Id_nastavnika_FK")
	private Nastavnik nastavnik;

	public Pripadnost_Nastavnika_katedri() {
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

}