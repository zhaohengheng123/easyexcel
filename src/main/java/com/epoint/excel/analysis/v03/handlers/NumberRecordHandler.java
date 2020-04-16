package com.epoint.excel.analysis.v03.handlers;

import java.math.BigDecimal;

import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;

import com.epoint.excel.analysis.v03.IgnorableXlsRecordHandler;
import com.epoint.excel.constant.BuiltinFormats;
import com.epoint.excel.context.xls.XlsReadContext;
import com.epoint.excel.enums.RowTypeEnum;
import com.epoint.excel.metadata.CellData;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class NumberRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {

    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        NumberRecord nr = (NumberRecord)record;
        CellData cellData = CellData.newInstance(BigDecimal.valueOf(nr.getValue()), nr.getRow(), (int)nr.getColumn());
        Integer dataFormat = xlsReadContext.xlsReadWorkbookHolder().getFormatTrackingHSSFListener().getFormatIndex(nr);
        cellData.setDataFormat(dataFormat);
        cellData.setDataFormatString(BuiltinFormats.getBuiltinFormat(dataFormat,
            xlsReadContext.xlsReadWorkbookHolder().getFormatTrackingHSSFListener().getFormatString(nr),
            xlsReadContext.readSheetHolder().getGlobalConfiguration().getLocale()));
        xlsReadContext.xlsReadSheetHolder().getCellMap().put((int)nr.getColumn(), cellData);
        xlsReadContext.xlsReadSheetHolder().setTempRowType(RowTypeEnum.DATA);
    }
}
