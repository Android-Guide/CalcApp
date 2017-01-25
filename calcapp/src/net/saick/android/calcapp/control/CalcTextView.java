/**
 * 
 */
package net.saick.android.calcapp.control;

import android.content.Context;
import android.util.AttributeSet;
import net.saick.android.control.AutoAjustSizeTextView;

/**
 * @author Eric
 * 支持小数点前数字，每3位以逗号分隔
 */
public class CalcTextView extends AutoAjustSizeTextView {

	/**
	 * @param context
	 */
	public CalcTextView(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CalcTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CalcTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override  
    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        super.onTextChanged(text, start, before, after);
    } 
	
	@Override
	public void setText(CharSequence text, BufferType type) {
        
		String toCheckString = text.toString();
		String toAddString = "";
		String lastString = "";
		int index = toCheckString.indexOf(".");
		if (index == -1) {
			// 未找到小数点
			toAddString = toCheckString;
		} else {
			toAddString = toCheckString.substring(0, index);
			lastString = toCheckString.substring(index);
		}
		
		String retString = "";
		if (toAddString.length() <= 3) {
			retString = toAddString;
			// continue
		} else {
			// 从后向前每3位，添加一个，
			StringBuilder sb = new StringBuilder();
			int flag = 1;
			for(int i=toAddString.length()-1; i>=0; i--) {
				sb.insert(0, toAddString.charAt(i));
				if (flag % 3 == 0 && i != 0) {
					sb.insert(0, ",");
				} else {
					// continue
				}
				flag += 1;
			}
			retString = sb.toString() + lastString;
		}
		
		super.setText(retString, type);
    }
}
