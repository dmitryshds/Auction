package biz.bagira.auction.entities;

public enum UserProfileType {
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	String userProfileType;

	UserProfileType() {
	}

	UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}



	public String getUserProfileType(){
		return userProfileType;
	}
	
}
