package com.weixin.vo.assets.qyappmanager;

import com.weixin.vo.httprs.BaseMsg;

import java.util.List;

public class GetqyAppList extends BaseMsg {
	private List<AgentVo> agentlist;

	public List<AgentVo> getAgentlist() {
		return agentlist;
	}

	public void setAgentlist(List<AgentVo> agentlist) {
		this.agentlist = agentlist;
	}

	@Override
	public String toString() {
		return "GetqyAppList [agentlist=" + agentlist + "]";
	}

}
