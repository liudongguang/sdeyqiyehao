package com.weixin.vo;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResponseStream {
	private String contentType;
	private InputStream in;
	private byte[] buffer;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStream getIn() {
		return new ByteArrayInputStream(buffer);
	}

	public void setIn(InputStream in) {
		try {
			this.buffer = IOUtils.toByteArray(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.in = in;
	}

	@Override
	public String toString() {
		return "ResponseStream [contentType=" + contentType + ", in=" + in + "]";
	}

}
