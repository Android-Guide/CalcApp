package net.saick.android.calcapp;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
//import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.saick.android.calcapp.R;
import net.saick.android.calcapp.core.CalcAction;
import net.saick.android.calcapp.core.CalcCore;

public class MainActivity extends Activity implements OnClickListener {

	private TextView et_xianshi;
	private Button bt_ac;
	private Button bt_jia;
	private Button bt_num0;
	private Button bt_num1;
	private Button bt_num2;
	private Button bt_num3;
	private Button bt_num4;
	private Button bt_num5;
	private Button bt_num6;
	private Button bt_num7;
	private Button bt_num8;
	private Button bt_num9;
	private Button bt_dian;
	private Button bt_jian;
	private Button bt_cheng;
	private Button bt_chu;
	private Button bt_bfh;
	private Button bt_dengyu;
	private Button bt_zf;
	
	private SoundPool soundPool;
	
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ret_copy, menu);
    }
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// AdapterContextMenuInfo info =
		// (AdapterContextMenuInfo)item.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.action_copy:
			String current = (String) et_xianshi.getText();
			current = current.replace(",", "");

			Log.e("shjborage", "you have click copy current value :" + current);
			ClipboardManager sysClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
			ClipData myClip = ClipData.newPlainText("text", current);
			sysClipboard.setPrimaryClip(myClip);
			
			return true;
		}

		return super.onContextItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.loadSounds();
		
		setContentView(R.layout.activity_main);
	//	EditText edit = (EditText) findViewById(R.id.et_xianshi);
	//	edit.setInputType(InputType.TYPE_NULL);
		et_xianshi = (TextView) findViewById(R.id.tv_jisuan);
		registerForContextMenu(et_xianshi);
		
		bt_num0 = (Button) findViewById(R.id.bt_num0);
		bt_num1 = (Button) findViewById(R.id.bt_num1);
		bt_num2 = (Button) findViewById(R.id.bt_num2);
		bt_num3 = (Button) findViewById(R.id.bt_num3);
		bt_num4 = (Button) findViewById(R.id.bt_num4);
		bt_num5 = (Button) findViewById(R.id.bt_num5);
		bt_num6 = (Button) findViewById(R.id.bt_num6);
		bt_num7 = (Button) findViewById(R.id.bt_num7);
		bt_num8 = (Button) findViewById(R.id.bt_num8);
		bt_num9 = (Button) findViewById(R.id.bt_num9);
		bt_dian = (Button) findViewById(R.id.bt_dian);
		bt_jia = (Button) findViewById(R.id.bt_jia);
		bt_jian = (Button) findViewById(R.id.bt_jian);
		bt_cheng = (Button) findViewById(R.id.bt_cheng);
		bt_chu = (Button) findViewById(R.id.bt_chu);
		bt_ac = (Button) findViewById(R.id.bt_ac);
		bt_bfh = (Button) findViewById(R.id.bt_bfh);
		bt_zf = (Button) findViewById(R.id.bt_zf);
		bt_dengyu = (Button) findViewById(R.id.bt_dengyu);
		
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
		bt_ac.setOnClickListener(this);
		bt_jia.setOnClickListener(this);
		bt_jian.setOnClickListener(this);
		bt_cheng.setOnClickListener(this);
		bt_chu.setOnClickListener(this);
		bt_dengyu.setOnClickListener(this);
		bt_zf.setOnClickListener(this);
		bt_bfh.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// 播放音效
		soundPool.play(1, 1, 1, 0, 0, 1);
		
		switch (v.getId()) {
		case R.id.bt_num0:
			et_xianshi.setText(CalcCore.input("0"));
			break;
		case R.id.bt_num1:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("1"));
			break;
		case R.id.bt_num2:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("2"));
			break;
		case R.id.bt_num3:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("3"));
			break;
		case R.id.bt_num4:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("4"));
			break;
		case R.id.bt_num5:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("5"));
			break;
		case R.id.bt_num6:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("6"));
			break;
		case R.id.bt_num7:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("7"));
			break;
		case R.id.bt_num8:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("8"));
			break;
		case R.id.bt_num9:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("9"));
			break;
		case R.id.bt_dian:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			et_xianshi.setText(CalcCore.input("."));
			break;
		case R.id.bt_ac:
		{
			boolean isNeedDelete = CalcCore.isNeedDelete();
			if (isNeedDelete) {
				et_xianshi.setText(CalcCore.clear());
			} else {
				XuanZhong.noxz(bt_jia);
				XuanZhong.noxz(bt_jian);
				XuanZhong.noxz(bt_cheng);
				XuanZhong.noxz(bt_chu);
				XuanZhong.no_left(bt_num3);
				XuanZhong.no_left(bt_num9);
				XuanZhong.no_left(bt_num6);
				XuanZhong.no_left(bt_bfh);
				et_xianshi.setText(CalcCore.allclear());
			}
		}
			break;
		case R.id.bt_jia:
			XuanZhong.xz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			String ret = CalcCore.calculate(CalcAction.PLUS);
			et_xianshi.setText(ret);
			break;
		case R.id.bt_jian:
			XuanZhong.noxz(bt_jia);
			XuanZhong.xz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			String ret1 = CalcCore.calculate(CalcAction.MINUS);
			et_xianshi.setText(ret1);
			break;
		case R.id.bt_cheng:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.xz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			String ret2 = CalcCore.calculate(CalcAction.MULI);
			et_xianshi.setText(ret2);
			break;
		case R.id.bt_chu:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.xz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.left(bt_bfh);
			String ret3 = CalcCore.calculate(CalcAction.DIVIDE);
			et_xianshi.setText(ret3);
			break;
		case R.id.bt_dengyu:
			XuanZhong.noxz(bt_jia);
			XuanZhong.noxz(bt_jian);
			XuanZhong.noxz(bt_cheng);
			XuanZhong.noxz(bt_chu);
			XuanZhong.no_left(bt_num3);
			XuanZhong.no_left(bt_num9);
			XuanZhong.no_left(bt_num6);
			XuanZhong.no_left(bt_bfh);
			String ret4 = CalcCore.calculate(CalcAction.EQUAL);
			et_xianshi.setText(ret4);
			break;
		case R.id.bt_zf:
			String ret5 = CalcCore.calculate(CalcAction.PLUSMINUS);
			et_xianshi.setText(ret5);
			break;
		case R.id.bt_bfh:
			String ret6 = CalcCore.calculate(CalcAction.PERSENT);
			et_xianshi.setText(ret6);
			break;
		default:
			break;
		}
		
		boolean isNeedDelete = CalcCore.isNeedDelete();
		Button bt_ac = (Button)findViewById(R.id.bt_ac);
		if (isNeedDelete) {
			bt_ac.setText("C");
		} else {
			bt_ac.setText("AC");
		}
	}
	
	private void loadSounds() {
 		soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM,5);
 		soundPool.load(this, R.raw.dada, 1);
	}

}
class XuanZhong{

		public static void xz(Button bt){
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)bt.getLayoutParams();
			layoutParams.setMargins(0,1,0,1);
			bt.setLayoutParams(layoutParams);

		}
		public static void noxz(Button bt){
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)bt.getLayoutParams();
			layoutParams.setMargins(0,0,0,0);
			bt.setLayoutParams(layoutParams);
		
		}
		public static void left(Button bt){
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)bt.getLayoutParams();
			layoutParams.setMargins(0,0,2,0);
			bt.setLayoutParams(layoutParams);
		}
		public static void no_left(Button bt){
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)bt.getLayoutParams();
			layoutParams.setMargins(0,0,1,0);
			bt.setLayoutParams(layoutParams);
		}
		}

