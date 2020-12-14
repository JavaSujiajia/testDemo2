package com.example.demo.core.lang;/*
package com.huare.demo.core.lang;

import com.github.pagehelper.Page;

import io.swagger.annotations.ApiModelProperty;


*/
/**
 * 统一的返回结果类型，结果必须包含code跟msg
 *
 * @author maheng
 * @param <T> 返回的数据
 * <p>
 * 失败结果
 * @param code
 * @return 成功结果
 * @param data
 * @return
 *//*

public class Result<T> implements HttpCode {
	
	@ApiModelProperty("返回的数据")
	private T data;
	
	@ApiModelProperty("返回的结果编码")
	private String code;

	@ApiModelProperty("返回的提示信息")
	private String msg;
	
	@ApiModelProperty("当前页数，不是分页查询操作时为空")
	private Integer pageNum;//当前页数
	
	@ApiModelProperty("每页记录数，不是分页查询操作时为空")
	private Integer pageSize;
	
	@ApiModelProperty("总的记录数，不是分页查询操作时为空")
	private Long total;//分页总记录数

	public Result() {

	}
	
	*/
/**
 * 失败结果
 * @param code
 * @return
 *//*

	public static <T> Result<T> error(String code,String message){
		return new Result<T>(code,message,null);
	}
	
	*/
/**
 * 成功结果
 * @param data
 * @return
 *//*

	public static <T> Result<T> ok(T data){
		return new Result<T>(SUCCESS_CODE,"操作成功",data);
	}
	
	
	public Result(String code, String msg,T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
		if(data instanceof Page){
			Page<?> p;
			this.pageNum = (p = (Page<?>)data).getPageNum();
			this.pageSize = p.getPageSize();
			this.total = p.getTotal();
		}
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	

}
*/
