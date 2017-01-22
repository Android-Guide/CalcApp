/**
 * 
 */
package net.saick.android.calcapp.core;

import java.util.ArrayList;

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
	// 计算前的值
	private double lastValue = 0.0;
	// 当前显示的值（计算结果）
	private double currentValue = 0.0;
	// 当前操作
	private CalcAction lastAction = CalcAction.NONE;
	// TODO: 用来记录操作记录
	private ArrayList history;
	
	private CalcCore() {
	}

	/**
	 * 根据操作计算两个数的结果，如果操作不可计算，返回第二个数
	 * @param v1
	 * @param v2
	 * @param action
	 * @return 计算结果
	 */
	private double calculateWithTwoValue(double v1, double v2, CalcAction action) {
		double result = v2;
		
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

	private static class CalcSingletonHolder {
		private final static CalcCore instance = new CalcCore();
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
		if (action == CalcAction.EQUAL) {
			doAction = calc.lastAction;
		} else {
			doAction = action;
		}
		double result = calc.calculateWithTwoValue(calc.lastValue, calc.currentValue, doAction);
		calc.lastValue = calc.currentValue;
		calc.currentValue = result;
		calc.lastAction = action;
	
		return CalcCore.currentOutput();	
	}
	
	/**
	 * @param value 值（数字）
	 */
	public static String input(String value) {
		CalcCore calc = CalcCore.getInstance();
		String origin = Double.toString(calc.currentValue);
		if (calc.currentValue <= 0.0) {
			origin = "";
		} else {
			// contiune
		}
		StringBuffer sb = new StringBuffer();
		sb.append(origin);
		value = Double.toString(Double.valueOf(value));
		if (Double.valueOf(value) <= 0.0) {
			// continue
		} else {
			sb.append(value);
		}
		String current = sb.toString();
		
		try {
			calc.currentValue = Double.valueOf(current);
		} catch (Exception e) {
			
		} finally {
			
		}
		return CalcCore.currentOutput();
	}
	
	/**
	 * @return 可显示的当前值
	 */
	public static String currentOutput() {
		return Double.toString(CalcCore.getInstance().currentValue);
	}
}
