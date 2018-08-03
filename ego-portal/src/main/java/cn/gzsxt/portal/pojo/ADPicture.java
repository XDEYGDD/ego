package cn.gzsxt.portal.pojo;

public class ADPicture {
	/**
	 * "srcB":"http://image.ego.com/images/2015/03/03/2015030304360302109345.jpg"
	 * ,"height":240
	 * ,"alt":""
	 * ,"width":670
	 * ,"src":"http://image.ego.com/images/2015/03/03/2015030304360302109345.jpg"
	 * ,"widthB":550,
	 * "href":"http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE"
	 * ,"heightB":240
	 * 
	 */

	private String scrB;
	private Integer height = 240;
	private String alt;
	private Integer width = 670;
	private String src;
	private Integer widthB;
	private String href;
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	private Integer heightB;

	public ADPicture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getScrB() {
		return scrB;
	}

	public void setScrB(String scrB) {
		this.scrB = scrB;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Integer getWidthB() {
		return widthB;
	}

	public void setWidthB(Integer widthB) {
		this.widthB = widthB;
	}

	public Integer getHeightB() {
		return heightB;
	}

	public void setHeightB(Integer heightB) {
		this.heightB = heightB;
	}

}