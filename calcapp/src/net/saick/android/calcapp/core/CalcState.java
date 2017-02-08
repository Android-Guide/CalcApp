/**
 * 
 */
package net.saick.android.calcapp.core;

/**
 * @author Eric
 *
 */
public enum CalcState {
	INIT,			// 初始状态
	INPUT1,			// 输入状态
	ACTIONREADY,	// 有操作状态
	INPUT2,			// 已有操作和数，再输入的状态
	CALC,			// 计算中
	RESULT,			// 计算结果状态
}
