package com.revature.eggheads.backendp2.authentication;


public class AuthenticationResponse {
    private final String jwt;
    private final int userId;

    public AuthenticationResponse(String jwt, int userId) {
        this.jwt = jwt;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public int getUserId() { return userId; }
}
