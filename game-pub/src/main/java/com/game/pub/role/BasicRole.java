package com.game.pub.role;

import io.protostuff.Tag;
import lombok.Data;

@Data
public class BasicRole {

    @Tag(1)
    protected long id;

    @Tag(2)
    protected String name;

    @Tag(3)
    protected int level;

    @Tag(4)
    protected int exp;

}

