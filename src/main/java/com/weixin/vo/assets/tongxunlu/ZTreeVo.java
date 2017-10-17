package com.weixin.vo.assets.tongxunlu;

import java.util.ArrayList;
import java.util.List;

public class ZTreeVo {
	private Integer id;
	private Integer pid;
	private Integer level;
	/////
	private Boolean open = false;
	private String name;
	private String url;
	private String target = "_blank";
	private List<ZTreeVo> children = new ArrayList<>();

	// @JsonIgnore
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// @JsonIgnore
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	// @JsonIgnore
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ZTreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<ZTreeVo> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "ZTreeVo [id=" + id + ", pid=" + pid + ", level=" + level + ", open=" + open + ", name=" + name
				+ ", url=" + url + ", target=" + target + ", children=" + children + "]";
	}

}
