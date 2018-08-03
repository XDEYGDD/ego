package cn.gzsxt.common.pojo;
/**
 * 上传结果类型
 * @author 47097
 *
 */
public class PictureResult {
	private Integer error;
	
	private String url;
	
	private String message;

	public PictureResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
