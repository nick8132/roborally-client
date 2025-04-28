package dk.dtu.compute.se.pisd.roborally.gameselection.controller;

import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import dk.dtu.compute.se.pisd.roborally.gameselection.model.OnlineState;
import dk.dtu.compute.se.pisd.roborally.gameselection.model.User;
import dk.dtu.compute.se.pisd.roborally.gameselection.view.AppDialogs;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

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

    //Added back things (after making sure teh program isn't breaking, delete note in final )
    public void signIn(String name) {
        if (name.length() >= 4) {
            try {
                List<User> users = restClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/users/searchusers")  // ⚡ make sure your backend matches this!
                                .queryParam("name", name)
                                .build())
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<User>>() {});

                if (!users.isEmpty()) {
                    setOnlineUser(users.get(0));
                } else {
                    System.out.println("No user found with name: " + name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void setOnlineUser(User user) {
        this.onlineState.setOnlineUser(user);
    }

    public void signOut() {
        this.onlineState.setOnlineUser(null);
        this.onlineState.setSignedIn(false);
    }
}
