package edu.uncc.evaluation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
//**21 we will then have every fragment and listener called in, this will conclude adding all listeners in the main activity so every button can function properly and fragment display properly
public class MainActivity extends AppCompatActivity implements MainFragment.MainListener, AddContactFragment.AddContactListener, GroupsFragment.GroupListener ,DetailFragment.DetailListener { //All of our fragments and listeners that accompany them need to be called, or the main activity will not display our info correctly

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
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.containerView, DetailFragment.newInstance(contact))
            .addToBackStack(null)
            .commit();
    }//Our detail fragment is created and now when the user clicks on the arrayList, they are able to pull up its details! This is also where our contact Detail's new instance is being called!

    //#14 From here we will create our details fragment this will lead us to the details that we click on our array list in our main fragment

    @Override
    public void addContact(Contact contact) { // From here well take the current contact we want and pop back to our add contacts fragment
        this.contacts.add(contact);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelAddContact() {
    getSupportFragmentManager().popBackStack();//From here we will pop to our back Stack, we need to do this to go back to our previous screen our mainFragment, this button is in our addContactFragment
    }

    //****#18Finally we will look into programming the final GroupFragment and this will finish the practice!!!!


    //**#22 we will get our remaining buttons to work correctly here and operate from there.
    @Override
    public void gotoPickGroupFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new GroupsFragment())
                .addToBackStack(null)
                .commit();
    }//This will make our group fragment appear where we can select our option



    @Override
    public void cancelGroupSelect() {
        getSupportFragmentManager().popBackStack();
    }//Our cancel button simply takes us back.

    @Override
    public void setGroupSelected(String group) {
        //This probably is the most complicated logically, here we want to set the group based on what we select and go back to our previous screen
        AddContactFragment fragment = (AddContactFragment) getSupportFragmentManager().findFragmentByTag("add-contact-Fragment");
        if(fragment != null){
            fragment.setGroupSelected(group);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void goBackFromDetail() { //From here we will program our back button for our detail screen
        getSupportFragmentManager().popBackStack();
    }
}