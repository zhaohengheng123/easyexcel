package com.epoint.excel.metadata;

import com.epoint.excel.enums.HolderEnum;

/**
 *
 * Get the corresponding holder
 *
 * @author Jiaju Zhuang
 **/
public interface Holder {

    /**
     * What holder is the return
     *
     * @return Holder enum.
     */
    HolderEnum holderType();

}
