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
import android.widget.TextView;
import net.saick.android.calcapp.R;
import net.saick.android.calcapp.core.CalcAction;
import net.saick.android.calcapp.core.CalcCore;

public class MainActivity extends Activity implements OnClickListener {

	private TextView et_xianshi;
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
		Button bt_ac = (Button) findViewById(R.id.bt_ac);
		Button bt_dengyu = (Button) findViewById(R.id.bt_dengyu);
		Button bt_zf = (Button) findViewById(R.id.bt_zf);
		Button bt_bfh = (Button) findViewById(R.id.bt_bfh);
		
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
			et_xianshi.setText(CalcCore.input("1"));
			break;
		case R.id.bt_num2:
			et_xianshi.setText(CalcCore.input("2"));
			break;
		case R.id.bt_num3:
			et_xianshi.setText(CalcCore.input("3"));
			break;
		case R.id.bt_num4:
			et_xianshi.setText(CalcCore.input("4"));
			break;
		case R.id.bt_num5:
			et_xianshi.setText(CalcCore.input("5"));
			break;
		case R.id.bt_num6:
			et_xianshi.setText(CalcCore.input("6"));
			break;
		case R.id.bt_num7:
			et_xianshi.setText(CalcCore.input("7"));
			break;
		case R.id.bt_num8:
			et_xianshi.setText(CalcCore.input("8"));
			break;
		case R.id.bt_num9:
			et_xianshi.setText(CalcCore.input("9"));
			break;
		case R.id.bt_dian:
			et_xianshi.setText(CalcCore.input("."));
			break;
		case R.id.bt_ac:
		{
			boolean isNeedDelete = CalcCore.isNeedDelete();
			if (isNeedDelete) {
				et_xianshi.setText(CalcCore.clear());
			} else {
				et_xianshi.setText(CalcCore.allclear());
			}
		}
			break;
		case R.id.bt_jia:
			String ret = CalcCore.calculate(CalcAction.PLUS);
			et_xianshi.setText(ret);
			break;
		case R.id.bt_jian:
			String ret1 = CalcCore.calculate(CalcAction.MINUS);
			et_xianshi.setText(ret1);
			break;
		case R.id.bt_cheng:
			String ret2 = CalcCore.calculate(CalcAction.MULI);
			et_xianshi.setText(ret2);
			break;
		case R.id.bt_chu:
			String ret3 = CalcCore.calculate(CalcAction.DIVIDE);
			et_xianshi.setText(ret3);
			break;
		case R.id.bt_dengyu:
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
