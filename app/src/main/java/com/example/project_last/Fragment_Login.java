package com.example.project_last;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Login newInstance(String param1, String param2) {
        Fragment_Login fragment = new Fragment_Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment_Login newInstance() {
        return new Fragment_Login();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ImageView intro;
    AlphaAnimation AA;
    TextView ani;
    View login_dialog;
    String pwd_str;
    ConstraintLayout login_click;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__login, container, false);
        intro = view.findViewById(R.id.imageView3);
        ani = view.findViewById(R.id.textView00);
        login_click = view.findViewById(R.id.login_click);
        login_dialog = getLayoutInflater().inflate(R.layout.dialog_pwd, null);

        AA = new AlphaAnimation(1, 0);
        AA.setDuration(3000);
        AA.setStartOffset(500);
        AA.setRepeatCount(Animation.INFINITE);
        AA.setRepeatMode(Animation.REVERSE);
        ani.setAnimation(AA);

        Glide.with(this).load(R.drawable.intro).into(intro);
        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intro.setVisibility(View.GONE);
                //ani.setVisibility(View.GONE);
                login_click.setVisibility(View.GONE);
                AA.reset();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(login_dialog);
                EditText pwd = login_dialog.findViewById(R.id.pwd);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        JoinDBHelper dbHelper = new JoinDBHelper(view.getContext());
                        SQLiteDatabase dbR = dbHelper.getReadableDatabase();
                        Cursor cursor = dbR.rawQuery("select pwd from joinTB;", null);
                        while (cursor.moveToNext()) {
                            pwd_str = cursor.getString(0);
                        }
                        if(pwd_str.equals(pwd.getText().toString())){
                            Intent intent = new Intent(view.getContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(),"비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(view.getContext(), Intro.class);
                            startActivity(intent);
                        }
                    }
                });
                builder.show();
            }
        });

        return view;
    }
}