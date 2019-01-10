package project.user;

public class User {
	Integer id;
	String username;
	String password;
	String autority;

	public User(int id, String username) {
		this.id = id;
		this.username = username;
		this.autority = "user";
	}

	public User(Integer id, String username, String autority) {
		this(id, username);
		this.autority = autority;
	}

	public User(Integer id, String username, String password, String autority) {
		this(id, username, autority);
		this.password = password;
	}

	public User(String username, String password, String autority) {
		this.password = password;
		this.username = username;
		this.autority = autority;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAutority() {
		return autority;
	}

	public void setAutority(String autority) {
		this.autority = autority;
	}

}
