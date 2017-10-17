package com.weixin.vo.httprs;

import java.util.List;

public class Extattr {
	private List<String> attrs;

	public List<String> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<String> attrs) {
		this.attrs = attrs;
	}

	@Override
	public String toString() {
		return "Extattr [attrs=" + attrs + "]";
	}

}
