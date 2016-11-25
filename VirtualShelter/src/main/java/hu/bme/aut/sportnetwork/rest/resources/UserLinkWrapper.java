package hu.bme.aut.sportnetwork.rest.resources;

public class UserLinkWrapper {
	
	private String name;
	
	private Long id;

	public UserLinkWrapper() {
	}

	public UserLinkWrapper(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
