package edu.uncc.evaluation;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.List;

//#7 From here we need to create a Contact adapter!!! we will form the logic here
public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects){
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list_item, parent, false); //*****#8 Hold the phone here!! Were creating a list of a custom setup, as a result, we will need to insert a custom layout file. we will namme it contact_list_item in this case!!!!
            //****Ok so our layout file is created and now our list should format correctly now.
        }


        Contact contact = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);
        TextView textViewGroup = convertView.findViewById(R.id.textViewGroup);

        textViewName.setText(contact.getName());
        textViewPhone.setText(contact.getPhone());
        textViewGroup.setText(contact.getGroup());

        return convertView;
    }//OK here it is, the contact adapter is officially created!! This will allow the list to display in accordance to the custom view and the adapter will display the appropriate list.
}
