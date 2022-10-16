package edu.uncc.evaluation;


//#3 We will store appropriate data variables in this class in this case we want name, phone, and a group.

import java.io.Serializable;

public class Contact implements Serializable  { //We want out objects so we must declare serilizable
    public String name, phone, group; //Our apporpriate data is called

    public Contact(String name, String phone, String group){ //Contact is created and arguemnts are called
        this.name = name;
        this.phone = phone;
        this.group = group;
    }

    @Override
    public String toString(){
        return "Contact{" +
                "name'" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", group'" + group + '\'' +
                '}';  //I persume that this calls the contact data into a string in this case.
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getGroup() {return group;}

    public void setGroup (String group) {this.group = group;}

    // and our setters and getters are set down here
}
