package com.epoint.excel.analysis.v03.handlers;

import org.apache.poi.hssf.record.BoolErrRecord;
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
public class BoolErrRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {

    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        BoolErrRecord ber = (BoolErrRecord)record;
        xlsReadContext.xlsReadSheetHolder().getCellMap().put((int)ber.getColumn(),
            CellData.newInstance(ber.getBooleanValue(), ber.getRow(), (int)ber.getColumn()));
        xlsReadContext.xlsReadSheetHolder().setTempRowType(RowTypeEnum.DATA);
    }
}
