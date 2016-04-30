package org.osgichina.petstore.bootstrap.actionhandler;

import java.util.Map;

/**
 * ActionHandlerMap��Ĭ��ʵ��
 * @author chris
 *
 */
public class DefaultActionHandlerMap implements ActionHandlerMap {
	private Map<String, ActionHandler> actionHandlerMap;
	
	public void setActionHandlerMap(Map<String, ActionHandler> actionHandlerMap) {
		this.actionHandlerMap = actionHandlerMap;
	}

	public Map<String, ActionHandler> getActionHandlerMap() {
		return actionHandlerMap;
	}

}
