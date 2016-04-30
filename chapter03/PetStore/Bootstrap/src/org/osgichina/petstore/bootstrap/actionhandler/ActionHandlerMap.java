package org.osgichina.petstore.bootstrap.actionhandler;

import java.util.Map;

/**
 * 路径和ActionHandler对应关系的接口
 * @author chris
 *
 */
public interface ActionHandlerMap {
	Map<String/*uri*/, ActionHandler> getActionHandlerMap();
}
