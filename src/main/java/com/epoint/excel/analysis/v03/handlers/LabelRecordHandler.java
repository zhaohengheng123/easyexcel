package com.epoint.excel.analysis.v03.handlers;

import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.Record;

import com.epoint.excel.analysis.v03.IgnorableXlsRecordHandler;
import com.epoint.excel.context.xls.XlsReadContext;
import com.epoint.excel.enums.RowTypeEnum;
import com.epoint.excel.metadata.CellData;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class LabelRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {
    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        LabelRecord lrec = (LabelRecord)record;
        String data = lrec.getValue();
        if (data != null && xlsReadContext.currentReadHolder().globalConfiguration().getAutoTrim()) {
            data = data.trim();
        }
        xlsReadContext.xlsReadSheetHolder().getCellMap().put((int)lrec.getColumn(),
            CellData.newInstance(data, lrec.getRow(), (int)lrec.getColumn()));
        xlsReadContext.xlsReadSheetHolder().setTempRowType(RowTypeEnum.DATA);
    }
}