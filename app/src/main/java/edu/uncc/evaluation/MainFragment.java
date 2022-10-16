package edu.uncc.evaluation;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import edu.uncc.evaluation.databinding.FragmentMainBinding;

// Inside our main fragment we need to get our logic to post into this fragment.
//#4 we will additionally need to call in our variables for our methods in our mainfragment.

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_NAME = "param-name";
    private String mName;
    private ArrayList<Contact> mContacts = new ArrayList<>();
    //From here we will call our private variables.

//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters

    //#4 Here, we need to call into our main fragment
    public static MainFragment newInstance(String name) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_PARAM_NAME);
        }
    }


    //We will set binding to our fragment main binding and will use this or our bindings


    FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false); this must be modified to account for the binding
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    //****** OK so from here we can post into our main screen, from this point more logic will be added to progress to the contacts. at this point we cannot get name to change!!!!!

    //From here, we will need to get the main fragment to show our list for contacts additionally this will manage sending us to our details page.

    ContactAdapter adapter; //******* #7 We cant progress until we have developed a Contact adapter!!!! From here we need to make a new class immediately called ContactAdapter!

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){ // our onView created is started from here this will form out the elements we want
        super.onViewCreated(view, savedInstanceState);
        binding.textViewWelcome.setText("Welcome " + mName); //From here this will make the name actually change to what we typed.

    /*problematic*/    mContacts = mListener.getContactsList();//********Ok, so our mListener is not working correctly here, we need to first  create an onattach and interface for this
        if(mContacts.size() == 0){
            binding.textViewContactCount.setText("You have 0 contacts");
        }   else if(mContacts.size() == 1){
            binding.textViewContactCount.setText("You have 1 contact");
        } else {
            binding.textViewContactCount.setText("You have " + mContacts.size() +" contacts");
        }

        //Ok so we got the UI up and running, however we need to set the adapters for items to click and view properly
        //#9 Get our onclicks to display properly if we dont our app will just crash!!
        adapter = new ContactAdapter(getActivity(), R.layout.contact_list_item, mContacts);//** As mentioned before, we needed that custom layout file for our list display here.
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Contact contact = mContacts.get(position);
                mListener.showContactDetails(contact);
            }
        });//From here we have develpoed an clickalbe list, This allows us to set the listing and set the elements to be clickable.

        binding.buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoAddContact();
            }
        });//From here this will make the program call into the next fragment when we click the list
    }

    //#6 create an onAttach and interface for our mListener
    MainListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        /*problematic*/    mListener = (MainListener) context;
    }

    interface MainListener{
        ArrayList<Contact> getContactsList(); //From here were a little ahead of ourselves this is here going to send us to add contact and contact detalis this will be clarified in the buttons and each button will send us to the appropriate fragment
        void gotoAddContact();
        void showContactDetails(Contact contact);
    }


}//********* What is written so far, the main fragment is complete  however line 125 will not let me post into the main fragment, however this is the main code written for the main fragment and should allow the main list to display in it.

//***#10 From here We will need to develop access to our add contract fragment. We will go to our main Activity and start from there