package message.service;

import com.game.util.file.FileUtil;

import java.io.InputStream;
import java.util.List;

/**
 * @author caochaojie
 * 2018/12/17 17:02
 */
public class ParseService {
    private static final ParseService INSTANCE = new ParseService();

    private ParseService() {

    }

    public ParseService getInstance() {
        return INSTANCE;
    }

    private void parse(String fileDir) {
        List<String> allFile = FileUtil.listFileName(fileDir, ".xml", false);
        if (allFile != null) {
            for (String file : allFile) {
                SAXReader saxReader = new SAXReader();
                List<ConfigDataContainer> ret = null;
                try (InputStream inputStreamByFileName = FileUtil.findInputStreamByFileName(file)) {

                } finally {

                }


            }
        }
    }
