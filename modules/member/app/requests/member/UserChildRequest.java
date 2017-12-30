package requests.member;

import play.data.validation.Constraints.Required;

public class UserChildRequest {
    
    @Required
    private String userData;

    @Required
    private String userAddress;

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
