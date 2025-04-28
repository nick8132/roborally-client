package dk.dtu.compute.se.pisd.roborally.gameselection.controller;

import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import dk.dtu.compute.se.pisd.roborally.gameselection.model.OnlineState;
import dk.dtu.compute.se.pisd.roborally.gameselection.model.User;
import dk.dtu.compute.se.pisd.roborally.gameselection.view.AppDialogs;
import org.springframework.web.client.RestClient;

public class OnlineController {

    private OnlineState onlineState;
    private RestClient restClient;
    private AppDialogs appDialogs;

    public OnlineController(AppController appController) {
        this.onlineState = new OnlineState();
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/roborally/")
                .build();
        this.appDialogs = new AppDialogs(this);
    }

    public OnlineState getOnlineState() {
        return onlineState;
    }

    public AppDialogs getAppDialogs() {
        return appDialogs;
    }

    public void showSignInDialog() {
        appDialogs.showSignInDialog();
    }

    public void signIn(String name) {
        // same as before
    }

    public void setOnlineUser(User user) {
        this.onlineState.setOnlineUser(user);
    }

    public void signOut() {
        this.onlineState.setOnlineUser(null);
        this.onlineState.setSignedIn(false);
    }
}
