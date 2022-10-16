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
    public void showContactDetails(Contact contact) { //This is how our program will know to send us to our detail fragment after we click our list in our main fragment.
//    getSupportFragmentManager().beginTransaction()
//            .replace(R.id.containerView, DetailFragment.newInstance(contact))
//            .addToBackStack(null)
//            .commit();
    }//Currently we do not have our detail fragment set up so we will leave this code commented out.


    @Override
    public void addContact(Contact contact) {

    }

    @Override
    public void cancelAddContact() {
    getSupportFragmentManager().popBackStack();//From here we will pop to our back Stack, we need to do this to go back to our previous screen our mainFragment, this button is in our addContactFragment
    }

    @Override
    public void gotoPickGroupFragment() {

    }
}