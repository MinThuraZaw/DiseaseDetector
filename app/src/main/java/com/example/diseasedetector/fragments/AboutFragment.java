package com.example.diseasedetector.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diseasedetector.R;



public class AboutFragment extends Fragment {

    private AppCompatTextView txt_appname, txt_version, txt_developed, txt_teamname, txt_ack,
            txt_ack1, txt_ack2, txt_ack3,txt_ack4,txt_ack5, txt_aboutapp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_about, container, false);

        txt_appname = v.findViewById(R.id.txt_name);
        txt_version = v.findViewById(R.id.version);
        txt_developed = v.findViewById(R.id.developed);
        txt_teamname = v.findViewById(R.id.txt_team);
        txt_ack = v.findViewById(R.id.acknowledge);
        txt_ack1 = v.findViewById(R.id.ack_one);
        txt_ack2 = v.findViewById(R.id.ack_two);
        txt_ack3 = v.findViewById(R.id.ack_three);
        txt_ack4 = v.findViewById(R.id.ack_four);
        txt_ack5  = v.findViewById(R.id.ack_five);
        txt_aboutapp = v.findViewById(R.id.aboutapp);


        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenirnextregular.ttf");
        txt_appname.setTypeface(font);
        txt_version.setTypeface(font);
        txt_developed.setTypeface(font);
        txt_teamname.setTypeface(font);
        txt_ack.setTypeface(font);
        txt_ack1.setTypeface(font);
        txt_ack2.setTypeface(font);
        txt_ack3.setTypeface(font);
        txt_ack4.setTypeface(font);
        txt_ack5.setTypeface(font);

        txt_aboutapp.setTypeface(font);





        return  v;
    }


}
