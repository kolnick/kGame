package com.game.encrypt;

import com.game.util.encrypt.EncryptUtil;
import org.junit.Test;

/**
 * @Author: caochaojie
 * @Date: 2019/5/4
 */
public class EncryptTest {

    @Test
    public void md5() {
        String ddddaaa = EncryptUtil.md5("ddddaaa");
        System.out.println(ddddaaa);
    }
}
