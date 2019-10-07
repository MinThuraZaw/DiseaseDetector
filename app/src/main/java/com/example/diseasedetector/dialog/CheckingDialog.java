package com.example.diseasedetector.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.diseasedetector.R;
import com.github.ybq.android.spinkit.SpinKitView;

public class CheckingDialog extends DialogFragment {

    private SpinKitView spinKitView;
    private AppCompatTextView tv_checking;

    public static CheckingDialog getInstance(int check){
        CheckingDialog checkingDialog = new CheckingDialog();

        Bundle bundle = new Bundle();
        bundle.putInt("checking", check);
        checkingDialog.setArguments(bundle);
        return checkingDialog;
    }

    public void cancel(){
        this.dismiss();
    }

    public void setCancelTrue(){
        this.setCancelable(true);
    }

    public void removeChecking(){

        spinKitView.setVisibility(View.GONE);
        tv_checking.setVisibility(View.GONE);

    }

    public void setMainText(String mainText){

        tv_checking.setText(mainText);
        tv_checking.setVisibility(View.VISIBLE);

    }


    @Override
    public void onResume() {

        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        int width = Utils.dpToPx(300);
        params.width = width;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.checking_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.custom_dialog));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinKitView = view.findViewById(R.id.spin_kit);
        tv_checking = view.findViewById(R.id.tv_checking);

        int check = getArguments().getInt("checking");

        if(check == 0) {

            spinKitView.setVisibility(View.VISIBLE);
            tv_checking.setVisibility(View.VISIBLE);

        }else if (check == 1){ //for no internet connection

            spinKitView.setVisibility(View.GONE);
            this.setCancelTrue();
            tv_checking.setText("No Internet Connection! Check your connection and restart the app!");


        } else if (check == 2){ //for news loading

            spinKitView.setVisibility(View.VISIBLE);
            tv_checking.setText("Loading news...");


        }



    }
}