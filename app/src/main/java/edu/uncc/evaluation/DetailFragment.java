package edu.uncc.evaluation;

//#14 We will write the logic here for our detail fragment

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.evaluation.databinding.FragmentDetailBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    public static final String ARG_PARAM_CONTACT = "ARG_PARAM_CONTACT";
    private Contact mContact;

    //Again, our variables are created

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    //As before, we don't need these parameters above

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(Contact contact) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CONTACT, contact);
        //We only need one string here
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = (Contact) getArguments().getSerializable(ARG_PARAM_CONTACT);
        }
    }

    FragmentDetailBinding binding; //We need to create our binding

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false);
        //Again we will modify this to set our create view correctly
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        //From here, this is where we will get our information to display correctly

        getActivity().setTitle("Detail");// To start, this will set the fragment title.

        binding.textViewContactName.setText(mContact.getName());//Set our name info also notice at the end that these call our methods from the contact class
        binding.textViewContactPhone.setText(mContact.getPhone());// This will set our phone
        binding.textViewContactGroup.setText(mContact.getGroup());


        //This sets our button to go back from our details page.
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goBackFromDetail(); //****#15As we can see here we need to create an onAttach and interface for mListener to perform its purpose.
            }
        });
    }

    //#15 onAttach and interface get created!

    DetailListener mListener;//#16 We will need to develop an interface for DetailListener.

    //#17 Then we create the onAttach
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (DetailListener) context;
    }

    //#16 DetailListener interface is developed for DetailListener
    interface DetailListener{
        void goBackFromDetail();
    }

}