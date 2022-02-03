package com.example.share_wise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingScreen extends AppCompatActivity {
    private ViewPager screenpager;
    introViewPageter introViewPageter;
    TabLayout tab_indicator;
    Button btn_next;
    int position=0;
    Button btn_get_Started;
    TextView btn_skip;
    Animation btnanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
        btn_next=findViewById(R.id.btn_next);
        tab_indicator=findViewById(R.id.tab_indicator);
        btn_get_Started=findViewById(R.id.btn_get_Started);
        btnanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        btn_skip=findViewById(R.id.btn_skip);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (restorePrefData()){
            Intent mainActivity=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
        List<screen_item> mList=new ArrayList<>();
        mList.add(new screen_item("Efficient Data Sharing","Saves a lot of time to transfer data",R.drawable.group_o));
        mList.add(new screen_item("Fast & Secure","Transfer data on high speed",R.drawable.group_t   ));


        screenpager=findViewById(R.id.scren_viewpager);
        introViewPageter=new introViewPageter(this,mList);
        screenpager.setAdapter(introViewPageter);
        tab_indicator.setupWithViewPager(screenpager);

        btn_skip.setOnClickListener(view -> {
            Intent intent=new Intent(OnBoardingScreen.this,MainActivity.class);
            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            if(screenpager.getCurrentItem() == mList.size()-1){

                btn_get_Started.setVisibility(View.VISIBLE);
                btn_next.setVisibility(View.INVISIBLE);
                btn_skip.setVisibility(View.INVISIBLE);
                tab_indicator.setVisibility(View.INVISIBLE);

            } else {
                position=screenpager.getCurrentItem();
                if (position<mList.size()){
                    position++;
                    screenpager.setCurrentItem(position);
                }
                if (position == mList.size()){
                    loaddLastscreen();
                }
            }


        });

        tab_indicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==mList.size()-1){

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btn_get_Started.setOnClickListener(view -> {
            Intent intent=new Intent(OnBoardingScreen.this,MainActivity.class);
            intent.putExtra("edit","0");
            startActivity(intent);
            savePrefsData();
            finish();
        });

    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext ().getSharedPreferences ( "myPrefs", MODE_PRIVATE) ;
        Boolean isIntroActivityopnendBefore = pref.getBoolean ("iSIntroOpnend", false);
        return isIntroActivityopnendBefore;

    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext ().getSharedPreferences ( "myPrefs", MODE_PRIVATE) ;
        SharedPreferences.Editor editor = pref.edit ();
        editor.putBoolean ("isIntroOpnend", true);
        editor.commit();

    }
    private void loaddLastscreen() {
        btn_next.setVisibility(View.INVISIBLE);
        btn_get_Started.setVisibility(View.VISIBLE);
        tab_indicator.setVisibility(View.INVISIBLE);
        btn_get_Started.setAnimation(btnanim);




    }
}