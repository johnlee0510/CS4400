
public class User {
	private String username;
	private String emailAddress;
	private String password;
	private int isCityOfficial;
	private int isAdmin;
	private int isCityScientist;
	private String userType;

	public User(String username, String emailAddress, String password, int isCityOfficial, int isAdmin, int isCityScientist) {
		this.username = username;
		this.emailAddress = emailAddress;
		this.password = password;
		this.isCityOfficial = isCityOfficial;
		this.isAdmin = isAdmin;
		this.isCityScientist = isCityScientist;
		if (isCityOfficial == 1) {
			userType = "City Official";
		} else if (isAdmin == 1) {
			userType = "Admin";
		} else if (isCityScientist == 1) {
			userType = "City Scientist";
		} else {
			userType = "User";
		}
	}

	public String getUsername() {
		return username;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
 

	public String getPassword() {
		return password;
	}


	public int getIsCityOfficial() {
		return isCityOfficial;
	}


	public int getIsAdmin() {
		return isAdmin;
	}

	public int getIsCityScientist() {
		return isCityScientist;
	}
	public String getUserType() {
		return userType;
	}
}
