package com.example.fp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class end extends AppCompatActivity {
    public TextView wintv;
    public Button retbtn,agabtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        wintv=findViewById(R.id.wintv);
        retbtn=findViewById(R.id.retbtn);
        agabtn=findViewById(R.id.agabtn);

        Bundle bundle=getIntent().getExtras().getBundle("win");
        int whonum=bundle.getInt("win");
        if(whonum==1){
            wintv.setText("恭喜 PlayerA 勝利 !\n PlayerB 請喝一杯");
        }
        else if(whonum==2){
            wintv.setText("恭喜 PlayerB 勝利 !\n PlayerA 請喝一杯");
        }
        else if(whonum==3){
            wintv.setText("       恭喜 PlayerA \n一擊斃殺了 PlayerB\n         請喝兩杯");
        }
        else if(whonum==4) {
            wintv.setText("       恭喜 PlayerB \n一擊斃殺了 PlayerA\n         請喝兩杯");
        }
        retbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent( end.this,
                        MainActivity.class),1);
            }
        });
        agabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent( end.this,
                        game.class),1);
            }
        });
    }
}