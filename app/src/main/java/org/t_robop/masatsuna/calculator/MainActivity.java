package org.t_robop.masatsuna.calculator;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    String dp = "";         //画面に表示する数字

    TextView mTextView;     //TextView型のmTextView

    int memo;               //四則演算のボタンが押された時、そのときの画面の値を記憶

    String ope;             //clickEqualを実行する時、四則演算を判別するために使用

    int total;              //clickEqualで四則演算をした値

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* ０〜９または小数点が押された時、それぞれの数字を引数にdisplay()を実行*/
    public void click0(View view) {
        display("0");
    }

    public void click1(View view) {
        display("1");
    }

    public void click2(View view) {
        display("2");
    }

    public void click3(View view) {
        display("3");
    }

    public void click4(View view) {
        display("4");
    }

    public void click5(View view) {
        display("5");
    }

    public void click6(View view) {
        display("6");
    }

    public void click7(View view) {
        display("7");
    }

    public void click8(View view) {
        display("8");
    }

    public void click9(View view) {
        display("9");
    }

    public void clickTen(View view) {
        display(".");
    }

    //各数字を文字として引数にして画面に引数を追加表示
    public void display(String num){

        //mTextViewとdisplayのidを関連付け
        mTextView = (TextView) findViewById(R.id.display);

        //すでに画面に表示されてる値（dp）に、引数の値を追加
        dp += num;

        //dpを画面（mTextView）に表示
        mTextView.setText(String.valueOf(dp));
    }


    /*四則演算の処理
     各々の記号を引数にしてopeに記憶させた後、画面の値をmemoに記憶
     最後に次数字が入力されたとき画面がリセットされるようにdpを空にする
     */

    public void clickPlus(View view){
        operation("+");
    }

    public void clickMinus(View view){
       operation("-");
    }

    public void clickMultiply(View view){
       operation("×");
    }

    public void clickDivide(View view){
        operation("÷");
    }

    public void operation(String mark){

        //opeに四則演算の記号を記憶
        ope = mark;

        //mTextViewの値をnowValueにgetTextして、その値をvalueにint型に変換
        mTextView = (TextView) findViewById(R.id.display);
        String nowValue = mTextView.getText().toString();
        int value = Integer.parseInt(nowValue);

        //memoに画面の値を記憶
        memo = value;

        //次、数字が入力されたときに現在表記されている数字を消すために、dpを空にする
        dp = "";
    }

    /* " = "が押された時の実行内容*/
    public void clickEqual(View view){

        //mTextViewをdisplayと関連付け
        mTextView = (TextView) findViewById(R.id.display);

        //四則演算のボタンを押された後の数字をvalueに記憶
        String nowValue = mTextView.getText().toString();
        int value = Integer.parseInt(nowValue);

        /* opeの内容によって計算を分ける
           memoは四則演算のボタンを押される前の値、valueは四則演算のボタンを押される後の値
         */
        switch (ope) {
            case "+":
                total = memo + value;
                break;
            case "-":
                total = memo - value;
                break;
            case "×":
                total = memo * value;
                break;
            case "÷":
                total = memo / value;
                break;
        }

        //計算結果を画面に表示
        mTextView.setText(String.valueOf(total));

        /*次、数字ボタンを押したとき計算する前のdpの値に追加されないように（" = "を押して計算結果は表示されても、
        dpには前の画面の数字が記憶されたままになっている）dpを初期化する */
        dp = "";
    }

    //Clearを押したときの処理
    public void clickClear(View view){

        TextView input = (TextView) findViewById(R.id.display);

        //dpを空にして画面に表示
        dp = "";
        input.setText(String.valueOf(dp));
    }

}