package edu.uncc.evaluation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.MainListener, AddContactFragment.AddContactListener {

    String mName;//Delcare the String Variable
    ArrayList<Contact> contacts = new ArrayList<>(); // We need to call our array list !! Note we need to create a contact class with appropriate variables!!!!!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //#2 From here well call into our main fragment test data is inserted for reference

        mName = getIntent().getStringExtra("name");

        contacts.add(new Contact("Test", "123-456-7890", "someone"));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, MainFragment.newInstance(mName)) // new instance will not just work alone, it is going to want a a newinstance method called into the main fragment. from here we will go to #4
                .commit();
        //We cannot progress without creating a contact class to check our variables in #3 we will create a contact class
    }

    //Note here! we cannot have override up until we implement our fragment listeners up top in the class
    @Override
    public ArrayList<Contact> getContactsList() { return contacts; } // So far getContactsList needs to be called this is called from the main fragment

    @Override
    public void gotoAddContact(){ //Similarly gotoAddContact
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new AddContactFragment(), "add-contact-fragment")
                .addToBackStack(null)
                .commit();
    }//#11 From here we will move to our addContact Fragment

    @Override
    public void showContactDetails(Contact contact) {

    }


    @Override
    public void addContact(Contact contact) {

    }

    @Override
    public void cancelAddContact() {

    }

    @Override
    public void gotoPickGroupFragment() {

    }
}