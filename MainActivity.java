package com.haha.jisuanqi;

import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private EditText et_xianshi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditText edit = (EditText) findViewById(R.id.et_xianshi);
		edit.setInputType(InputType.TYPE_NULL);
		et_xianshi = (EditText) findViewById(R.id.et_xianshi);
		Button bt_num0 = (Button) findViewById(R.id.bt_num0);
		Button bt_num1 = (Button) findViewById(R.id.bt_num1);
		Button bt_num2 = (Button) findViewById(R.id.bt_num2);
		Button bt_num3 = (Button) findViewById(R.id.bt_num3);
		Button bt_num4 = (Button) findViewById(R.id.bt_num4);
		Button bt_num5 = (Button) findViewById(R.id.bt_num5);
		Button bt_num6 = (Button) findViewById(R.id.bt_num6);
		Button bt_num7 = (Button) findViewById(R.id.bt_num7);
		Button bt_num8 = (Button) findViewById(R.id.bt_num8);
		Button bt_num9 = (Button) findViewById(R.id.bt_num9);
		Button bt_dian = (Button) findViewById(R.id.bt_dian);
		Button bt_jia = (Button) findViewById(R.id.bt_jia);
		Button bt_jian = (Button) findViewById(R.id.bt_jian);
		Button bt_cheng = (Button) findViewById(R.id.bt_cheng);
		Button bt_chu = (Button) findViewById(R.id.bt_chu);
		Button bt_shanchu = (Button) findViewById(R.id.bt_shanchu);
		Button bt_dengyu = (Button) findViewById(R.id.bt_dengyu);
		bt_num0.setOnClickListener(this);
		bt_num1.setOnClickListener(this);
		bt_num2.setOnClickListener(this);
		bt_num3.setOnClickListener(this);
		bt_num4.setOnClickListener(this);
		bt_num5.setOnClickListener(this);
		bt_num6.setOnClickListener(this);
		bt_num7.setOnClickListener(this);
		bt_num8.setOnClickListener(this);
		bt_num9.setOnClickListener(this);
		bt_dian.setOnClickListener(this);
		bt_shanchu.setOnClickListener(this);
		bt_jia.setOnClickListener(this);
		bt_jian.setOnClickListener(this);
		bt_cheng.setOnClickListener(this);
		bt_chu.setOnClickListener(this);
		bt_dengyu.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_num0:
			et_xianshi.append("0");
			break;
		case R.id.bt_num1:
			et_xianshi.append("1");
			break;
		case R.id.bt_num2:
			et_xianshi.append("2");
			break;
		case R.id.bt_num3:
			et_xianshi.append("3");
			break;
		case R.id.bt_num4:
			et_xianshi.append("4");
			break;
		case R.id.bt_num5:
			et_xianshi.append("5");
			break;
		case R.id.bt_num6:
			et_xianshi.append("6");
			break;
		case R.id.bt_num7:
			et_xianshi.append("7");
			break;
		case R.id.bt_num8:
			et_xianshi.append("8");
			break;
		case R.id.bt_num9:
			et_xianshi.append("9");
			break;
		case R.id.bt_dian:
			et_xianshi.append(".");
			break;
		case R.id.bt_shanchu:
			String s = et_xianshi.getText().toString();
			if (!"".equals(s)) {
				s = s.substring(0, s.length() - 1);
				et_xianshi.setText(s);
			}
			break;
		case R.id.bt_jia:
			et_xianshi.append("+");
			break;
		case R.id.bt_jian:
			et_xianshi.append("-");
			break;
		case R.id.bt_cheng:
			et_xianshi.append("×");
			break;
		case R.id.bt_chu:
			et_xianshi.append("÷");
			break;
		case R.id.bt_dengyu:
			String s1 = et_xianshi.getText().toString();
			boolean jia = s1.contains("+");
			boolean jian = s1.contains("-");
			boolean cheng = s1.contains("×");
			boolean chu = s1.contains("÷");
			if ("".equals(s1)) {
				Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT).show();
				return;
			}
			if (!jia && !jian && !cheng && !chu) {
				Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT).show();
				return;
			}
			if (s1.endsWith(".") || s1.endsWith("+") || s1.endsWith("-")
					|| s1.endsWith("×") || s1.endsWith("÷")) {
				Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT).show();
				return;
			}
			if (jia) {
				if (s1.indexOf("+") != s1.lastIndexOf("+")
						|| s1.indexOf("+") < 1) {
					Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if(jian||cheng||chu){
					Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
					.show();
			return;
				}
				int index = s1.indexOf("+");
				String a = s1.substring(0, index);
				String b = s1.substring(index + 1, s1.length());
				if(a.contains(".")){
					if(a.indexOf(".")!=a.lastIndexOf(".")||a.indexOf(".")==a.length()-1){
						Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
						.show();
				return;
					}
				}
				if(b.contains(".")){
					if(b.indexOf(".")!=b.lastIndexOf(".")||b.indexOf(".")==0){
						Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
						.show();
				return;
					}
				}
				double x = Double.parseDouble(a);
				double y = Double.parseDouble(b);
				String s2 = String.valueOf(x + y);
				et_xianshi.setText(s2);
			} else if (jian) {
				if (s1.indexOf("-") != s1.lastIndexOf("-")
						|| s1.indexOf("-") < 1) {
					Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if(jia||cheng||chu){
					Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
					.show();
			return;
				}
				int index = s1.indexOf("-");
				String a = s1.substring(0, index);
				String b = s1.substring(index + 1, s1.length());
				if(a.contains(".")){
					if(a.indexOf(".")!=a.lastIndexOf(".")||a.indexOf(".")==a.length()-1){
						Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
						.show();
				return;
					}
				}
				if(b.contains(".")){
					if(b.indexOf(".")!=b.lastIndexOf(".")||b.indexOf(".")==0){
						Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
						.show();
				return;
					}
				}
				double x = Double.parseDouble(a);
				double y = Double.parseDouble(b);
				String s2 = String.valueOf(x - y);
				et_xianshi.setText(s2);

			}else if (cheng) {
				if (s1.indexOf("×") != s1.lastIndexOf("×")
						|| s1.indexOf("×") < 1) {
					Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if(jia||jian||chu){
					Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
					.show();
			return;
				}
				int index = s1.indexOf("×");
				String a = s1.substring(0, index);
				String b = s1.substring(index + 1, s1.length());
				if(a.contains(".")){
					if(a.indexOf(".")!=a.lastIndexOf(".")||a.indexOf(".")==a.length()-1){
						Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
						.show();
				return;
					}
				}
				if(b.contains(".")){
					if(b.indexOf(".")!=b.lastIndexOf(".")||b.indexOf(".")==0){
						Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
						.show();
				return;
					}
				}
				double x = Double.parseDouble(a);
				double y = Double.parseDouble(b);
				String s2 = String.valueOf(x * y);
				et_xianshi.setText(s2);

			}else if (chu) {
				if (s1.indexOf("÷") != s1.lastIndexOf("÷")
						|| s1.indexOf("÷") < 1) {
					Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if(jia||jian||cheng){
					Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
					.show();
			return;
				}
				int index = s1.indexOf("÷");
				String a = s1.substring(0, index);
				String b = s1.substring(index + 1, s1.length());
				if(a.contains(".")){
					if(a.indexOf(".")!=a.lastIndexOf(".")||a.indexOf(".")==a.length()-1){
						Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
						.show();
				return;
					}
				}
				if(b.contains(".")){
					if(b.indexOf(".")!=b.lastIndexOf(".")||b.indexOf(".")==0){
						Toast.makeText(this, "你输入的运算格式有误", Toast.LENGTH_SHORT)
						.show();
				return;
					}
				}
				double x = Double.parseDouble(a);
				double y = Double.parseDouble(b);
				String s2 = String.valueOf(x / y);
				et_xianshi.setText(s2);

			}
	
			break;

		default:
			break;
		}
	}

}
