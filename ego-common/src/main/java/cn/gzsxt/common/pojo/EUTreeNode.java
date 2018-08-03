package cn.gzsxt.common.pojo;

public class EUTreeNode {
	private Long id;
	
	private String text;
	
	private String state;//节点状态 open表示叶子节点 closed 表示关闭

	public EUTreeNode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
}
