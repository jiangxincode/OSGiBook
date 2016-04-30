package org.osgichina.petstore.util;

/**
 * WebConfigMgr的默认实现
 * @author chris
 *
 */
public class WebConfigMgrImpl implements WebConfigMgr {
	private String resourcePath;

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
}
