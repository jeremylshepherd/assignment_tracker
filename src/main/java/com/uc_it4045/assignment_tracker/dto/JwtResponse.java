package com.uc_it4045.assignment_tracker.dto;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public String getToken() {
        return jwttoken;
    }

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
