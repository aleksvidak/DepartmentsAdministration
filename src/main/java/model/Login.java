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
	private String j_username; //spring security j_username

	@Column(name="Lozinka")
	private String j_password; //spring security j_password

	@Column(name="Privilegija")
	private String privilegija;

	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}

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

	/*public String getKorisnickoIme() {
		return this.j_username;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.j_username = korisnickoIme;
	}

	public String getLozinka() {
		return this.j_password;
	}

	public void setLozinka(String lozinka) {
		this.j_password = lozinka;
	}*/

	public String getPrivilegija() {
		return this.privilegija;
	}

	public void setPrivilegija(String privilegija) {
		this.privilegija = privilegija;
	}

}