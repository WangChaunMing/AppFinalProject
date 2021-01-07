package com.example.fp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class game extends AppCompatActivity {
    //宣告變數
    public Button red,black,even,odd,big,small,aa_openbtn,aa_closebtn,bb_openbtn,bb_closebtn,nextbtn,ret_btn,aga_btn;
    public TextView game_state,roundnum,adicenum_tv,bdicenum_tv;
    public ImageView adice1,adice2,adice3,adice4,adice5;
    public ImageView bdice1,bdice2,bdice3,bdice4,bdice5;
    public int aleft=0,bleft=0, winnum=0;
    public int roundstatus=1,ant,round=0;
    public int  adicenum=5,bdicenum=5;
    public int []adice={0,0,0,0,0};
    public int []bdice={0,0,0,0,0};
    public int []s={R.drawable.num1,R.drawable.num2,R.drawable.num3,
            R.drawable.num4,R.drawable.num5,R.drawable.num6,R.drawable.num};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //骰子
        odd = findViewById(R.id.odd);
        red = findViewById(R.id.red);
        black = findViewById(R.id.black);
        even = findViewById(R.id.even);
        big = findViewById(R.id.big);
        small = findViewById(R.id.small);
        //開關按鈕
        aa_openbtn = findViewById(R.id.aa_openbtn);
        bb_openbtn= findViewById(R.id.bb_openbtn);
        aa_closebtn = findViewById(R.id.aa_closebtn);
        bb_closebtn = findViewById(R.id.bb_closebtn);
        //Textview
        game_state = findViewById(R.id.game_state);
        roundnum = findViewById(R.id.roundnum);
        adicenum_tv = findViewById(R.id.adicenum_tv);
        bdicenum_tv = findViewById(R.id.bdicenum_tv);
        //骰子圖
        adice1 = findViewById(R.id.adice1);
        adice2 = findViewById(R.id.adice2);
        adice3 = findViewById(R.id.adice3);
        adice4 = findViewById(R.id.adice4);
        adice5 = findViewById(R.id.adice5);
        bdice1 = findViewById(R.id.bdice1);
        bdice2 = findViewById(R.id.bdice2);
        bdice3 = findViewById(R.id.bdice3);
        bdice4 = findViewById(R.id.bdice4);
        bdice5 = findViewById(R.id.bdice5);

        nextbtn = findViewById(R.id.nextbtn);
        ret_btn = findViewById(R.id.ret_btn);
        aga_btn= findViewById(R.id.aga_btn);

        //按下開始按鍵後
        roundstatus=1;//基數回合
        ret_btn.setEnabled(false);
        aga_btn.setEnabled(false);
        randomproduce();//亂數產生
        oddround();//第一回合提示+看牌
        closedicebtn();
        closelookbtn();
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomproduce();
                ret_btn.setEnabled(false);
                aga_btn.setEnabled(false);
                if(roundstatus==1){
                    oddround();
                }
                else if (roundstatus==2){
                    evenround();
                }
            }
        });
        ret_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent( game.this,
                        MainActivity.class),1);
            }
        });
        aga_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent( game.this,
                        game.class),1);
                finish();
            }
        });
    }
    public void oddround() {
        acover();//將a的骰子蓋住
        bcover();
        round = round + 1;//回合數
        roundnum.setText("A喊骰，回合:"+round);
        Toast.makeText(game.this, "第" + round + "回合：playerA喊骰子 ", Toast.LENGTH_LONG).show();
        bpart1.start();
        Toast.makeText(game.this, "44444 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "33333 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "22222 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "11111 ", Toast.LENGTH_SHORT).show();
        apart1.start();
        Toast.makeText(game.this, "44444 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "33333 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "22222 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "11111 ", Toast.LENGTH_SHORT).show();
        waittime.start();
    }//基數回合
    public void evenround() {
        acover();
        bcover();
        round = round + 1;//回合數
        roundnum.setText("B喊骰，回合:"+round);
        Toast.makeText(game.this, "第" + round + "回合：playerB喊骰子", Toast.LENGTH_LONG).show();
        apart2.start();
        Toast.makeText(game.this, "44444 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "33333 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "22222 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "11111 ", Toast.LENGTH_SHORT).show();
        bpart2.start();
        Toast.makeText(game.this, "44444 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "33333 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "22222 ", Toast.LENGTH_SHORT).show();
        Toast.makeText(game.this, "11111 ", Toast.LENGTH_SHORT).show();
        waittime.start();
    }//偶數回合
    public void aaadiceshow(){//更新骰子數和骰子的圖片
        acover();
        adicenum_tv.setText("骰子數:"+adicenum);
        for(int i=0;i<adicenum;i++) {
            if(i==0){
                adice1.setVisibility(View.VISIBLE);
                switch (adice[0]) {//翻牌
                    case 1: adice1.setImageResource(s[0]);break;
                    case 2: adice1.setImageResource(s[1]);break;
                    case 3: adice1.setImageResource(s[2]);break;
                    case 4: adice1.setImageResource(s[3]);break;
                    case 5: adice1.setImageResource(s[4]);break;
                    case 6: adice1.setImageResource(s[5]);break;
                }
            }
            else if(i==1){
                adice2.setVisibility(View.VISIBLE);
                switch (adice[1]) {//翻牌
                    case 1: adice2.setImageResource(s[0]);break;
                    case 2: adice2.setImageResource(s[1]);break;
                    case 3: adice2.setImageResource(s[2]);break;
                    case 4: adice2.setImageResource(s[3]);break;
                    case 5: adice2.setImageResource(s[4]);break;
                    case 6: adice2.setImageResource(s[5]);break;
                }

            }
            else if(i==2){
                adice3.setVisibility(View.VISIBLE);
                switch (adice[2]) {//翻牌
                    case 1: adice3.setImageResource(s[0]);break;
                    case 2: adice3.setImageResource(s[1]);break;
                    case 3: adice3.setImageResource(s[2]);break;
                    case 4: adice3.setImageResource(s[3]);break;
                    case 5: adice3.setImageResource(s[4]);break;
                    case 6: adice3.setImageResource(s[5]);break;
                }
            }
            else if(i==3){
                adice4.setVisibility(View.VISIBLE);
                switch (adice[3]) {//翻牌
                    case 1: adice4.setImageResource(s[0]);break;
                    case 2: adice4.setImageResource(s[1]);break;
                    case 3: adice4.setImageResource(s[2]);break;
                    case 4: adice4.setImageResource(s[3]);break;
                    case 5: adice4.setImageResource(s[4]);break;
                    case 6: adice4.setImageResource(s[5]);break;
                }
            }
            else if(i==4){
                adice5.setVisibility(View.VISIBLE);
                switch (adice[4]) {//翻牌
                    case 1: adice5.setImageResource(s[0]);break;
                    case 2: adice5.setImageResource(s[1]);break;
                    case 3: adice5.setImageResource(s[2]);break;
                    case 4: adice5.setImageResource(s[3]);break;
                    case 5: adice5.setImageResource(s[4]);break;
                    case 6: adice5.setImageResource(s[5]);break;
                }
            }
            else{}
        }
    } //更新玩家A的骰子
    public void bbbdiceshow(){
        bdicenum_tv.setText("骰子數:"+bdicenum);
        bcover();
        for(int i=0;i<bdicenum;i++){
            if(i==0){
                bdice1.setVisibility(View.VISIBLE);
                switch (bdice[0]) {//翻牌
                    case 1: bdice1.setImageResource(s[0]);break;
                    case 2: bdice1.setImageResource(s[1]);break;
                    case 3: bdice1.setImageResource(s[2]);break;
                    case 4: bdice1.setImageResource(s[3]);break;
                    case 5: bdice1.setImageResource(s[4]);break;
                    case 6: bdice1.setImageResource(s[5]);break;
                }
            }
            else if(i==1){
                bdice2.setVisibility(View.VISIBLE);
                switch (bdice[1]) {//翻牌
                    case 1: bdice2.setImageResource(s[0]);break;
                    case 2: bdice2.setImageResource(s[1]);break;
                    case 3: bdice2.setImageResource(s[2]);break;
                    case 4: bdice2.setImageResource(s[3]);break;
                    case 5: bdice2.setImageResource(s[4]);break;
                    case 6: bdice2.setImageResource(s[5]);break;
                }
            }
            else if(i==2){
                bdice3.setVisibility(View.VISIBLE);
                switch (bdice[2]) {//翻牌
                    case 1: bdice3.setImageResource(s[0]);break;
                    case 2: bdice3.setImageResource(s[1]);break;
                    case 3: bdice3.setImageResource(s[2]);break;
                    case 4: bdice3.setImageResource(s[3]);break;
                    case 5: bdice3.setImageResource(s[4]);break;
                    case 6: bdice3.setImageResource(s[5]);break;
                }
            }
            else if(i==3){
                bdice4.setVisibility(View.VISIBLE);
                switch (bdice[3]) {//翻牌
                    case 1: bdice4.setImageResource(s[0]);break;
                    case 2: bdice4.setImageResource(s[1]);break;
                    case 3: bdice4.setImageResource(s[2]);break;
                    case 4: bdice4.setImageResource(s[3]);break;
                    case 5: bdice4.setImageResource(s[4]);break;
                    case 6: bdice4.setImageResource(s[5]);break;
                }
            }
            else if(i==4){
                bdice5.setVisibility(View.VISIBLE);
                switch (bdice[4]) {//翻牌
                    case 1: bdice5.setImageResource(s[0]);break;
                    case 2: bdice5.setImageResource(s[1]);break;
                    case 3: bdice5.setImageResource(s[2]);break;
                    case 4: bdice5.setImageResource(s[3]);break;
                    case 5: bdice5.setImageResource(s[4]);break;
                    case 6: bdice5.setImageResource(s[5]);break;
                }
            }
            else{}
        }
    }//更新玩家B的骰子
    public void randomproduce(){
        for (int a = 0; a <adicenum; a++) {
            bdice[a] = (int) (Math.random() * 6) + 1;
        }
        for (int a = 0; a < bdicenum; a++) {
            adice[a] = (int) (Math.random() * 6) + 1;
        }
    }// 亂數產生
    public void winjudgment(){
        if((adicenum==0 && bdicenum==0 && roundstatus == 1)||(adicenum==0 && bdicenum!=0)){
            winnum=2;//B贏
            if(round==1){
                winnum=4;
            }
            roundstatus=1;round=0;
            adicenum=5;bdicenum=5;
            //傳輸輸贏
            Bundle bundle=new Bundle();
            bundle.putInt("win",winnum);
            Intent intent=new Intent(this,end.class);
            intent.putExtra("win",bundle);
            startActivity(intent);
        }//A輸，B贏
        else if((adicenum==0 && bdicenum==0 && roundstatus == 2)||(bdicenum==0 && adicenum!=0)){
            winnum=1;//A贏
            if(round==1){
                winnum=3;
            }
            roundstatus=1;round=0;
            adicenum=5;bdicenum=5;
            //傳輸輸贏
            Bundle bundle=new Bundle();
            Intent intent=new Intent(this,end.class);
            bundle.putInt("win",winnum);
            intent.putExtra("win",bundle);
            startActivity(intent);
        }
        else{
        }
    }//輸贏判斷
    //覆蓋
    public void bcover(){
        bdice1.setImageResource(s[6]);
        bdice2.setImageResource(s[6]);
        bdice3.setImageResource(s[6]);
        bdice4.setImageResource(s[6]);
        bdice5.setImageResource(s[6]);
    }
    public void acover(){
        adice1.setImageResource(s[6]);
        adice2.setImageResource(s[6]);
        adice3.setImageResource(s[6]);
        adice4.setImageResource(s[6]);
        adice5.setImageResource(s[6]);
    }
    public void closedicebtn(){
        odd.setEnabled(false);
        even.setEnabled(false);
        red.setEnabled(false);
        black.setEnabled(false);
        small.setEnabled(false);
        big.setEnabled(false);
    }
    public void closelookbtn(){
        aa_closebtn.setEnabled(false);
        aa_openbtn.setEnabled(false);
        bb_closebtn.setEnabled(false);
        bb_openbtn.setEnabled(false);
    }
    public void openbtn(){
        odd.setEnabled(true);
        even.setEnabled(true);
        red.setEnabled(true);
        black.setEnabled(true);
        small.setEnabled(true);
        big.setEnabled(true);
    }
    //按下看骰子和關骰子button會執行的程式
    public void bopendice(View view){
        bbbdiceshow();
    }
    public void bclosedice(View view){
        bcover();
    }
    public void aopendice(View view){
        aaadiceshow();
    }
    public void aclosedice(View view){
        acover();
    }
    //拿掉骰子按下去的時按做的事情
    public void odddice(View view){
        if(roundstatus==1) { game_state.setText("A選擇奇數拿掉"); }
        else { game_state.setText("B選擇奇數拿掉"); }
        for (int i = 0; i < adicenum; i++) {
            if (adice[i] == 1 || adice[i] == 3 || adice[i] == 5) {
            } else {
                adice[aleft] = adice[i];
                aleft = aleft + 1;
            }
        }
        for (int i = 0; i < bdicenum; i++) {
            if (bdice[i] == 1 || bdice[i] == 3 || bdice[i] == 5) {
            } else {
                bdice[bleft] = bdice[i];
                bleft = bleft + 1;
            }
        }
        adicenum = aleft;
        bdicenum = bleft;
        aleft=0;bleft=0;
        Toast.makeText(game.this, "A玩家剩餘骰子數"+adicenum+
                ",B玩家剩餘骰子數"+bdicenum, Toast.LENGTH_LONG).show();
        //更新剩下骰子數和骰子
        aaadiceshow();
        bbbdiceshow();
        winjudgment();
        if(roundstatus==1)
            roundstatus=2;
        else
            roundstatus=1;
        closedicebtn();
    }
    public void evendice(View view){
        if(roundstatus==1) {
            game_state.setText("A選擇偶數拿掉");
        }
        else {
            game_state.setText("B選擇偶數拿掉");
        }
        for (int i = 0; i < adicenum; i++) {
            if (adice[i] == 2 || adice[i] == 4 || adice[i] == 6) {
            } else {
                adice[aleft] = adice[i];
                aleft = aleft + 1;
            }
        }
        for (int i = 0; i < bdicenum; i++) {
            if (bdice[i] == 2 || bdice[i] == 4 || bdice[i] == 6) {
            } else {
                bdice[bleft] = bdice[i];
                bleft = bleft + 1;
            }
        }
        adicenum = aleft;
        bdicenum = bleft;
        aleft=0;bleft=0;
        //更新剩下骰子數和骰子
        aaadiceshow();
        bbbdiceshow();
        winjudgment();
        if(roundstatus==1)
            roundstatus=2;
        else
            roundstatus=1;
        closedicebtn();
    }
    public void smalldice(View view){
        if(roundstatus==1) {
            game_state.setText("A選擇小的拿掉");
        }
        else {
            game_state.setText("B選擇小的拿掉");
        }
        for (int i = 0; i < adicenum; i++) {
            if (adice[i] == 1 || adice[i] == 2 || adice[i] == 3) {
            } else {
                adice[aleft] = adice[i];
                aleft = aleft + 1;
            }
        }
        for (int i = 0; i < bdicenum; i++) {
            if (bdice[i] == 1 || bdice[i] == 2 || bdice[i] == 3) {
            } else {
                bdice[bleft] = bdice[i];
                bleft = bleft + 1;
            }
        }
        adicenum = aleft;
        bdicenum = bleft;
        aleft=0;bleft=0;
        //更新剩下骰子數和骰子
        aaadiceshow();
        bbbdiceshow();
        winjudgment();
        if(roundstatus==1)
            roundstatus=2;
        else
            roundstatus=1;
        closedicebtn();
    }
    public void bigdice(View view){
        if(roundstatus==1) {
            game_state.setText("A選擇大的拿掉");
        }
        else {
            game_state.setText("B選擇大的拿掉");
        }
        for (int i = 0; i < adicenum; i++) {
            if (adice[i] == 4 || adice[i] == 5 || adice[i] == 6) {
            } else {
                adice[aleft] = adice[i];
                aleft = aleft + 1;
            }
        }
        for (int i = 0; i < bdicenum; i++) {
            if (bdice[i] == 4 || bdice[i] == 5 || bdice[i] == 6) {
            } else {
                bdice[bleft] = bdice[i];
                bleft = bleft + 1;
            }
        }
        adicenum = aleft;
        bdicenum = bleft;
        aleft=0;bleft=0;
        //更新剩下骰子數和骰子
        aaadiceshow();
        bbbdiceshow();
        winjudgment();
        if(roundstatus==1)
            roundstatus=2;
        else
            roundstatus=1;
        closedicebtn();
    }
    public void reddice(View view){
        if(roundstatus==1) {
            game_state.setText("A選擇紅色拿掉");
        }
        else {
            game_state.setText("B選擇紅色拿掉");
        }
        for (int i = 0; i < adicenum; i++) {
            if (adice[i] == 1 || adice[i] == 4 ) {
            } else {
                adice[aleft] = adice[i];
                aleft = aleft + 1;
            }
        }
        for (int i = 0; i < bdicenum; i++) {
            if (bdice[i] == 1 || bdice[i] == 4 ) {
            } else {
                bdice[bleft] = bdice[i];
                bleft = bleft + 1;
            }
        }
        adicenum = aleft;
        bdicenum = bleft;
        aleft=0;bleft=0;
        //更新剩下骰子數和骰子
        aaadiceshow();
        bbbdiceshow();
        winjudgment();
        if(roundstatus==1)
            roundstatus=2;
        else
            roundstatus=1;
        closedicebtn();
    }
    public void blackdice(View view){
        if(roundstatus==1) {
            game_state.setText("A選擇黑色拿掉");
        }
        else {
            game_state.setText("B選擇黑色拿掉");
        }
        for (int i = 0; i < adicenum; i++) {
            if (adice[i] == 2 || adice[i] ==3  || adice[i] == 5 || adice[i] == 6) {
            } else {
                adice[aleft] = adice[i];
                aleft = aleft + 1;
            }
        }
        for (int i = 0; i < bdicenum; i++) {
            if (bdice[i] == 2 || bdice[i] ==3  || bdice[i] == 5 || bdice[i] == 6) {
            } else {
                bdice[bleft] = bdice[i];
                bleft = bleft + 1;
            }
        }
        adicenum = aleft;
        bdicenum = bleft;
        aleft=0;bleft=0;
        //更新剩下骰子數和骰子
        aaadiceshow();
        bbbdiceshow();
        winjudgment();
        if(roundstatus==1)
            roundstatus=2;
        else
            roundstatus=1;
        closedicebtn();
    }
    CountDownTimer bpart1 = new CountDownTimer(3000, 3000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }
        @Override
        public void onFinish() {
            if(bdicenum!=1) {
                closelookbtn();
                bb_openbtn.setEnabled(true);
                bb_closebtn.setEnabled(true);
                game_state.setText("B看牌");
            }
            else {
                closelookbtn();
                game_state.setText("B骰子剩1,故無法查看");
            }
        }
    };
    CountDownTimer apart1 = new CountDownTimer(12000, 12000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }
        @Override
        public void onFinish() {
            if(adicenum!=1) {
                closelookbtn();
                aa_openbtn.setEnabled(true);
                aa_closebtn.setEnabled(true);
                game_state.setText("換A看牌");
            }//判斷是否剩一顆骰子
            else{
                closelookbtn();
                game_state.setText("A骰子剩1,故無法查看");
            }
        }
    };
    CountDownTimer waittime = new CountDownTimer(20000, 20000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }
        @Override
        public void onFinish() {
            openbtn();
            ret_btn.setEnabled(true);
            aga_btn.setEnabled(true);
            if(roundstatus==1){
                game_state.setText("A看牌結束,A的選擇是");
            }
            else if(roundstatus==2){
                game_state.setText("B看牌結束,B的選擇是");
            }
        }
    };
    CountDownTimer bpart2 = new CountDownTimer(12000, 12000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }
        @Override
        public void onFinish() {
            if(bdicenum!=1) {
                closelookbtn();
                bb_openbtn.setEnabled(true);
                bb_closebtn.setEnabled(true);
                game_state.setText("換B看牌");
            }
            else {
                closelookbtn();
                game_state.setText("B骰子剩1,故無法查看");
            }
        }
    };
    CountDownTimer apart2 = new CountDownTimer(3000, 3000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }
        @Override
        public void onFinish() {
            if(adicenum!=1) {
                closelookbtn();
                aa_openbtn.setEnabled(true);
                aa_closebtn.setEnabled(true);
                game_state.setText("A看牌");
            }//判斷是否剩一顆骰子
            else{
                closelookbtn();
                game_state.setText("A骰子剩1,故無法查看");
            }
        }
    };
}