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

	static final int PLUS = 1;		//���Z�q���{�̂Ƃ�
	static final int MINUS =2;		//���Z�q���|�̂Ƃ�
	static final int MULTIPLE = 3;	//���Z�q���~�̂Ƃ�
	static final int DEVIDE = 4;	//���Z�q�����̂Ƃ�44
	static final int MAX_LENGTH = 8;
	
	
	
	BigDecimal A;					//�v�Z���鐔�P
	int operand = 0;				//�I�y�����h
	BigDecimal B;					//�v�Z���鐔�Q
	BigDecimal result;				//�v�Z����
	int initFlag = 0;				//�������t���O�������Ă��琔���������炻�̐��Z�b�g
	int commaFlag = 0;				//�R���}��񉟂���Ă��痧�Ă�t���O
	int evaluateFlag = 0;			//�R���}�̈ʒu
	
	
	TextView display; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        display = (TextView)findViewById(R.id.display);
        //0~9�������ꂽ�ꍇ�𑀍삷��C���X�^���X
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
        
        //�I�y�����h�C���X�^���X
        Button buttonPlus = (Button)findViewById(R.id.plus);
        Button buttonMinus = (Button)findViewById(R.id.minus);
        Button buttonMultiple = (Button)findViewById(R.id.multiple);
        Button buttonDevide = (Button)findViewById(R.id.devide);
        
        //���̑��C���X�^���X
        Button buttonAC = (Button)findViewById(R.id.allclear);
        Button buttonBS = (Button)findViewById(R.id.backspace);
        Button buttonEQ = (Button)findViewById(R.id.equal);
        Button buttonComma = (Button)findViewById(R.id.comma);
        
        
        //�I�y�����h
        //�����Z
        buttonPlus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//Log.d("tasizan","");
				//A = Integer.parseInt("" + display.getText());
				A = new BigDecimal(display.getText().toString());	
				
				operand = PLUS;
				initFlag = 1;
				commaFlag = 0;
				//display.setText("0");
			}
		});
        
        //�����Z
        buttonMinus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				A = new BigDecimal(display.getText().toString());
				operand = MINUS;
				initFlag = 1;
				commaFlag = 0;
				//display.setText("0");
			}
		});
        
        //�|���Z
        buttonMultiple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				A = new BigDecimal(display.getText().toString());
				operand = MULTIPLE;
				initFlag = 1;
				commaFlag = 0;
				//display.setText("0");
			}
		});
        
        //����Z
        buttonDevide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				A = new BigDecimal(display.getText().toString());
				operand = DEVIDE;
				initFlag = 1;
				commaFlag = 0;
				//display.setText("0");
			}
		});
        
        
        //allclear�{�^��
        buttonAC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				A = new BigDecimal("0");
				B = new BigDecimal("0");
				display.setText("0");
				commaFlag = 0;
			}
		});
        
        //backspace�{�^��
        buttonBS.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Log.d("BS","aaa");
				//TextView display = (TextView)findViewById(R.id.display);
				//backspace�ŃR���}�������Ƃ�
				if(display.toString().substring(display.getText().length()-1, display.getText().length()).equals(".")){
					commaFlag = 0;
				}else{
				
		    		display.setText(display.getText().subSequence(0, display.getText().length()-1));
		    		if(display.getText().length() == 0){
		    			display.setText("0");
		    		}
				}
			}
		});
        
        
        //equal�{�^��
        buttonEQ.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				commaFlag = 0;
				//�����Z
				if(operand == PLUS){
					//int result = A + Integer.parseInt("" + display.getText());
					B = new BigDecimal(display.getText().toString());
					result = A.add(B);
					
					display.setText("" + result);
					operand = 0;
					initFlag = 1;
				}

				//�����Z
				else if(operand == MINUS){
					B = new BigDecimal(display.getText().toString());
					result = A.subtract(B);
					display.setText("" + result);
					operand = 0;
					initFlag = 1;
				}

				//�|���Z
				else if(operand == MULTIPLE){
					B = new BigDecimal(display.getText().toString());
					result = A.multiply(B);
					Log.d("cutZero�O","result = " + result);
					String temp = cutZero("" + result);
					Log.d("cutZero��","result = " + result);
					display.setText("" + normalization("" + temp));
					operand = 0;
					initFlag = 1;
				}
				
				//����Z
				else if(operand == DEVIDE){
					B = new BigDecimal(display.getText().toString());
					result = A.divide(B, 2, BigDecimal.ROUND_HALF_UP);
					String temp = cutZero("" + result);
					display.setText(result.toString());
					operand = 0;
					initFlag = 1;
				}
				//���̑�
				else{
				
				}
				
			}
		});
        
        
        
        //�R�����������ꂽ�Ƃ�
        buttonComma.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				//Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){

					
					//Log.d("if��","setText");	
					display.setText("0.");
					initFlag = 0;
					commaFlag=1;
					
				}else{
					if(commaFlag == 1){
						
					}else{
						//Log.d("if��","append");	
						display.append(".");
						commaFlag = 1;
					}
				}
			}
		});
        
        
        //���������ꂽ�Ƃ�
        button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				//Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("1");
					initFlag = 0;
				}else{
					//Log.d("if��","append");	
					display.append("1");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				//Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("2");
					initFlag = 0;
				}else{
					//Log.d("if��","append");	
					display.append("2");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				//Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("3");
					initFlag = 0;
				}else{
					//Log.d("if��","append");	
					display.append("3");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				//Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("4");
					initFlag = 0;
				}else{
					//Log.d("if��","append");	
					display.append("4");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				//Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("5");
					initFlag = 0;
				}else{
					//Log.d("if��","append");	
					display.append("5");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				//Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("6");
					initFlag = 0;
				}else{
					//Log.d("if��","append");	
					display.append("6");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("7");
					initFlag = 0;
				}else{
					Log.d("if��","append");	
					display.append("7");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("8");
					initFlag = 0;
				}else{
					Log.d("if��","append");	
					display.append("8");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("9");
					initFlag = 0;
				}else{
					
					display.append("9");
					Log.d("setText�O","display=" + display.getText());	
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("0");
					initFlag = 0;
				}else{
					Log.d("if��","append");	
					display.append("0");
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});
        
        button00.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TextView display = (TextView)findViewById(R.id.display);
				//TextView display = new TextView(this);

				Log.d("debug","display={" + display.getText() + "}");
				if("0".equals("" + display.getText() ) || initFlag == 1){
					Log.d("if��","setText");	
					display.setText("0");
					initFlag = 0;
				}else{
					Log.d("if��","append");	
					display.append("00");
					display.setText(evalLength(display.getText().toString()));
					display.setText(evalLength(display.getText().toString()));
				}
			}
		});

    }
    
    private String evalLength(String temp){
    	
    	if(temp.length() > MAX_LENGTH){
    		return "" + temp.subSequence(0, MAX_LENGTH);
    	}else
    	return temp;
    }

    //���K���p
    private String normalization(String display){
    	if(display.length() > MAX_LENGTH){
    		//�����������R���}��T��
    		
    		/*
    		for(int i=0; i<MAX_LENGTH ; i++){
    			String chara = display.substring(i, i+1);
    			
    			//�����̐��K��
    			if(chara.equals(".")){
    				Log.d("normaliza", "if��");
    				Double temp = Double.parseDouble(display)/Math.pow(10, (i-1));
    				display = temp.toString().substring(0, MAX_LENGTH-5) + "E" + (i-1);
    				//display = temp + "E" + (i-1);
    				break;
     			}
    			
    		}
    		*/
    		Log.d("DecimalFormat1",display);
    		DecimalFormat df = new DecimalFormat("0.00E0");
    		Log.d("DecimalFormat2",display);
    		display = df.format(new BigDecimal(display));
    		Log.d("DecimalFormat3",display);
    		//Double temp = Double.parseDouble(display);  //Math.pow(10, display.length() - MAX_LENGTH);
    		//isplay = temp.toString();
    		
    		
    		
    	}
    	return display;
    }
    
    public String cutZero(String number){
    	Log.d("cutzero-1",number);
    	int canCutFlag = 0;
    	Log.d("cutzero0",number);
    	int length = number.length();
    	Log.d("cutzero","Length = " + length);
    	if(length > MAX_LENGTH){
    		length = MAX_LENGTH;
    	}
    	
    	
    	for(int i=0; i<length ; i++){
    		//Log.d("cutZero in if", "MAX_LENGTH= " + MAX_LENGTH + "i =" + i);
    		Log.d("cutzero1","");
    		String chara = number.substring(i, i+1);
   			if(chara.equals(".")){
   				canCutFlag = 1;
       			Log.d("cutZero in if", "canCutFlag= " + canCutFlag + "i =" + i);
   				break;
   			}
    	}
    	Log.d("if�O", "aaa");
    	if(canCutFlag == 1){
        	for(int i = number.length()-1 ; i > 0 ; i--){
    			Log.d("cutZero in if", "canCutFlag= " + canCutFlag + "i =" + i);
    			if(number.substring(i , i+1).equals("0")){
    				Log.d("if��", "number = " + number);
    				number = number.substring(0 , i);
    			}else if(number.substring(i , i+1).equals(".")){
    				number = number.substring(0 , i);
    			}else{
    				break;
    			}
    		}
    	}
    	canCutFlag = 0;
    	return number;
    }
}