
package com.game.config.model;

import com.game.config.AbstractConfigData;

public class FunctionConfig
    extends AbstractConfigData
{

    /**
     * 
     * @编号ID
     */
    private Integer id;
    /**
     * 
     * @说明
     */
    private String desc;

    public Integer getId() {
        return	id;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public String getDesc() {
        return	desc;
    }

    public void setDesc(String desc) {
        this.desc=desc;
    }

}
