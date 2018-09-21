package com.example.amritansh.contentproviderdemo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amritansh.contentproviderdemo.Fragments.ContactsFragment;
import com.example.amritansh.contentproviderdemo.Models.Contact;
import com.example.amritansh.contentproviderdemo.R;
import com.example.amritansh.contentproviderdemo.interfaces.ContactClickListner;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private ArrayList<Contact> contactsList;
    ContactsFragment context;

    public ContactAdapter(@NonNull ContactsFragment context) {
        super();
        this.context = context;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.contact_row, viewGroup, false);

        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder contactHolder, int i) {
        Contact contact = contactsList.get(i);
        contactHolder.setContactDetails(contact);
    }

    @Override
    public int getItemCount() {
        if (contactsList.size() != 0) {
            return contactsList.size();
        }
        return 0;
    }

    public void updateContactList(ArrayList<Contact> contactsList) {
        this.contactsList = contactsList;
        notifyDataSetChanged();
    }


    public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView number, name;
        private Contact contact;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.contact_number);
            name = itemView.findViewById(R.id.contact_name);

            itemView.setOnClickListener(this);
        }

        public void setContactDetails(Contact contact) {
            this.contact = contact;
            number.setText(contact.getNumber());
            name.setText(contact.getName());
        }

        @Override
        public void onClick(View v) {
            ContactClickListner listner = context;
            listner.onContactClick(contact);
        }
    }
}
