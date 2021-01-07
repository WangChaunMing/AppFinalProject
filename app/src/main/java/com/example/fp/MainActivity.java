package com.example.fp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button rule ,start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rule = findViewById(R.id.rr);
        start = findViewById(R.id.start);//規則畫面xml的start button
        rule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("遊戲規則");
                dialog.setMessage("起始玩家看完自己的牌以後可以選擇以下六種喊法 :\n" +
                        "(1) 紅的拿掉(1、4)\n" +
                        "(2) 黑的拿掉(2、3、5、6)\n" +
                        "(3) 大的拿掉 (4、5、6)\n" +
                        "(4) 小的拿掉 (1、2、3)\n" +
                        "(5) 單數拿掉 (1、3、5)\n" +
                        "(6) 雙數拿掉 (2、4、6)\n" +
                        "起始玩家擇一喊完後，請另一玩家依照該指示拿掉該骰子，包含喊者本人。\n" +
                        "此動作完成後，請兩位玩家將自己剩餘的骰子放入骰盅再次骰動。\n" +
                        "接下來攻守交換，重複同樣動作，直到有玩家骰子被拿光則為輸家。\n" +
                        "若開局第一次喊牌時有玩家直接拿光骰子，為一擊必殺，懲罰兩倍。\n" +
                        "若同時沒骰子，則喊方為輸家。");

                dialog.setNeutralButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });dialog.show();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent( MainActivity.this,
                       game.class),1);
                finish();
            }
        });
    }
}