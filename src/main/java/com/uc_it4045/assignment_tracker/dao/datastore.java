package com.uc_it4045.assignment_tracker.dao;


class UserDao{
    public String firstName;
    public String lastName;
    UserDao(){
        com.uc_it4045.assignment_tracker.dto.User firstName = new com.uc_it4045.assignment_tracker.dto.User();
        com.uc_it4045.assignment_tracker.dto.User lastName = new com.uc_it4045.assignment_tracker.dto.User();
        this.firstName = firstName.getFirstName();
        this.lastName = lastName.getLastName();
    }

}
class datastore{
public static void main(String[] args){

}
}