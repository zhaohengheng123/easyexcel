package com.epoint.excel.converters.shortconverter;

import java.math.BigDecimal;

import com.epoint.excel.converters.Converter;
import com.epoint.excel.enums.CellDataTypeEnum;
import com.epoint.excel.metadata.CellData;
import com.epoint.excel.metadata.GlobalConfiguration;
import com.epoint.excel.metadata.property.ExcelContentProperty;

/**
 * Short and number converter
 *
 * @author Jiaju Zhuang
 */
public class ShortNumberConverter implements Converter<Short> {

    @Override
    public Class supportJavaTypeKey() {
        return Short.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public Short convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return cellData.getNumberValue().shortValue();
    }

    @Override
    public CellData convertToExcelData(Short value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return new CellData(new BigDecimal(Short.toString(value)));
    }

}
