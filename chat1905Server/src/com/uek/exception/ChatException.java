package com.uek.exception;

/**
 * 异常时java还有一些别的编程语言重要组成部分
 * 
 * 异常的体系结构
 * 		throwable
 * 				error
 * 				exception
 * 						IOExcetpion--------------可能不是程序问题，比如文件挪动位置就找不到了，这种需要制定应急预案 需要try catch
 * 						SQLException
 * 						RuntimeExcetion-------是比较低级的异常 无需提前捕获
 * 								NullPointException
 * 								ClassCastException
 * 								ArrayLIndexOutOftException
 * 								ClassNotFountException
 * 								....
 * 
 * @author 86139
 *
 */
public class ChatException extends RuntimeException{

	//alt shift s 从父类superclass产生构造器constructor
	public ChatException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ChatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ChatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
