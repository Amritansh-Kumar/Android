package com.example.amritansh.contentproviderdemo.Fragments;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amritansh.contentproviderdemo.Adapters.ContactAdapter;
import com.example.amritansh.contentproviderdemo.Models.Contact;
import com.example.amritansh.contentproviderdemo.R;
import com.example.amritansh.contentproviderdemo.interfaces.ContactClickListner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;


public class ContactsFragment extends Fragment implements ContactClickListner {

    private ContactAdapter contactAdapter;
    private RecyclerView contactsRV;
    private ArrayList<Contact> contactsList;

    //    private static final Uri CONTACT_URI = ContactsContract.Contacts.CONTENT_URI;
    private static final Uri CONTACT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    private static final String[] PROJECTION = {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            };

    public ContactsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contactsRV = view.findViewById(R.id.rv_contacts);

        contactsList = new ArrayList<>();
        contactsList = getContacts();
        setUpRecyclerView();
    }

//    private ArrayList<Contact> getContacts() {
//        HashSet<String> contactsSet = new HashSet<>();
//        ArrayList<Contact> contactsList = new ArrayList<>();
//
//        ContentResolver contentResolver = getActivity().getContentResolver();
//        String order = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
//
//        Cursor cursor = contentResolver.query(CONTACT_URI, null, null, null,
//                order);
//
//        int counter = 1;
//
//        while (cursor.moveToNext()) {
//            String name = cursor.getString(cursor.getColumnIndex(
//                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
//
//            String contactId = cursor.getString(cursor.getColumnIndex(
//                    ContactsContract.Contacts._ID));
//            ;
//
//            String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =? ";
//            String[] selectionArgs = {contactId};
//
//            Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                    null, selection, selectionArgs, null);
//
//            while (phones.moveToNext()) {
//                String number = phones.getString(phones.getColumnIndex
//                        (ContactsContract.CommonDataKinds.Phone.NUMBER));
//
//                Log.d("provider", "update content" + name);
//
//                if (!contactsSet.contains(name)) {
//                    contactsSet.add(name);
//                    Contact contact = new Contact(number, name);
//                    contactsList.add(contact);
//                }
//            }
//
//            phones.close();
//        }
//
//        cursor.close();
//        return contactsList;
//    }


    private ArrayList<Contact> getContacts() {
        HashSet<String> contactsSet = new HashSet<>();
        ArrayList<Contact> contactsList = new ArrayList<>();
        ContentResolver contentResolver = null;

        try {
            contentResolver = getActivity().getContentResolver();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        String order = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";

        Cursor cursor = null;
        if (contentResolver != null) {
            cursor = contentResolver.query(CONTACT_URI, PROJECTION, null, null,
                    order);
        }

        if (cursor == null) {
            Toast.makeText(getActivity(), "no contats found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);

                if (!contactsSet.contains(name)) {
                    contactsSet.add(name);
                    String number = cursor.getString(1);

                    Contact contact = new Contact(number, name);
                    contactsList.add(contact);
                }
            }
            cursor.close();
        }

        return contactsList;
    }

    private void setUpRecyclerView() {
        contactsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactAdapter = new ContactAdapter(this);
        contactsRV.setAdapter(contactAdapter);
        contactAdapter.updateContactList(contactsList);
    }

    @Override
    public void onContactClick(Contact contact) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Bundle args = new Bundle();
        args.putString("name", contact.getName());
        args.putString("num", contact.getNumber());
        Fragment detailsFrag = new ContactDetailsFragment();
        detailsFrag.setArguments(args);

        transaction.replace(R.id.container, detailsFrag);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
