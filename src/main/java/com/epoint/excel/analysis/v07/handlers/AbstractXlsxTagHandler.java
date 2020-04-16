package com.epoint.excel.analysis.v07.handlers;

import org.xml.sax.Attributes;

import com.epoint.excel.context.xlsx.XlsxReadContext;

/**
 * Abstract tag handler
 *
 * @author Jiaju Zhuang
 */
public abstract class AbstractXlsxTagHandler implements XlsxTagHandler {
    @Override
    public boolean support(XlsxReadContext xlsxReadContext) {
        return true;
    }

    @Override
    public void startElement(XlsxReadContext xlsxReadContext, String name, Attributes attributes) {

    }

    @Override
    public void endElement(XlsxReadContext xlsxReadContext, String name) {

    }

    @Override
    public void characters(XlsxReadContext xlsxReadContext, char[] ch, int start, int length) {

    }
}
