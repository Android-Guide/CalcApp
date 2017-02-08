/**
 * 
 */
package net.saick.android.calcapp.core;

import java.text.DecimalFormat;

import android.util.Log;
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

	// 计算状态
	private CalcState state = CalcState.INIT;

	// 输入编辑值
	private double inputValue = 0.0;
	// 当前显示的值（计算结果）
	private double currentValue = 0.0;
	// 计算前的值
	private double lastValue = 0.0;
	// 准备计算的操作
	private CalcAction currentAction = CalcAction.NONE;
	// 当前操作
	private CalcAction lastAction = CalcAction.NONE;

	// TODO: 用来记录操作记录
	// private ArrayList history;

	/**
	 * 根据操作计算两个数的结果，如果操作不可计算，返回第二个数
	 * 
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

	/**
	 * 单例
	 * 
	 * @return 实例
	 */
	public static CalcCore getInstance() {
		return CalcSingletonHolder.instance;
	}

	/**
	 * @param action
	 *            操作类型
	 * @return 计算结果
	 */
	public static String calculate(CalcAction action) {
		CalcCore calc = CalcCore.getInstance();

		if (calc.state == CalcState.INIT) {
			// 初始状态不做任何操作
			return CalcCore.currentOutput();
		} else if (calc.state == CalcState.INPUT1 || calc.state == CalcState.ACTIONREADY) {
			calc.state = CalcState.ACTIONREADY;
			calc.currentAction = action;
			// 只有百分号和正负号需要在这时计算
			if (action == CalcAction.PERSENT) {
				
			} else if (action == CalcAction.PLUSMINUS) {
				double result = calc.calculateWithTwoValue(calc.lastValue, calc.currentValue, action);
				calc.lastValue = calc.currentValue;
				calc.currentValue = result;
				calc.state = CalcState.RESULT;
			} else {
				// 其它操作的话，把当前值保存下来作为input1用来进行计算
				calc.lastValue = calc.currentValue;
			}
		} else if (calc.state == CalcState.INPUT2) {
			calc.state = CalcState.CALC;
			
			if (action == CalcAction.PERSENT) {
				// 百分号
			} else if ( action == CalcAction.PLUSMINUS) {
				// 正负号
			} else {
				double result = calc.calculateWithTwoValue(calc.lastValue, calc.currentValue, calc.currentAction);
				calc.lastValue = calc.currentValue;
				calc.lastAction = action;
				calc.currentValue = result;
				calc.state = CalcState.RESULT;
			}
		} else if (calc.state == CalcState.CALC) {
			// do nothing
		} else if (calc.state == CalcState.RESULT) {
			// 如果之前计算过，再按等于的话，再计算一次之前的操作。
			// 其它运算符需要考虑运算优先级
			calc.state = CalcState.CALC;
			
			if (action == CalcAction.EQUAL) {
				double result = calc.calculateWithTwoValue(calc.lastValue, calc.currentValue, calc.lastAction);
				calc.lastValue = calc.currentValue;
				calc.currentValue = result;
				calc.state = CalcState.RESULT;
			} else {
				// +- 和 */ 要分开处理
				// 百分号也要等特殊处理
			}
		}

		return CalcCore.currentOutput();
	}

	/**
	 * @param value
	 *            值（数字）
	 */
	public static String input(String value) {
		CalcCore calc = CalcCore.getInstance();

		String current = "";
		if (calc.state == CalcState.INPUT1 || calc.state == CalcState.INPUT2) {
			String origin = CalcCore.currentOutput(calc.inputValue);
			StringBuffer sb = new StringBuffer();
			sb.append(origin);
			try {
				if (value.equals(".")) {
					// 不做转换
				} else {
					value = Double.toString(Double.valueOf(value));
				}
			} catch (Exception e) {
				Log.w("saick", e.toString());
				value = "";
			}
			
			sb.append(value);
			current = sb.toString();
		} else {
			// 切换到输入状态
			if (calc.state == CalcState.INIT) {
				calc.state = CalcState.INPUT1;
			} else if (calc.state == CalcState.RESULT
					|| calc.state == CalcState.ACTIONREADY) {
				calc.state = CalcState.INPUT2;
			} else {
				// 其实状态统计为 Input1
				calc.state = CalcState.INPUT1;
			}
			current = value;
		}

		try {
			calc.inputValue = Double.valueOf(current);
			calc.currentValue = Double.valueOf(current);
		} catch (Exception e) {
			Log.w("saick", e.toString());
		} finally {

		}
		return CalcCore.currentOutput(calc.currentValue);
	}

	/**
	 * @return 可显示的当前值
	 */
	public static String currentOutput() {
		CalcCore calc = CalcCore.getInstance();
		return CalcCore.currentOutput(calc.currentValue);
	}

	public static String currentOutput(double d) {
		DecimalFormat decimalFormat = new DecimalFormat(
				"###################.###########");
		// return decimalFormat.format(CalcCore.getInstance().currentValue);
		return decimalFormat.format(d);
	}

	/**
	 * 判断当前是否需要删除，如果不需要的话，显示 AC
	 * 
	 * @return 是否需要删除
	 */
	public static boolean isNeedDelete() {
		CalcCore calc = CalcCore.getInstance();
		if (calc.state == CalcState.INPUT1 || calc.state == CalcState.INPUT2) {
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

		if (calc.state == CalcState.INPUT1) {
			calc.state = CalcState.INIT;
		} else if (calc.state == CalcState.INPUT2) {
			calc.state = CalcState.ACTIONREADY;
		} else {
			// exception
			Log.w("saick", "清除状态异常 state:" + calc.state);
		}

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
		calc.state = CalcState.INIT;
		// calc.history.clear();
		return CalcCore.currentOutput(calc.currentValue);
	}
}
