package com.example.diseasedetector.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.diseasedetector.R;
import com.example.diseasedetector.adapter.CheckupRecordAdapter;
import com.example.diseasedetector.adapter.NewsAdapter;
import com.example.diseasedetector.database.AppDatabase;
import com.example.diseasedetector.database.CheckupRecords;

import java.util.List;


public class CheckupRecordFragment extends Fragment {

    List<CheckupRecords> data = null;
    private AppCompatTextView tv_nohistory, tv_gg;
    private AppCompatImageView img_nohistory;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private CheckupRecordAdapter adapter;
    private List<CheckupRecords> recordsdata = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_checkup_record, container, false);

        tv_nohistory = view.findViewById(R.id.txt_nohistory);
        img_nohistory = view.findViewById(R.id.img_nohistory);
        tv_gg = view.findViewById(R.id.gg);

        recyclerView = view.findViewById(R.id.record_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);


        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenirnextregular.ttf");
        tv_nohistory.setTypeface(font);

        AppDatabase db = Room.databaseBuilder(getActivity().getBaseContext(), AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();


        if(db.histroyDao().dataCount() > 0){
            //Toast.makeText(getActivity().getBaseContext(), "there are Data", Toast.LENGTH_SHORT).show();
            tv_nohistory.setVisibility(View.INVISIBLE);
            img_nohistory.setVisibility(View.INVISIBLE);
        }

        data = db.histroyDao().getAll();
        adapter = new CheckupRecordAdapter(getActivity().getBaseContext(), data);
        recyclerView.setAdapter(adapter);


        //adapter.onBindViewHolder(new );

//            for(int i=0; i<data.size(); i++){
//
//                tv_gg.append("" +data.get(i).getAttribute()+"\n");
//
//            }
        return view;


    }

}
