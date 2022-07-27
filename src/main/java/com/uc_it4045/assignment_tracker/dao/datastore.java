package com.uc_it4045.assignment_tracker.dao;

// Once you get your DAO figured out you need to have @Repository annotation..
/*Need a access modifier at the like: public */class UserDao{
    private String firstName;
    private String lastName;
    UserDao(){
        com.uc_it4045.assignment_tracker.dto.User firstName = new com.uc_it4045.assignment_tracker.dto.User();
        com.uc_it4045.assignment_tracker.dto.User lastName = new com.uc_it4045.assignment_tracker.dto.User();
        this.firstName = firstName.getFirstName();
        this.lastName = lastName.getLastName();
    }

}

// There should only be one "main" and that is the project start up in your AssignmentTrackerApplication
// You also have UserDao nested in the datastore.java??
//class datastore{
//public static void main(String[] args){
//
//}
//}