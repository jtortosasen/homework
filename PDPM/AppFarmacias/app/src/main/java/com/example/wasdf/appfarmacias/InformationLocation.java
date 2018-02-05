package com.example.wasdf.appfarmacias;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InformationLocation extends Fragment {

    private int idSite;
    static private int idOpenSite = -1;

    private TextView textStreet, textPhone, textName, textOpenPharm, textOpenPhone;
    private ImageView imagePharmacy;
    private Button button;
    
    public InformationLocation(){}

    public static InformationLocation newInstance(int id) {
        InformationLocation instance = new InformationLocation();
        Bundle bundle = new Bundle();
        bundle.putInt("idSite", id);
        instance.setArguments(bundle);

        return instance;
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        idSite = getArguments().getInt("idSite");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // This is the up button
            case android.R.id.home:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if(fm.getBackStackEntryCount() > 0){
                    fm.popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information_location, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textName = view.findViewById(R.id.textViewName);
        textPhone = view.findViewById(R.id.textViewPhone);
        textStreet = view.findViewById(R.id.textViewStreet);
        textOpenPharm = view.findViewById(R.id.textViewPharmOpen);
        textOpenPhone = view.findViewById(R.id.textViewPharmOpen);
        imagePharmacy = view.findViewById(R.id.imageView);
        button = view.findViewById(R.id.myButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOpenPharmacy();
            }
        });



        AccessData dataMap = new AccessData();

        textName.setText(dataMap.getNameSite(idSite));
        textPhone.setText(dataMap.getPhoneSite(idSite));
        textStreet.setText(dataMap.getStreetSite(idSite));

        //Picasso.with(getContext()).load(dataMap.getImageSite(idSite)).fit().into(imagePharmacy);
    }




    public void buttonOpenPharmacy(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("id");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                idOpenSite = Math.toIntExact((Long)dataSnapshot.child("idOpen").getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        if(idOpenSite != -1){
            AccessData dataMap = new AccessData();
            textOpenPharm.setText(dataMap.getStreetSite(idOpenSite));
            textOpenPhone.setText(dataMap.getPhoneSite(idOpenSite));
        }
    }

}
