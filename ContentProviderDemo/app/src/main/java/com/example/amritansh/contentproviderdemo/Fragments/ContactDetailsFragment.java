package com.example.amritansh.contentproviderdemo.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amritansh.contentproviderdemo.Models.Contact;
import com.example.amritansh.contentproviderdemo.R;

public class ContactDetailsFragment extends Fragment {

    public ContactDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();

        TextView name = view.findViewById(R.id.name);
        TextView number = view.findViewById(R.id.number);

        number.setText(args.getString("num"));
        name.setText(args.getString("name"));

    }

}
