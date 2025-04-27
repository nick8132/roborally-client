package dk.dtu.compute.se.pisd.roborally.gameselection.model;

public class OnlineState {
    /** The User who has successfully signed in */
    private User onlineUser;

    /** Whether the client believes it’s currently “signed in” */
    private boolean signedIn;

    public OnlineState() {
        this.signedIn = false;
    }

    public User getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(User onlineUser) {
        this.onlineUser = onlineUser;
        this.signedIn = (onlineUser != null);
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    @Override
    public String toString() {
        return "OnlineState{ signedIn=" + signedIn +
                (onlineUser != null ? ", user=" + onlineUser.getName() : "") +
                " }";
    }
}
