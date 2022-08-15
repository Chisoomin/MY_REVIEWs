package com.example.project_last;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;

public class ContentsReview extends AppCompatActivity {
    ImageView movie_img;
    TextView movie_title;
    EditText review_title, review_content;
    TextView date_tv, people_tv;
    ImageView date_btn, people_btn;
    RatingBar ratingBar;
    Button enter;
    Integer Year, Month, Day;
    Calendar calendar;
    String mon, day;
    View with_dialog;
    ReviewDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentsreview);
        setTitle("평론 작성");

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        movie_img = findViewById(R.id.movie_img);
        movie_title = findViewById(R.id.movie_title);
        review_title = findViewById(R.id.review_title);
        review_content = findViewById(R.id.review_content);
        date_tv = findViewById(R.id.date_tv);
        people_tv = findViewById(R.id.people_tv);
        date_btn = findViewById(R.id.date_btn);
        people_btn = findViewById(R.id.people_btn);
        ratingBar = findViewById(R.id.ratingBar);
        enter = findViewById(R.id.enter_btn);
        dbHelper = new ReviewDBHelper(getApplicationContext());
        with_dialog = getLayoutInflater().inflate(R.layout.with_dialog, null);

        String title = getIntent().getStringExtra("title");
        String imgurl = getIntent().getStringExtra("imgurl");

        movie_title.setText("제목 : " + title);
        Glide.with(getApplicationContext()).load(imgurl.trim()).error(R.drawable.gray).into(movie_img);

        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ContentsReview.this, mDateSetListener, Year, Month, Day).show();

            }
        });

        people_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(with_dialog);
                EditText with = with_dialog.findViewById(R.id.with);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        people_tv.setText(with.getText().toString());
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                builder.show();


            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Log.d("뭐라고 뜰까 rating", ratingBar.getRating()+"");
                String rate = String.valueOf(ratingBar.getRating());
                String query = "Insert into ReviewTB(title_MR, imgurl_R, title_R, content_R, date, with_p, rating) values ('" + title + "', '" + imgurl + "', '" + review_title.getText().toString() + "', '" + review_content.getText().toString() + "', '" + date_tv.getText().toString() + "', '" + people_tv.getText().toString() + "', '"+ rate +"')";
                db.execSQL(query);
                db.close();
                finish();
            }
        });
    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Year = year;
            Month = monthOfYear;
            Day = dayOfMonth;

            calendar.set(Calendar.YEAR, Year);
            calendar.set(Calendar.MONTH, Month);
            calendar.set(Calendar.DATE, Day);

            if (Integer.toString(Month + 1).length() < 2)
                mon = "0" + (Month + 1);
            else
                mon = "" + (Month + 1);
            if (Integer.toString(Day).length() < 2)
                day = "0" + Day;
            else
                day = "" + Day;
            date_tv.setText(String.format("%d년 %s월 %s일", Year, mon, day));
        }
    };
}