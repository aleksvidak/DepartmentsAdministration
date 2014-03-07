package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Login database table.
 * 
 */
@Entity
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_Login;

	private int ID_nastavnika;

	@Column(name="KorisnickoIme")
	private String korisnickoIme;

	@Column(name="Lozinka")
	private String lozinka;

	@Column(name="Privilegija")
	private String privilegija;

	public Login() {
	}

	public int getID_Login() {
		return this.ID_Login;
	}

	public void setID_Login(int ID_Login) {
		this.ID_Login = ID_Login;
	}

	public int getID_nastavnika() {
		return this.ID_nastavnika;
	}

	public void setID_nastavnika(int ID_nastavnika) {
		this.ID_nastavnika = ID_nastavnika;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return this.lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPrivilegija() {
		return this.privilegija;
	}

	public void setPrivilegija(String privilegija) {
		this.privilegija = privilegija;
	}

}