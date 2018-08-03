package cn.gzsxt.rest.pojo;

import java.util.List;

public class MenuNode {
	private String u;//节点的url路径
	
	private String n;//节点的名称
	
	private List<?> i;//当前节点的子节点

	public MenuNode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public List<?> getI() {
		return i;
	}

	public void setI(List<?> i) {
		this.i = i;
	}
	
	
	
	
	
	
}
