package org.osgichina.petstore.bootstrap.dal.util;

import org.hsqldb.Server;

import org.hsqldb.persist.HsqlProperties;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Properties;

/**
 * 在spring中以server模式启动hsqldb。
 *
 */
public class HsqldbServerBean implements InitializingBean, DisposableBean {
    private Properties params;
    private Server     server;

    public void setParams(Properties params) {
        this.params = params;
    }

    public void afterPropertiesSet() throws Exception {
        if ((params == null) || params.isEmpty()) {
            throw new IllegalArgumentException("missing hsqldb params");
        }

        server = new Server();

        HsqlProperties props = new HsqlProperties(params);

        server.setProperties(props);

        server.setLogWriter(null);
        server.setErrWriter(null);

        server.start();
    }

    public void destroy() throws Exception {
        if (server != null) {
            server.stop();
        }
    }
}
