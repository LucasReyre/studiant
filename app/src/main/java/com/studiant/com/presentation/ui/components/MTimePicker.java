package com.studiant.com.presentation.ui.components;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by lucas on 04/05/2017.
 */

public class MTimePicker implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    TextView _editText;
    private int _minute;
    private int _hour;
    private Context _context;

    public MTimePicker(Context context, int editTextViewID)
    {
        Activity act = (Activity)context;
        this._editText = (TextView)act.findViewById(editTextViewID);
        this._editText.setOnClickListener(this);
        this._context = context;
    }
/*
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        _birthYear = year;
        _month = monthOfYear;
        _day = dayOfMonth;
        updateDisplay();
    }
    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(_context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }*/

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        _hour = hour;
        _minute = minute;
        updateDisplay();
    }

    @Override
    public void onClick(View view) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker = new TimePickerDialog(_context, this, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    // updates the date in the birth date EditText
    private void updateDisplay() {

        _editText.setText(new StringBuilder()
                .append(_hour).append("h").append(_minute).append(" "));
    }


}