package com.epoint.excel.converters.inputstream;

import java.io.IOException;
import java.io.InputStream;

import com.epoint.excel.converters.Converter;
import com.epoint.excel.enums.CellDataTypeEnum;
import com.epoint.excel.metadata.CellData;
import com.epoint.excel.metadata.GlobalConfiguration;
import com.epoint.excel.metadata.property.ExcelContentProperty;
import com.epoint.excel.util.IoUtils;

/**
 * File and image converter
 *
 * @author Jiaju Zhuang
 */
public class InputStreamImageConverter implements Converter<InputStream> {
    @Override
    public Class supportJavaTypeKey() {
        return InputStream.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.IMAGE;
    }

    @Override
    public InputStream convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        throw new UnsupportedOperationException("Cannot convert images to input stream");
    }

    @Override
    public CellData convertToExcelData(InputStream value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) throws IOException {
        return new CellData(IoUtils.toByteArray(value));
    }

}
