package com.epoint.excel.analysis.v07.handlers;

import org.xml.sax.Attributes;

import com.epoint.excel.constant.ExcelXmlConstants;
import com.epoint.excel.context.xlsx.XlsxReadContext;
import com.epoint.excel.enums.CellExtraTypeEnum;
import com.epoint.excel.metadata.CellExtra;
import com.epoint.excel.util.StringUtils;

/**
 * Cell Handler
 *
 * @author Jiaju Zhuang
 */
public class MergeCellTagHandler extends AbstractXlsxTagHandler {

    @Override
    public boolean support(XlsxReadContext xlsxReadContext) {
        return xlsxReadContext.readWorkbookHolder().getExtraReadSet().contains(CellExtraTypeEnum.MERGE);
    }

    @Override
    public void startElement(XlsxReadContext xlsxReadContext, String name, Attributes attributes) {
        String ref = attributes.getValue(ExcelXmlConstants.ATTRIBUTE_REF);
        if (StringUtils.isEmpty(ref)) {
            return;
        }
        CellExtra cellExtra = new CellExtra(CellExtraTypeEnum.MERGE, null, ref);
        xlsxReadContext.readSheetHolder().setCellExtra(cellExtra);
        xlsxReadContext.analysisEventProcessor().extra(xlsxReadContext);
    }

}
