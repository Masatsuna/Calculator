package org.t_robop.masatsuna.calculator;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {



    String dp = "0";         //画面に表示する数字

    TextView mTextView;     //TextView型のmTextView

    double memo;            //四則演算のボタンが押された時、そのときの画面の値を記憶

    String ope = "";        //clickEqualを実行する時、四則演算を判別するために使用

    double total;           //clickEqualで四則演算をした値

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /*ボタンを識別して、それぞれのメソッドを実行*/
    public  void onClick(View view) {

        switch (view.getId()) {

            case R.id.button0:
                display("0");
                break;

            case R.id.button1:
                display("1");
                break;

            case R.id.button2:
                display("2");
                break;

            case R.id.button3:
                display("3");
                break;

            case R.id.button4:
                display("4");
                break;

            case R.id.button5:
                display("5");
                break;

            case R.id.button6:
                display("6");
                break;

            case R.id.button7:
                display("7");
                break;

            case R.id.button8:
                display("8");
                break;

            case R.id.button9:
                display("9");
                break;

            case R.id.buttonTen:
                display(".");
                break;

            case R.id.buttonPlus:
                operation("+");
                break;

            case R.id.buttonMinus:
                operation("-");
                break;

            case R.id.buttonMultiply:
                operation("*");
                break;

            case R.id.buttonDivide:
                operation("/");
                break;

            case R.id.buttonEqual:

                calculation();

        /*次、数字ボタンを押したとき計算する前のdpの値に追加されないように（" = "を押して計算結果は表示されても、
        dpには前の画面の数字が記憶されたままになっている）dpを初期化する */
                dp = "";

                ope = "";

                break;

            case R.id.buttonClear:

                mTextView = (TextView) findViewById(R.id.display);

                //dpを０にして画面に表示
                dp = "0";
                mTextView.setText(String.valueOf(dp));

                ope = "";

                break;
        }
    }

    //各数字を文字として引数にして画面に引数を追加表示
    public void display(String num) {

        //mTextViewとdisplayのidを関連付け
        mTextView = (TextView) findViewById(R.id.display);



        if (mTextView.length() < 9 || dp.equals("")) {      //入力できる桁数の制限
                                                            //演算子のボタンが押された後実行できるようにdpの値でも判別

        /*画面で0が連投されたとき、0を一つだけしか表示させない
        もしdpが０でないまたは、引数が " . " ならば・・
         */
            if (!(dp.equals("0")) || num.equals(".")) {

                //すでに画面に表示されてる値（dp）に、引数の値を追加
                dp += num;
            }

            //dpが0で、０〜９のボタンが押されたら
            else {

                dp = num;
            }

            //dpを画面（mTextView）に表示
            mTextView.setText(String.valueOf(dp));
        }
    }

    //四則演算のボタンが押されたときの処理内容
    public void operation(String mark){

        if(!(ope.equals(""))){

            calculation();
        }

        //opeに四則演算の記号を記憶
        ope = mark;

        //mTextViewの値をnowValueにgetTextして、valueにnowValueをdouble型に変換してを記憶する
        mTextView = (TextView) findViewById(R.id.display);
        String nowValue = mTextView.getText().toString();
        double value = Double.parseDouble(nowValue);

        //memoに画面の値を記憶
        memo = value;

        //次、数字が入力されたときに現在表記されている数字を消すために、dpを空にする
        dp = "";
    }

    //四則演算のボタンが押される前の値memoと押される後の値valueについて計算してディスプレイに表示する
    public void calculation(){

        mTextView = (TextView) findViewById(R.id.display);
        String nowValue = mTextView.getText().toString();
        double value = Double.parseDouble(nowValue);

        switch (ope) {
            case "+":
                total = memo + value;
                break;
            case "-":
                total = memo - value;
                break;
            case "*":
                total = memo * value;
                break;
            case "/":
                total = memo / value;
                break;
        }

        //int型のtotal
        int intTotal = (int)total;

        /*int型とdouble型の差が０ならint型で、そうでなければdouble型で表示する
        * 例えばtotalが5.1のとき、intTotalは5となるので二つの差はo.1となるのでdouble型の5.1を表示する
        * */
        if(total - intTotal == 0) {
            //計算結果を画面に表示
            mTextView.setText(String.valueOf(intTotal));
        }
        else{
            mTextView.setText(String.valueOf(total));
        }
    }

}