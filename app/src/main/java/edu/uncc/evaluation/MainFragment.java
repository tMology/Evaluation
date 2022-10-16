package edu.uncc.evaluation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    //****** OK so from here we can post into our main screen, from this point more logic will be added to progress to the contacts. at this point we cannot get name to change!!!!!
}