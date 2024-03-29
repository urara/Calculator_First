package com.example.testcalculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestCalculatorActivity extends Activity {
    /** Called when the activity is first created. */

	static final int PLUS = 1;			//演算子が＋のとき
	static final int MINUS =2;			//演算子が−のとき
	static final int MULTIPLE = 3;		//演算子が×のとき
	static final int DEVIDE = 4;		//演算子が÷のとき
	static final int MAX_LENGTH = 8;	
	
	
	
	BigDecimal A;					//計算する数１
	int operand = 0;				//オペランド
	BigDecimal B;					//計算する数２
	BigDecimal result;				//計算結果
	int initFlag = 0;				//初期化フラグが立ってたら数字押したらその数セット
	int commaFlag = 0;				//コンマ一回押されてたら立てるフラグ
	int evaluateFlag = 0;			//コンマの位置
	
	TextView display; 				//出力される結果
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        display = (TextView)findViewById(R.id.display);
        Log.d("testmethod","result = " + normalization("100000000"));

        
        //0~9,00を押された場合を操作するインスタンス
        Button button0 = (Button)findViewById(R.id.zero);
        Button button1 = (Button)findViewById(R.id.one);
        Button button2 = (Button)findViewById(R.id.two);
        Button button3 = (Button)findViewById(R.id.three);
        Button button4 = (Button)findViewById(R.id.four);
        Button button5 = (Button)findViewById(R.id.five);
        Button button6 = (Button)findViewById(R.id.six);
        Button button7 = (Button)findViewById(R.id.seven);
        Button button8 = (Button)findViewById(R.id.eight);
        Button button9 = (Button)findViewById(R.id.nine);
        Button button00 = (Button)findViewById(R.id.doublezero);
        
        //オペランドインスタンス
        Button buttonPlus = (Button)findViewById(R.id.plus);
        Button buttonMinus = (Button)findViewById(R.id.minus);
        Button buttonMultiple = (Button)findViewById(R.id.multiple);
        Button buttonDevide = (Button)findViewById(R.id.devide);
        
        //その他インスタンス
        Button buttonAC = (Button)findViewById(R.id.allclear);
        Button buttonBS = (Button)findViewById(R.id.backspace);
        Button buttonEQ = (Button)findViewById(R.id.equal);
        Button buttonComma = (Button)findViewById(R.id.comma);
        
        
        //オペランド
        //足し算
        buttonPlus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				A = new BigDecimal(display.getText().toString());	
				operand = PLUS;
				initFlag = 1;
				commaFlag = 0;
			}
		});
        
        //引き算
        buttonMinus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				A = new BigDecimal(display.getText().toString());
				operand = MINUS;
				initFlag = 1;
				commaFlag = 0;
			}
		});
        
        //掛け算
        buttonMultiple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				A = new BigDecimal(display.getText().toString());
				operand = MULTIPLE;
				initFlag = 1;
				commaFlag = 0;
			}
		});
        
        //割り算
        buttonDevide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				A = new BigDecimal(display.getText().toString());
				operand = DEVIDE;
				initFlag = 1;
				commaFlag = 0;
			}
		});        
        
        //allclearボタン
        buttonAC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				A = new BigDecimal("0");
				B = new BigDecimal("0");
				display.setText("0");
				commaFlag = 0;
			}
		});
        
        //backspaceボタン
        buttonBS.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//backspaceで消される文字が”.”のときコンマがまた打てるように

				if(display.getText().toString().substring(display.getText().length()-1, display.getText().length()).equals("." + "")){
					commaFlag = 0;
				}
		    	display.setText(display.getText().subSequence(0, display.getText().length()-1));
		    	if(display.getText().length() == 0){
		    		display.setText("0");
		    	
				}
			}
		});        
        
        //equalボタン
        buttonEQ.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				commaFlag = 0;
				//足し算
				if(operand == PLUS){
					B = new BigDecimal(display.getText().toString());
					result = A.add(B);
					display.setText(normalization("" + result));
					operand = 0;
					initFlag = 1;
				}

				//引き算
				else if(operand == MINUS){
					B = new BigDecimal(display.getText().toString());
					result = A.subtract(B);
					display.setText(normalization("" + result));
					operand = 0;
					initFlag = 1;
				}

				//掛け算
				else if(operand == MULTIPLE){
					B = new BigDecimal(display.getText().toString());
					result = A.multiply(B);
					//Log.d("cutZero前","result = " + result);
					String temp = cutZero("" + result);
					Log.d("cutZero後","result = " + result + " temp=" + temp);
					display.setText(normalization("" + temp));
					operand = 0;
					initFlag = 1;
				}
				
				//割り算
				else if(operand == DEVIDE){
					B = new BigDecimal(display.getText().toString());
					if(B.toString().equals("0")){
						Log.d("=if=","A =" + A + " B =" + B + "result =" + result);
						display.setText("0");
					}else{
						result = A.divide(B, MAX_LENGTH-2, BigDecimal.ROUND_HALF_UP);
						Log.d("devide","A =" + A + " B =" + B + "result =" + result);
						String temp = cutZero("" + result);
						display.setText(normalization("" + temp));
						operand = 0;
						initFlag = 1;
					}
					
					Log.d("owari","A =" + A + " B =" + B + "result =" + result);
					operand = 0;
					initFlag = 1;
				}
			
			}
		});
        
        
        
        //コロンが押されたとき
        buttonComma.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){

					
					//Log.d("if中","setText");	
					display.setText("0.");
					initFlag = 0;
					commaFlag=1;
					
				}else{
					if(commaFlag == 1){
						
					}else{
						//Log.d("if中","append");	
						display.append(".");
						commaFlag = 1;
					}
				}
			}
		});
        
        
        //数字押されたとき
        button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					display.setText("1");
					initFlag = 0;
				}else{
					display.append("1");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if中","setText");	
					display.setText("2");
					initFlag = 0;
				}else{
					display.append("2");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if中","setText");	
					display.setText("3");
					initFlag = 0;
				}else{	
					display.append("3");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if中","setText");	
					display.setText("4");
					initFlag = 0;
				}else{
					display.append("4");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					display.setText("5");
					initFlag = 0;
				}else{
					display.append("5");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					display.setText("6");
					initFlag = 0;
				}else{
					display.append("6");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					display.setText("7");
					initFlag = 0;
				}else{
					display.append("7");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					display.setText("8");
					initFlag = 0;
				}else{
					display.append("8");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					display.setText("9");
					initFlag = 0;
				}else{
					
					display.append("9");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					display.setText("0");
					initFlag = 0;
				}else{
					display.append("0");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button00.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals("" + display.getText() ) || initFlag == 1){
					display.setText("0");
					initFlag = 0;
				}else{
					display.append("00");
					display.setText(evalLength(display.getText().toString()));
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});

    }
    //長かった時に切る(文字が打ち続けられないように)
    public String evalLength(String temp){
    	if(temp.length() > MAX_LENGTH){
    		return "" + temp.subSequence(0, MAX_LENGTH);
    	}else
    	return temp;
    }

    //正規化用
    public String normalization(String display){
    	String result = display;
    	String normalizedDisplay = null;
    	if(display.length() > MAX_LENGTH){
    		result = "";

    		DecimalFormat df = new DecimalFormat("0.00E0");
    		normalizedDisplay = df.format(new BigDecimal(display));
    		    		
   			result = normalizedDisplay.substring(0, 5);	
   			if(display.length() > 10){
   				Log.d("大きい時","result = " + result + " normdis = " + normalizedDisplay);
   				result += normalizedDisplay.substring(normalizedDisplay.length()-3 , normalizedDisplay.length());
   			}else{
   				Log.d("小さい時","result = " + result + " normdis = " + normalizedDisplay);	
   				result += normalizedDisplay.substring(normalizedDisplay.length()-2 , normalizedDisplay.length());
    		}
    			
    		Log.d("DecimalFormat4","result = " + result + " display.substring(0, 5)=" + display.substring(0, 5) 
    				+ "display.substring(display.length() - 4, display.length() = " + display.substring(display.length() - 4, display.length()));
    		
    	}
    	return result;
    }
    
    //小数点以下の最下位の0を切る
    public String cutZero(String number){
    	
    	int canCutFlag = 0;
       	int length = number.length();
    	if(length > MAX_LENGTH){
    		length = MAX_LENGTH;
    	}
    	
    	for(int i=0; i<length ; i++){
    		String chara = number.substring(i, i+1);
   			if(chara.equals(".")){
   				canCutFlag = 1;
   				break;
   			}
    	}
    	if(canCutFlag == 1){
        	for(int i = number.length()-1 ; i > 0 ; i--){
    			if(number.substring(i , i+1).equals("0")){
    				number = number.substring(0 , i);
    			}else if(number.substring(i , i+1).equals(".")){
    				number = number.substring(0 , i);
    				break;
    			}else{
    				break;
    			}
    		}
    	}
    	canCutFlag = 0;
    	return number;
    }
}