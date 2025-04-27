package dk.dtu.compute.se.pisd.roborally.gameselection.controller;

import dk.dtu.compute.se.pisd.roborally.gameselection.model.OnlineState;
import dk.dtu.compute.se.pisd.roborally.gameselection.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class OnlineController {

    private OnlineState onlineState;
    private RestClient restClient;

    public OnlineController() {
        this.onlineState = new OnlineState();
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/roborally/") // ⚡ adjust if needed
                .build();
    }

    public OnlineState getOnlineState() {
        return onlineState;
    }

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
        System.out.println("Signed in as: " + user.getName());
    }

    public void signOut() {
        this.onlineState.setOnlineUser(null);
        this.onlineState.setSignedIn(false);
        System.out.println("Signed out");
    }
}
