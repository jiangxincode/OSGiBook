package org.osgichina.petstore.bootstrap.actionhandler;

import java.util.Map;

/**
 * ·����ActionHandler��Ӧ��ϵ�Ľӿ�
 * @author chris
 *
 */
public interface ActionHandlerMap {
	Map<String/*uri*/, ActionHandler> getActionHandlerMap();
}
