package com.epoint.excel.context.xls;

import com.epoint.excel.context.AnalysisContextImpl;
import com.epoint.excel.read.metadata.ReadWorkbook;
import com.epoint.excel.read.metadata.holder.xls.XlsReadSheetHolder;
import com.epoint.excel.read.metadata.holder.xls.XlsReadWorkbookHolder;
import com.epoint.excel.support.ExcelTypeEnum;

/**
 *
 * A context is the main anchorage point of a ls xls reader.
 *
 * @author Jiaju Zhuang
 */
public class DefaultXlsReadContext extends AnalysisContextImpl implements XlsReadContext {

    public DefaultXlsReadContext(ReadWorkbook readWorkbook, ExcelTypeEnum actualExcelType) {
        super(readWorkbook, actualExcelType);
    }

    @Override
    public XlsReadWorkbookHolder xlsReadWorkbookHolder() {
        return (XlsReadWorkbookHolder)readWorkbookHolder();
    }

    @Override
    public XlsReadSheetHolder xlsReadSheetHolder() {
        return (XlsReadSheetHolder)readSheetHolder();
    }
}
