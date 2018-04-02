/*******************************************************************************
 * Licensed to the OKChem
 *
 * http://www.joe.com
 *
 *******************************************************************************/
package com.joe.oauth2.exception;

/**
 * @author Joe
 *
 */
abstract class AbstractOKchemException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = -4020625169423429750L;
	private String msgCode;
	private String msg;

	/**
	 * @param msgCode
	 * @param msg
	 */
	public AbstractOKchemException(final String msgCode, final String msg) {
		super();
		this.msgCode = msgCode;
		this.msg = msg;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(final String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(final String msg) {
		this.msg = msg;
	}



}
