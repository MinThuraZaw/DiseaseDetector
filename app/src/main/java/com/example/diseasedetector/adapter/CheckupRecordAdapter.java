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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.diseasedetector.R;
import com.example.diseasedetector.activities.CheckUpDetailActivity;
import com.example.diseasedetector.database.CheckupRecords;
import com.google.android.material.card.MaterialCardView;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CheckupRecordAdapter extends RecyclerView.Adapter<CheckupRecordAdapter.ViewHolder>{


    private List<CheckupRecords> recordsdata = null;
    private CheckupRecords records;
    private Context context;
    private boolean expand = false;
    private String  type, result, date, attributes;

    public CheckupRecordAdapter(Context mcontext, List<CheckupRecords> mrecord ){
        this.context = mcontext;
        this.recordsdata = mrecord;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkup_record_list, parent,
                false);
        return new CheckupRecordAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.tv_type.setText(recordsdata.get(position).getDtype());
        holder.tv_result.setText(recordsdata.get(position).getResult());
        holder.tv_date.setText(recordsdata.get(position).getDatetime());
       // holder.tv_result.setText(recordsdata.get(position).getResult());


        holder.record_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type = recordsdata.get(position).getDtype();
                result = recordsdata.get(position).getResult();
                attributes = recordsdata.get(position).getAttribute();
                date = recordsdata.get(position).getDatetime();

                Intent intent = new Intent(context, CheckUpDetailActivity.class);
                intent.putExtra("type", ""+type);
                intent.putExtra("result", ""+result);
                intent.putExtra("date", date);
                intent.putExtra("attr", ""+attributes);

                view.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return recordsdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tv_attribute, tv_type, tv_result, txt_type, txt_result, txt_attr, txt_datetime, tv_date;
        MaterialCardView record_card;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            record_card = itemView.findViewById(R.id.record_cardview);

            tv_attribute = itemView.findViewById(R.id.tv_attribute);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_result = itemView.findViewById(R.id.tv_result);
            tv_date = itemView.findViewById(R.id.tv_date);


            txt_attr = itemView.findViewById(R.id.txt_attr);
            txt_type = itemView.findViewById(R.id.txt_type);
            txt_result = itemView.findViewById(R.id.txt_result);
            txt_datetime = itemView.findViewById(R.id.txt_datetime);



            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/avenirnextregular.ttf");
            tv_type.setTypeface(font);
            tv_result.setTypeface(font);
            tv_attribute.setTypeface(font);
            tv_date.setTypeface(font);

            txt_result.setTypeface(font);
            txt_type.setTypeface(font);
            txt_attr.setTypeface(font);
            txt_datetime.setTypeface(font);



        }

    }
}
