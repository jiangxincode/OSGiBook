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
 * ��ʼ����ṹ��װ�뷶�����ݡ�
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

            // �������tables�����Դ���
            if (drop) {
                try {
                    stmt.execute(getSqlScript(sqlSchemaDrop));
                } catch (SQLException e) {
                	e.printStackTrace();
                }
            }

            // ����table��index
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

            // ��ʼ������
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
     * ���autoLoadΪ<code>true</code>�����ʼ����װ�����ݡ�
     */
    public void afterPropertiesSet() throws Exception {
        if (autoLoad) {
            log.info("Auto-loading data due to autoLoad=" + autoLoad + ", autoDrop=" + autoDrop);

            try {
                createTables(autoDrop);
            } catch (SQLException e) {
            	log.warn(e.getMessage());
                return; // ������Ѵ��ڣ�����Ҫ��װ������
            }

            loadData();
        }
    }
}
