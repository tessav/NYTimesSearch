package com.example.tessav.nytimessearch.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.tessav.nytimessearch.R;


/**
 * Created by tessavoon on 9/19/17.
 */

public class FilterFragment extends DialogFragment {
    SharedPreferences filterSharedPreferences;
    SharedPreferences.Editor editor;

    public FilterFragment() {

    }

    public static FilterFragment newInstance(String title) {
        FilterFragment frag = new FilterFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container);
        Button btn =(Button) view.findViewById(R.id.btnSave);
        filterSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = filterSharedPreferences.edit();

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Spinner orderSpinner = (Spinner) getView().findViewById(R.id.sSortOrder);
                DatePicker beginDate = (DatePicker) getView().findViewById(R.id.dpBeginDate);
                CheckBox cbSports = (CheckBox) getView().findViewById(R.id.cbSports);
                CheckBox cbArts = (CheckBox) getView().findViewById(R.id.cbArts);
                CheckBox cbFashion = (CheckBox) getView().findViewById(R.id.cbFashion);
                String order = orderSpinner.getSelectedItem().toString();
                int beginDay = beginDate.getDayOfMonth();
                int beginMonth = beginDate.getMonth()+1;
                int beginYear = beginDate.getYear();
                editor.putInt("beginYear", beginYear);
                editor.putInt("beginMonth", beginMonth);
                editor.putInt("beginDay", beginDay);
                editor.putBoolean("cbSports", cbSports.isChecked());
                editor.putBoolean("cbArts", cbArts.isChecked());
                editor.putBoolean("cbFashion", cbFashion.isChecked());
                editor.apply();
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
