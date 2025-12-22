package com.mcp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

@Component
public class SyedTools {

	private static final Logger log = LoggerFactory.getLogger(SyedTools.class);

	// tools
	@McpTool(name = "Syed-latest-videos", description = "latest vedios")
	public String getStrings() {
		var vidios = "Syed";
		return vidios;
	}

	// resources

	// prompts

}
