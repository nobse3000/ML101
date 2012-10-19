package com.manunitder.cn.Model;

import java.io.Serializable;

public class MLInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String mlName;		//方式名字
	private String mlContent;	//方式内容
	private String mlDiffcult;			//方式难度系数 

	@Override
	public String toString() {
		return "MLInfo [id=" + id + ", mlContent=" + mlContent 
				+ ", mlName="+ mlName + ", mlDiffcult=" + mlDiffcult + "]";
	}
	public MLInfo() {
		super();
	}
	public MLInfo(String id, String mlName, 
			String mlContent,String mlDiffcult) {
		super();
		this.id = id;
		this.mlName = mlName;
		this.mlContent = mlContent;
		this.mlDiffcult = mlDiffcult;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMlName() {
		return mlName;
	}
	public void setMlName(String mlName) {
		this.mlName = mlName;
	}
	public String getMlContent() {
		return mlContent;
	}
	public void setMlContent(String mlContent) {
		this.mlContent = mlContent;
	}
	public String getMlDiffcult() {
		return mlDiffcult;
	}
	public void setMlDiffcult(String mlDiffcult) {
		this.mlDiffcult = mlDiffcult;
	}
}
