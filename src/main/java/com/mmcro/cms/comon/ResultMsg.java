package com.mmcro.cms.comon;

import java.io.Serializable;

/**
 *  用户前后端交互数据的结构体
 * @author zhuzg
 *
 */
public class ResultMsg  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5263594011238373608L;
	
	int result;// 处理的结果
	String  errorMsg;//错误消息
	Object data;// 返回的具体数据
	
	
	public ResultMsg(int result, String errorMsg, Object data) {
		super();
		this.result = result;
		this.errorMsg = errorMsg;
		this.data = data;
	}
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	

}
