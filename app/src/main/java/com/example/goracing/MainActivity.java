package com.example.goracing;

import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public boolean Started = false;
    public boolean Finished = false;

    public void Start(View view){
        Button button = (Button)findViewById(R.id.btnStart);
        if(!Finished){
            if(!Started){
                button.setBackgroundColor(Color.RED); //red
                button.setText("Пауза");
                Started = true;
            }
            else if(Started){
                button.setBackgroundColor(Color.GREEN); //green
                button.setText("Старт");
                Started = false;

            }
        }
        else{
            Intent intent=new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }



    public void Drive2(View view) {
        Button button = (Button)findViewById(R.id.btnStart);
        View Car = (View)findViewById(R.id.Car2);
        View CarBot = (View)findViewById(R.id.Car1);
        TextView result = (TextView)findViewById(R.id.tvResult);
        if(Started && !Finished){
            ViewGroup.MarginLayoutParams margin = (ViewGroup.MarginLayoutParams)Car.getLayoutParams();
            ViewGroup.MarginLayoutParams marginBot = (ViewGroup.MarginLayoutParams)CarBot.getLayoutParams();
            margin.leftMargin += 40;
            margin.rightMargin -= 40;
            int randomNum = ThreadLocalRandom.current().nextInt(0, 70);
            marginBot.leftMargin += randomNum;
            marginBot.rightMargin -= randomNum;
            Car.requestLayout();
            CarBot.requestLayout();
            if(margin.rightMargin <= -550){
                result.setText("Победа Игрока");
                button.setText("Заново");
                result.setTextColor(Color.GREEN);
                Finished = true;
            }
            if(marginBot.rightMargin <= -550){
                result.setText("Победа Бота");
                button.setText("Заново");
                result.setTextColor(Color.RED);
                Finished = true;
            }
        }
    }
}