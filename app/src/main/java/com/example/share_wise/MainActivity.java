package com.example.share_wise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView play_btn,setting;
    LinearLayout application;
    TextView start_share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_btn=findViewById(R.id.play_btn);
        application=findViewById(R.id.application);
        start_share=findViewById(R.id.start_share);
        setting=findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initializing the popup menu and giving the reference as current context
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, setting);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.menu_first, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked

                        switch (menuItem.getItemId()) {
                            case R.id.rate_us:

                                Intent rateee=new Intent(MainActivity.this,Rate_us.class);
                                startActivity(rateee);
                                return true;
                            case R.id.Privacy:
                                Intent intent=new Intent(MainActivity.this,Privacy_policy.class);
                                startActivity(intent);

                                return true;
                            case R.id.share:
                                Intent shareintent = new Intent();
                                shareintent.setAction(Intent.ACTION_SEND);
                                shareintent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.devapps.webshotsaver");
                                shareintent.setType("Text/Plain");
                                startActivity(Intent.createChooser(shareintent, "share via"));
                                Toast.makeText(MainActivity.this, "share clicked", Toast.LENGTH_SHORT).show();

                                return true;

                            default:
                                return false;
                        }

                    }

                });


                // Showing the popup menu
                popupMenu.show();
            }

        });
      start_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.activity_qr, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "---" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "---");
                startActivity(Intent.createChooser(intent, "Contact Us!"));
            }
        });

    }
}