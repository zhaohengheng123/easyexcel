package com.epoint.excel.converters.byteconverter;

import java.math.BigDecimal;

import com.epoint.excel.converters.Converter;
import com.epoint.excel.enums.CellDataTypeEnum;
import com.epoint.excel.metadata.CellData;
import com.epoint.excel.metadata.GlobalConfiguration;
import com.epoint.excel.metadata.property.ExcelContentProperty;

/**
 * Byte and number converter
 *
 * @author Jiaju Zhuang
 */
public class ByteNumberConverter implements Converter<Byte> {

    @Override
    public Class supportJavaTypeKey() {
        return Byte.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public Byte convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return cellData.getNumberValue().byteValue();
    }

    @Override
    public CellData convertToExcelData(Byte value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return new CellData(new BigDecimal(Byte.toString(value)));
    }

}