package com.example.project_last;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Join#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Join extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Join() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Join.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Join newInstance(String param1, String param2) {
        Fragment_Join fragment = new Fragment_Join();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ImageView intro, photo, cam;
    private final int GET_GALLERY_IMAGE = 200;
    AlphaAnimation AA;
    TextView textView_ani,genresult;
    EditText name, pwd, pwd_check;
    Spinner genre;
    String[] genre_array = {"선택해주세요.","가족/오락", "공포", "다큐멘터리", "드라마", "로맨스", "모험", "미스터리", "사극", "스릴러", "스포츠", "액션", "코미디", "판타지/SF"};
    Button join_btn;
    JoinDBHelper dbHelper;
    Uri selectedImageUri;
    ConstraintLayout join_click;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__join, container, false);
        intro = view.findViewById(R.id.intro);
        photo = view.findViewById(R.id.photo);
        cam = view.findViewById(R.id.cam);
        textView_ani = view.findViewById(R.id.textView5);
        dbHelper = new JoinDBHelper(getContext());
        join_click = view.findViewById(R.id.join_click);

        name = view.findViewById(R.id.edit_name);
        pwd = view.findViewById(R.id.edit_pwd);
        pwd_check = view.findViewById(R.id.edit_chpwd);
        genre = view.findViewById(R.id.spinner);
        genresult = view.findViewById(R.id.genresult);
        join_btn = view.findViewById(R.id.join_btn);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, genre_array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter);

        genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!(genresult.getText().toString().equals("선택 : "))&&i!=0){
                    genresult.append(", "+genre_array[i]);
                } else if(i!=0){
                    genresult.append(genre_array[i]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        AA = new AlphaAnimation(1, 0);
        AA.setDuration(3000);
        AA.setStartOffset(500);
        AA.setRepeatCount(Animation.INFINITE);
        AA.setRepeatMode(Animation.REVERSE);
        textView_ani.setAnimation(AA);

        Glide.with(this).load(R.drawable.intro).into(intro);
        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intro.setVisibility(View.GONE);
                //textView_ani.setVisibility(View.GONE);
                join_click.setVisibility(View.GONE);
                AA.reset();
            }
        });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().equals("")&&!pwd.getText().toString().equals("")&&!pwd_check.getText().toString().equals("")&&!genresult.getText().toString().equals("선택 : ")){
                    if(pwd.getText().toString().equals(pwd_check.getText().toString())){
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        String query = "Insert into joinTB(name, likeGenre, pwd, imgURL_pr) values ('" + name.getText().toString() + "', '" + genresult.getText().toString().replace("선택 : ","") + "', '" + pwd.getText().toString() + "', '" + String.valueOf(selectedImageUri) + "')";
                        db.execSQL(query);
                        db.close();
                        //디비저장
                        Fragment_Login fragment_login = Fragment_Login.newInstance();
                        ((Intro) getActivity()).replaceFragment(fragment_login);
                    } else {
                        Toast.makeText(view.getContext(),"비밀번호를 똑같이 입력해주세요.",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "모든 항목을 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            Glide.with(this).load(selectedImageUri).override(100,100).circleCrop().into(photo);
            Log.d("uri가 어케 뜨냐연", selectedImageUri+"");
            //photo.setImageURI(selectedImageUri);
        }

    }

}