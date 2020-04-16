package com.epoint.excel.write.metadata;

import com.epoint.excel.metadata.TableStyle;
import com.epoint.excel.write.style.HorizontalCellStyleStrategy;

/**
 * table
 *
 * @author jipengfei
 */
public class WriteTable extends WriteBasicParameter {
    /**
     * Starting from 0
     */
    private Integer tableNo;
    /**
     *
     * @deprecated please use{@link HorizontalCellStyleStrategy}
     */
    @Deprecated
    private TableStyle tableStyle;

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }

    public TableStyle getTableStyle() {
        return tableStyle;
    }

    public void setTableStyle(TableStyle tableStyle) {
        this.tableStyle = tableStyle;
    }
}
