package com.uc_it4045.assignment_tracker.dao;


import com.uc_it4045.assignment_tracker.dto.User;

class UserDao{
    private String firstName;
    private String lastName;
    UserDao(){
        User user = new User();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

}
class datastore{
public static void main(String[] args){

}
}