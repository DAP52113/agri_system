package com.sxt.sys.domain;

public class Menu {
    private Integer id;

    private Integer pid;

    private String title;

    private String href;

    private Integer open;

    private Integer parent;

    private String icon;

    private String target;

    private Integer available;
    
    

    public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu(Integer id, Integer pid, String title, String href,
			Integer open, Integer parent, String icon, String target,
			Integer available) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.href = href;
		this.open = open;
		this.parent = parent;
		this.icon = icon;
		this.target = target;
		this.available = available;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}