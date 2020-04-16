package com.epoint.excel.analysis.v07.handlers;

import java.util.LinkedHashMap;

import org.xml.sax.Attributes;

import com.epoint.excel.constant.ExcelXmlConstants;
import com.epoint.excel.context.xlsx.XlsxReadContext;
import com.epoint.excel.enums.RowTypeEnum;
import com.epoint.excel.metadata.Cell;
import com.epoint.excel.read.metadata.holder.ReadRowHolder;
import com.epoint.excel.read.metadata.holder.xlsx.XlsxReadSheetHolder;
import com.epoint.excel.util.PositionUtils;

/**
 * Cell Handler
 *
 * @author jipengfei
 */
public class RowTagHandler extends AbstractXlsxTagHandler {

    @Override
    public void startElement(XlsxReadContext xlsxReadContext, String name, Attributes attributes) {
        XlsxReadSheetHolder xlsxReadSheetHolder = xlsxReadContext.xlsxReadSheetHolder();
        int rowIndex = PositionUtils.getRowByRowTagt(attributes.getValue(ExcelXmlConstants.ATTRIBUTE_R),
            xlsxReadSheetHolder.getRowIndex());
        Integer lastRowIndex = xlsxReadContext.readSheetHolder().getRowIndex();
        if (lastRowIndex != null) {
            while (lastRowIndex + 1 < rowIndex) {
                xlsxReadContext.readRowHolder(new ReadRowHolder(lastRowIndex + 1, RowTypeEnum.EMPTY,
                    xlsxReadSheetHolder.getGlobalConfiguration(), new LinkedHashMap<Integer, Cell>()));
                xlsxReadContext.analysisEventProcessor().endRow(xlsxReadContext);
                xlsxReadSheetHolder.setColumnIndex(null);
                xlsxReadSheetHolder.setCellMap(new LinkedHashMap<Integer, Cell>());
                lastRowIndex++;
            }
        }
        xlsxReadSheetHolder.setRowIndex(rowIndex);
    }

    @Override
    public void endElement(XlsxReadContext xlsxReadContext, String name) {
        XlsxReadSheetHolder xlsxReadSheetHolder = xlsxReadContext.xlsxReadSheetHolder();
        xlsxReadContext.readRowHolder(new ReadRowHolder(xlsxReadSheetHolder.getRowIndex(), RowTypeEnum.DATA,
            xlsxReadSheetHolder.getGlobalConfiguration(), xlsxReadSheetHolder.getCellMap()));
        xlsxReadContext.analysisEventProcessor().endRow(xlsxReadContext);
        xlsxReadSheetHolder.setColumnIndex(null);
        xlsxReadSheetHolder.setCellMap(new LinkedHashMap<Integer, Cell>());
    }

}
