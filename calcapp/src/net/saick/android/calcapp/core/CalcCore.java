/**
 * 
 */
package net.saick.android.calcapp.core;

import java.text.DecimalFormat;
//import java.util.ArrayList;

//enum CalcAction {
//	NONE,		// 无-未更新
//	PLUS,		// 加
//	MINUS,		// 减
//	MULI,		// 乘
//	DIVIDE,		// 除
//	EQUAL,		// 等于
//	PERSENT,	// 百分比
//	PLUSMINUS	// 正负
//}

/**
 * @author Eric
 *
 */
public class CalcCore {
	private CalcCore() {
	}
	
	private static class CalcSingletonHolder {
		private final static CalcCore instance = new CalcCore();
	}
	private boolean chushi=true;
	private boolean b2=false;
	// 计算前的值
	private double lastValue = 0.0;
	// 输入编辑值
	private double inputValue = 0.0;
	// 当前显示的值（计算结果）
	private double currentValue = 0.0;
	// 当前操作
	private CalcAction lastAction = CalcAction.NONE;
	// TODO: 用来记录操作记录
//	private ArrayList history;
	
	/**
	 * 根据操作计算两个数的结果，如果操作不可计算，返回第二个数
	 * @param v1
	 * @param v2
	 * @param action
	 * @return 计算结果
	 */
	private double calculateWithTwoValue(double v1, double v2, CalcAction action) {
		double result = v2;
		CalcCore calc=CalcCore.getInstance();
		if(!calc.b2){
			return v1;
		}else{
		switch (action) {
		case DIVIDE:
			result = v1 / v2;
			break;
		case EQUAL:
			result = v2;
			break;
		case MINUS:
			result = v1 - v2;
			break;
		case MULI:
			result = v1 * v2;
			break;
		case NONE:
			result = v2;
			calc.chushi=false;
			break;
		case PERSENT:
			result = v2 * 0.01;
			break;
		case PLUS:
			result = v1 + v2;
			break;
		case PLUSMINUS: {
			if (v2 > 0) {
				result = 0 - v2;
			} else {
				result = v2 * -1;
			}
		}
			break;
		default:
			break;	
		}
		
		return result;
		}
	}

	
	
	/**
	 * 单例
	 * @return 实例
	 */
	public static CalcCore getInstance() {
		return CalcSingletonHolder.instance;
	}
	
	/**
	 * @param type 操作类型
	 * @return 计算结果
	 */
	public static String calculate(CalcAction action) {
		CalcCore calc = CalcCore.getInstance();
		
		CalcAction doAction;
		if (calc.lastAction != CalcAction.NONE || calc.chushi) {
			doAction = calc.lastAction;
		} else {
			doAction = action;
		}
		double result = calc.calculateWithTwoValue(calc.lastValue, calc.currentValue, doAction);
		//calc.lastValue = calc.currentValue;
		calc.lastValue = result;
		//calc.currentValue = result;
		calc.b2=false;
		calc.currentValue = 0.0;
		calc.lastAction = action;
		calc.inputValue = 0.0;
	
		return CalcCore.currentOutput(result);
	}
	
	/**
	 * @param value 值（数字）
	 */
	public static String input(String value) {
		CalcCore calc = CalcCore.getInstance();
		String origin = CalcCore.currentOutput(calc.inputValue);
		if (calc.inputValue <= 0.0) {
			origin = "";
		} else {
			// contiune
		}
		StringBuffer sb = new StringBuffer();
		sb.append(origin);
		value = Double.toString(Double.valueOf(value));
		//if (Double.valueOf(value) <= 0.0) {
			// continue
		//} else {
			sb.append(value);
		//}
		String current = sb.toString();
		
		try {
			calc.inputValue = Double.valueOf(current);
			calc.currentValue = Double.valueOf(current);
			calc.b2=true;
		} catch (Exception e) {
			
		} finally {
			
		}
		return CalcCore.currentOutput(calc.inputValue);
	}
	
	/**
	 * @return 可显示的当前值
	 */
	public static String currentOutput(double d) {
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########"); 
		//return decimalFormat.format(CalcCore.getInstance().currentValue);
		return decimalFormat.format(d);
	}
	
	/**
	 * 判断当前是否需要删除，如果不需要的话，显示 AC
	 * @return 是否需要删除
	 */
	public static boolean isNeedDelete() {
		CalcCore calc = CalcCore.getInstance();
		if (calc.currentValue != 0.0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 删除当前值
	 */
	public static String clear() {
		CalcCore calc = CalcCore.getInstance();
		calc.currentValue = 0.0;
		calc.inputValue = 0.0;
		calc.b2=false;
		return CalcCore.currentOutput(calc.currentValue);
	}
	
	/**
	 * 删除所有
	 */
	 public static String allclear() {
		 CalcCore calc = CalcCore.getInstance();
		 calc.currentValue = 0.0;
		 calc.inputValue = 0.0;
		 calc.lastValue = 0.0;
		 calc.lastAction = CalcAction.NONE;
//		 calc.history.clear();
		 calc.chushi=true;
		 return CalcCore.currentOutput(calc.currentValue);
	 }
	 public static String zf(){
		 CalcCore calc = CalcCore.getInstance();
		 
		 if(calc.b2==false&&calc.chushi==false){
			 calc.lastValue=0-calc.lastValue;
			 String s=calc.currentOutput(calc.lastValue);
			 return s;
		 }else if(calc.b2==true&&calc.chushi==false){
			 calc.currentValue=calc.currentValue*-1;
			 String s1=calc.currentOutput(calc.currentValue);
			 return s1; 
		 }else if(calc.chushi==true&&calc.inputValue!=0){
			 calc.currentValue=calc.currentValue*-1;
			 String s2=calc.currentOutput(calc.currentValue);
			 return s2;
		 }
		 
		return null;
		
	 }

}
