package edu.uncc.evaluation;

//#11 Within our addContact fragment we will now create our fragment to add a contact.

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.uncc.evaluation.databinding.FragmentAddContactBinding; //Our bindings are called here thats how we will declare them

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddContactFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContactFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    //In this case, no parameters are used above.

    public AddContactFragment() {  //We do still need our empty Constructor
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment AddContactFragment.
     */
//    // TODO: Rename and change types and number of parameters
//    public static AddContactFragment newInstance(String param1, String param2) {
//        AddContactFragment fragment = new AddContactFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    //Further, similar to above, we dont need parameters for our add contact.


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        //We will not need the arguments since we have no parameters
    }

    FragmentAddContactBinding binding; //From here our binding is declared
    String groupSelected; // Our string for our group selected is declared.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_contact, container, false); Many of the objects I am commenting here are the default template for this fragment, this will be modified down below to account for our binding to note this is the template by default for this fragment however.
        binding = FragmentAddContactBinding.inflate(inflater, container, false);
        return binding.getRoot();//As can be seen here the return needs to be called seperately in this case to call the binding root.
    }

    //#12 From here, we will need to create what is viewed on this next fragment, so from here we will create our onViewCreate
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        if(groupSelected != null){
            binding.textViewGroupSelected.setText("Group " + groupSelected);
        }   else{
            binding.textViewGroupSelected.setText("Group: N/A");
        }//From here this will show the group segment if it is populated or not populated with data

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.cancelAddContact(); //#13**** Similar case here hold the phone!! We need an onAttach and interface for our mlistener!
            }
        });//From here our Cancel Button is set.

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.editTextContactName.getText().toString();
                String phone = binding.editTextContactPhone.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter name !!", Toast.LENGTH_SHORT).show();
                } else if (phone.isEmpty()){
                    Toast.makeText(getActivity(), "Enter phone !!", Toast.LENGTH_SHORT).show();
                } else if(groupSelected == null){
                    Toast.makeText(getActivity(), "Select a group" , Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(name, phone, groupSelected);
                    mlistener.addContact(contact);
                }
            }
        });//From here the Submit button will check if the user entered data into all appropriate fields...

        binding.buttonSetDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.gotoPickGroupFragment();
            }
        });//From here, this button will send us straight to our pick group fragment

        getActivity().setTitle("Add Contact"); // Our title is set
    }

    //#13 interface and on attach created!
    AddContactListener mlistener; //We will declare our AddContactListener, keep in mind however, if we do not have an interface this will red out, so lets create our interface

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mlistener = (AddContactListener) context;
    }

    interface AddContactListener{
        void addContact(Contact contact);//Were again, a little ahead this isnt active at this time yet
        void cancelAddContact();
        void gotoPickGroupFragment();// Same case as addContact's explanation.
    }
}