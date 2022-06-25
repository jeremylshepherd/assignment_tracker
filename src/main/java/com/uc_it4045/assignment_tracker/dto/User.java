package com.uc_it4045.assignment_tracker.dto;

import lombok.Data;

//Lines 8 and 9 do the same thing you had below but in 2 lines total.. Power of using Lombok aka @Data
@Data
public class User {
    private String firstName;
    private String lastName;
}

// You didn't put any "Access Modifiers" when declaring your variables.. (public, private, or protected)

//public class User {
//    String firstName;
//    String lastName;
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//}
