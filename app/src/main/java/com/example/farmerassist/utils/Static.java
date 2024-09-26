package com.example.farmerassist.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.farmerassist.FileUtils;
import com.example.farmerassist.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Static {
    public static void setGlide(FragmentActivity activity, String url, ImageView imageView) {
        Glide.with(activity).load(Constants.BASE_URL+"/"+url).placeholder(R.drawable.back_arrow).error(R.drawable.back_arrow).into(imageView);
    }
    public static RequestBody getRequestBody(String text) {
        return RequestBody.create(MediaType.parse("text/plain"), text);
    }
    public static MultipartBody.Part getImagePart(Context context, Uri uri) {
        File file = new File(FileUtils.getPath(context, uri));
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData("image", file.getName(), reqFile);
    }
    public static void openLink(Context context, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

   public static void showDatePicker(Context context, EditText text) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(selectedYear, selectedMonth, selectedDay);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    text.setText(dateFormat.format(selectedDate.getTime()));
                },
                year, month, day
        );
        datePickerDialog.show();
    }
    public static void showTimePicker(Context context, EditText text) {
        final Calendar c = Calendar.getInstance();

        // on below line we are getting our hour, minute.
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // on below line we are initializing our Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // on below line we are setting selected time
                        // in our text view.
                        text.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
        // at last we are calling show to
        // display our time picker dialog.
        timePickerDialog.show();
    }

}
