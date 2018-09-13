package com.hs.datatrans.utils.dimeng.utils;

import java.util.Properties;
import javax.servlet.ServletContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.web.context.ServletContextAware;

public class FrameworkConfigurer extends PropertyPlaceholderConfigurer implements ServletContextAware {
    private static final Logger LOGS = LogManager.getLogger(FrameworkConfigurer.class);
    private static Properties properties;
    private static boolean encryptSwitch = false;
    private ServletContext servletContext;

    public FrameworkConfigurer() {
    }

    public static boolean isEncryptSwitch() {
        return encryptSwitch;
    }

    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        properties = props;
        this.InitParameter();
        this.InitExtParameter();
        super.processProperties(beanFactory, props);
        encryptSwitch = Boolean.parseBoolean((String)getProperties("interface.encrypt.switch"));
    }

    public void InitExtParameter() {
    }

    public static String getUpLoadRootPath() {
        return (String)getProperties("fileStore.home");
    }

    public static Object getProperties(String key) {
        return properties.getProperty(key);
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return this.servletContext;
    }

    private void InitParameter() {
        this.setRedis(properties.getProperty("redis.host"));
        if (null != this.servletContext) {
            String retention = this.servletContext.getInitParameter("retention");
            if (StringUtils.isNotEmpty(retention) && "PRODUCTION".equals(retention)) {
                retention = this.servletContext.getInitParameter("db.jdbcUrl");
                if (StringUtils.isNotEmpty(retention)) {
                    try {
                        properties.put("db.jdbcUrl", EncryptUtil.aesDecode(retention));
                    } catch (Exception var9) {
                        LOGS.error("FrameworkConfigurer.InitParameter", var9);
                    }
                }

                retention = this.servletContext.getInitParameter("db.slave.jdbcUrl");
                if (StringUtils.isNotEmpty(retention)) {
                    try {
                        properties.put("db.slave.jdbcUrl", EncryptUtil.aesDecode(retention));
                    } catch (Exception var8) {
                        LOGS.error("FrameworkConfigurer.InitParameter", var8);
                    }
                }

                retention = this.servletContext.getInitParameter("db.dbType");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("db.dbType", retention);
                }

                retention = this.servletContext.getInitParameter("db.user");
                if (StringUtils.isNotEmpty(retention)) {
                    try {
                        properties.put("db.user", EncryptUtil.aesDecode(retention));
                    } catch (Exception var7) {
                        LOGS.error("FrameworkConfigurer.InitParameter", var7);
                    }
                }

                retention = this.servletContext.getInitParameter("db.password");
                if (StringUtils.isNotEmpty(retention)) {
                    try {
                        properties.put("db.password", EncryptUtil.aesDecode(retention));
                    } catch (Exception var6) {
                        LOGS.error("FrameworkConfigurer.InitParameter", var6);
                    }
                }

                retention = this.servletContext.getInitParameter("db.slave.user");
                if (StringUtils.isNotEmpty(retention)) {
                    try {
                        properties.put("db.slave.user", EncryptUtil.aesDecode(retention));
                    } catch (Exception var5) {
                        LOGS.error("FrameworkConfigurer.InitParameter", var5);
                    }
                }

                retention = this.servletContext.getInitParameter("db.slave.password");
                if (StringUtils.isNotEmpty(retention)) {
                    try {
                        properties.put("db.slave.password", EncryptUtil.aesDecode(retention));
                    } catch (Exception var4) {
                        LOGS.error("FrameworkConfigurer.InitParameter", var4);
                    }
                }

                retention = this.servletContext.getInitParameter("db.driverClass");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("db.driverClass", retention);
                }

                retention = this.servletContext.getInitParameter("db.dialect");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("db.dialect", retention);
                }

                retention = this.servletContext.getInitParameter("ehcache.file");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("ehcache.file", retention);
                }

                retention = this.servletContext.getInitParameter("quartz.file");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("quartz.file", retention);
                }

                retention = this.servletContext.getInitParameter("platForm_url");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("SITE_INTERFACE_URL", retention);
                }

                retention = this.servletContext.getInitParameter("mail.host");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("mail.host", retention);
                }

                retention = this.servletContext.getInitParameter("mail.port");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("mail.port", retention);
                }

                retention = this.servletContext.getInitParameter("mail.username");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("mail.username", retention);
                }

                retention = this.servletContext.getInitParameter("mail.password");
                if (StringUtils.isNotEmpty(retention)) {
                    try {
                        properties.put("mail.password", EncryptUtil.aesDecode(retention));
                    } catch (Exception var3) {
                        LOGS.error("FrameworkConfigurer.InitParameter", var3);
                    }
                }

                retention = this.servletContext.getInitParameter("database.backup.path");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("database.backup.path", retention);
                }

                retention = this.servletContext.getInitParameter("fileStore.home");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("fileStore.home", retention);
                }

                retention = this.servletContext.getInitParameter("zookeeper.address");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("zookeeper.address", retention);
                }

                retention = this.servletContext.getInitParameter("mq.namesrvAddr");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("mq.namesrvAddr", retention);
                }

                retention = this.servletContext.getInitParameter("machine.node");
                if (StringUtils.isNotEmpty(retention)) {
                    properties.put("machine.node", retention);
                }

                retention = this.servletContext.getInitParameter("redis.host");
                if (StringUtils.isNotEmpty(retention)) {
                    this.setRedis(retention);
                }
            }
        }

    }

    private void setRedis(String host) {
        if (StringUtils.isNotEmpty(host)) {
            String[] temp = host.split("\\|");
            if ("single".equals(properties.get("redis.deploy.mode"))) {
                String[] temp2 = temp[0].split("\\:");
                properties.put("redis.host", temp2[0]);
                properties.put("redis.port", temp2[1]);
            } else {
                for(int i = 0; i < temp.length; ++i) {
                    properties.put("redis.host" + i, temp[i]);
                }
            }

            if (null != this.servletContext) {
                String masterName = this.servletContext.getInitParameter("redis.master.name");
                if (StringUtils.isNoneBlank(new CharSequence[]{masterName})) {
                    properties.put("redis.master.name", masterName);
                }
            }
        }

    }

    public static void main(String[] args) {
    }
}
