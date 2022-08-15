package com.example.project_last;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Explain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Explain extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Explain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Explain.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Explain newInstance(String param1, String param2) {
        Fragment_Explain fragment = new Fragment_Explain();
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
    ImageView search, bookmark, review, mypage;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__explain, container, false);
        search = view.findViewById(R.id.expliain_search);
        bookmark = view.findViewById(R.id.expliain_bookmark);
        review = view.findViewById(R.id.expliain_review);
        mypage = view.findViewById(R.id.explain_mypage);

        dialog = new Dialog( getContext() );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.dialog_explain );

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //View dialogView = getLayoutInflater().inflate(R.layout.dialog_explain, null);

                TextView title = dialog.findViewById(R.id.ex_title);
                TextView content = dialog.findViewById(R.id.ex_content);
                Button button = dialog.findViewById(R.id.ex_btn);

                title.setText("'SEARCH ; 영화 검색'");
                content.setText("검색창에 영화 제목을 입력하고 버튼을 클릭하여 검색해 주세요.\n영화를 찾아 클릭하면 PICK! 버튼을 통해 즐겨찾기 추가가 가능합니다.\n\n자신만의 영화 리스트를 만들어 보세요!");
                dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //View dialogView = getLayoutInflater().inflate(R.layout.dialog_explain, null);

                TextView title = dialog.findViewById(R.id.ex_title);
                TextView content = dialog.findViewById(R.id.ex_content);
                Button button = dialog.findViewById(R.id.ex_btn);

                title.setText("'BOOKMARK ; 나의 즐겨찾기 목록'");
                content.setText("PICK!한 영화들을 한눈에 볼 수 있는 페이지 입니다.\n인생작 영화들을 한눈에 볼 수 있습니다.\n\n그때의 감상을 기록하러 가볼까요?");
                dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //View dialogView = getLayoutInflater().inflate(R.layout.dialog_explain, null);

                TextView title = dialog.findViewById(R.id.ex_title);
                TextView content = dialog.findViewById(R.id.ex_content);
                Button button = dialog.findViewById(R.id.ex_btn);

                title.setText("'REVIEW ; 영화 평론'");
                content.setText("자신이 쓴 평론을 한눈에 볼 수 있습니다.\n+ 버튼을 클릭하고 평론하길 원하는 작품을 찾아주세요.\n작품 선택 후, 평론 작성을 누르면 평론 작성 페이지로 넘어갑니다.\n\n평론의 제목, 내용, 관람일 ,함께 본 사람, 별점을 입력하여 평론을 작성해주세요.\n평론을 등록한 뒤, 새로고침 버튼을 누르면 새로 쓴 평론이 보여집니다.\n\n평론을 써 그때의 감상을 기록해보아요!");
                dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //View dialogView = getLayoutInflater().inflate(R.layout.dialog_explain, null);

                TextView title = dialog.findViewById(R.id.ex_title);
                TextView content = dialog.findViewById(R.id.ex_content);
                Button button = dialog.findViewById(R.id.ex_btn);

                title.setText("'MYPAGE ; 마이페이지'");
                content.setText("회원가입 때 등록했던 정보들이 보여지게 됩니다. 또한 즐겨찾기와 평론의 개수를 한눈에 확인할 수 있습니다. \n\n추천 영화도 감상해보세요!");
                dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        return  view;
    }
}