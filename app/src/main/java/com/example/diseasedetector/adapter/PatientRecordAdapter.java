package com.example.diseasedetector.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diseasedetector.R;
import com.example.diseasedetector.activities.PatientRecordDetailActivity;
import com.example.diseasedetector.model.Data;
import com.example.diseasedetector.model.PatientRecord;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class PatientRecordAdapter extends RecyclerView.Adapter<PatientRecordAdapter.ViewHolder>{


    private List<Data> patientData = null;
    private PatientRecord records;
    private Context context;
    String date;
    private String value_name, value_age, value_gender, value_weight, value_height, value_blood, value_happen, value_date;

    public PatientRecordAdapter(Context mcontext, List<Data> mdata ){
        this.context = mcontext;
        this.patientData = mdata;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_record_list, parent,
                false);
        return new PatientRecordAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        date = patientData.get(position).getCreated_at().substring(0,10);
        //Log.d("json",""+date);


        holder.tv_type.setText(patientData.get(position).getHappened());
        holder.tv_name.setText(patientData.get(position).getName());
        holder.tv_date.setText(date);


        holder.patient_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                value_name = patientData.get(position).getName();
                value_age = patientData.get(position).getAge();
                value_gender = patientData.get(position).getGender();
                value_weight = patientData.get(position).getWeight();
                value_height = patientData.get(position).getHeight();
                value_blood = patientData.get(position).getBloodtype();
                value_happen = patientData.get(position).getHappened();
                value_date = patientData.get(position).getCreated_at().substring(0,10);



                Intent intent = new Intent(context, PatientRecordDetailActivity.class);
                intent.putExtra("pname", ""+value_name);
                intent.putExtra("page", ""+value_age);
                intent.putExtra("pgender", value_gender);
                intent.putExtra("pweight", ""+value_weight);
                intent.putExtra("pheight", ""+value_height);
                intent.putExtra("pblood", ""+value_blood);
                intent.putExtra("phappen", ""+value_happen);
                intent.putExtra("pdate", ""+value_date);

                view.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return patientData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tv_name, tv_type, tv_date, txt_name, txt_type, txt_date;
        MaterialCardView patient_card;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            patient_card = itemView.findViewById(R.id.patient_cardview);


            tv_type = itemView.findViewById(R.id.tv_patient_type);
            tv_name = itemView.findViewById(R.id.tv_patient_name);
            tv_date = itemView.findViewById(R.id.tv_patient_date);


            txt_type = itemView.findViewById(R.id.txt_patient_type);
            txt_date = itemView.findViewById(R.id.txt_patient_datetime);
            txt_name = itemView.findViewById(R.id.txt_patient_name);


            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/avenirnextregular.ttf");
            tv_type.setTypeface(font);
            tv_date.setTypeface(font);
            tv_name.setTypeface(font);

            txt_type.setTypeface(font);
            txt_name.setTypeface(font);
            txt_date.setTypeface(font);



        }

    }
}
