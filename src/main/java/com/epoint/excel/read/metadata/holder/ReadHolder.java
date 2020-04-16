package com.epoint.excel.read.metadata.holder;

import java.util.List;

import com.epoint.excel.metadata.ConfigurationHolder;
import com.epoint.excel.read.listener.ReadListener;
import com.epoint.excel.read.metadata.property.ExcelReadHeadProperty;

/**
 *
 * Get the corresponding Holder
 *
 * @author Jiaju Zhuang
 **/
public interface ReadHolder extends ConfigurationHolder {
    /**
     * What handler does the currently operated cell need to execute
     *
     * @return Current {@link ReadListener}
     */
    List<ReadListener> readListenerList();

    /**
     * What {@link ExcelReadHeadProperty} does the currently operated cell need to execute
     *
     * @return Current {@link ExcelReadHeadProperty}
     */
    ExcelReadHeadProperty excelReadHeadProperty();

}
