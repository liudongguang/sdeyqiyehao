package com.weixin.vo.assets.tongxunlu;

import java.util.ArrayList;
import java.util.List;

public class BootStrapTree {
	private Integer id;
	private Integer pid;
	private Integer level;
	private String text;
	private List<BootStrapTree> nodes = new ArrayList<>();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

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

	public List<BootStrapTree> getNodes() {
		if (nodes.size() == 0) {
			nodes = null;
		}
		return nodes;
	}

	public void setNodes(List<BootStrapTree> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "BootStrapTree [text=" + text + ", id=" + id + ", pid=" + pid + ", level=" + level + ", nodes=" + nodes
				+ "]";
	}

}
