package org.osgichina.petstore.bootstrap.dal.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.core.io.Resource;

import javax.sql.DataSource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 初始化表结构，装入范例数据。
 *
 */
public class DataLoader
        implements InitializingBean {
    private static final Log log           = LogFactory.getLog(DataLoader.class);
    private boolean             autoLoad;
    private boolean             autoDrop;
    private DataSource          dataSource;
    private Resource            sqlSchemaDrop;
    private Resource            sqlSchema;
    private Resource            sqlDataLoad;

    public void setAutoLoad(boolean autoLoad) {
        this.autoLoad = autoLoad;
    }

    public void setAutoDrop(boolean autoDrop) {
        this.autoDrop = autoDrop;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setSqlDataLoad(Resource sqlDataLoader) {
        this.sqlDataLoad = sqlDataLoader;
    }

    public void setSqlSchema(Resource sqlSchema) {
        this.sqlSchema = sqlSchema;
    }

    public void setSqlSchemaDrop(Resource sqlSchemaDrop) {
        this.sqlSchemaDrop = sqlSchemaDrop;
    }

    public void createTables(boolean drop) throws SQLException {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            Statement stmt = conn.createStatement();

            // 清除所有tables，忽略错误
            if (drop) {
                try {
                    stmt.execute(getSqlScript(sqlSchemaDrop));
                } catch (SQLException e) {
                	e.printStackTrace();
                }
            }

            // 创建table和index
            stmt.execute(getSqlScript(sqlSchema));

            stmt.close();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	e.printStackTrace();
                }
            }
        }
    }

    public void loadData() throws SQLException {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            Statement stmt = conn.createStatement();

            // 初始化数据
            stmt.execute(getSqlScript(sqlDataLoad));

            stmt.close();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    private String getSqlScript(Resource scriptFile) {
        try {
            char[] buffer = new char[8192];
            int amount;
            Reader reader = new InputStreamReader(scriptFile.getInputStream(), "GBK");
            StringWriter writer = new StringWriter();
            
            while ((amount = reader.read(buffer)) >= 0) {
            	writer.write(buffer, 0, amount);
            }

            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 如果autoLoad为<code>true</code>，则初始化表并装入数据。
     */
    public void afterPropertiesSet() throws Exception {
        if (autoLoad) {
            log.info("Auto-loading data due to autoLoad=" + autoLoad + ", autoDrop=" + autoDrop);

            try {
                createTables(autoDrop);
            } catch (SQLException e) {
            	log.warn(e.getMessage());
                return; // 如果表已存在，则不需要再装入数据
            }

            loadData();
        }
    }
}
