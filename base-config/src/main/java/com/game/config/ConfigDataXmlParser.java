package com.game.config;

import java.io.InputStream;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.game.util.file.FileUtil;

public class ConfigDataXmlParser {
    private static final String CONFIGDATA = "configdata";
    private static final String CONFIG = "config";
    private static final String CLAZZ = "class";
    private static final String FILE = "file";
    private static final String KEY = "key";

    private static final String CONVERT = "convert";
    private static final String CONVERTER = "converter";
    private static final String FIELD = "field";

    private static final String CONFIGCACHES = "configcaches";
    private static final String CONFIGCACHE = "configcache";

    public static List<ConfigDataContainer> parse(String path) {
        SAXReader saxReader = new SAXReader();
        List<ConfigDataContainer> ret = null;
        try {
            InputStream inputStreamByFileName = FileUtil.findInputStreamByFileName(path);
            Document document = saxReader.read(inputStreamByFileName);
            Element root = document.getRootElement();
            Iterator<Element> data = root.elementIterator(CONFIGDATA);
            ret = new ArrayList<>();
            while (data.hasNext()) {
                Element element = data.next();
                Iterator<Element> configElementIt = element.elementIterator(CONFIG);
                while (configElementIt.hasNext()) {
                    Element config = configElementIt.next();
                    String className = config.attributeValue(CLAZZ);
                    String file = config.attributeValue(FILE);
                    String key = config.attributeValue(KEY);
                    Class<?> aClass = Class.forName(className);
                    Map<String, IConverter> stringIConverterMap = parseConvert(config);
                    ConfigDataContainer container = new ConfigDataContainer(aClass, file, stringIConverterMap, key);
                    ret.add(container);
                }
            }
        } catch (DocumentException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private static Map<String, IConverter> parseConvert(Element config)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        Map<String, IConverter> converterMap = new HashMap<>();
        Iterator<Element> convertIt = config.elementIterator(CONVERT);
        while (convertIt.hasNext()) {
            Element convert = convertIt.next();
            String field = convert.attributeValue(FIELD);
            String converterClassName = convert.attributeValue(CONVERTER);
            Class<?> convertClazz = Class.forName(converterClassName);
            IConverter converter = (IConverter) convertClazz.newInstance();
            converterMap.put(field, converter);
        }
        return converterMap;
    }
}
