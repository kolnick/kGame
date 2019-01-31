package com.game.config;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ConfigDataManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDataManager.class);

    private Map<Class<?>, ConfigDataContainer> configContainer = new HashMap<>();

    private static ConfigDataManager INSTANCE = new ConfigDataManager();

    private ConfigDataManager() {

    }

    public static ConfigDataManager getInstance() {
        return INSTANCE;
    }

    /**
     * 加载
     *
     * @param path
     */
    public void init(String path) {
        String xmlPath = "data_config.xml";
        List<ConfigDataContainer> configData = ConfigDataXmlParser.parse(xmlPath);
        LOGGER.info("配置条数：" + configData.size());
        try {
            for (ConfigDataContainer<?> container : configData) {
                container.load(path);
                configContainer.put(container.getClazz(), container);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public <T> List getList(Class<T> clazz) {
        ConfigDataContainer configDataContainer = this.configContainer.get(clazz);
        if (configDataContainer == null) {
            return Collections.emptyList();
        }
        return configDataContainer.getList();
    }

    public <T> T getById(Class<T> clazz, Object object) {
        ConfigDataContainer configDataContainer = this.configContainer.get(clazz);
        if (configDataContainer == null) {
            return null;
        }
        return (T) configDataContainer.getCache(object);
    }


}
