package com.epoint.excel.converters.booleanconverter;

import com.epoint.excel.converters.Converter;
import com.epoint.excel.enums.CellDataTypeEnum;
import com.epoint.excel.metadata.CellData;
import com.epoint.excel.metadata.GlobalConfiguration;
import com.epoint.excel.metadata.property.ExcelContentProperty;

/**
 * Boolean and boolean converter
 *
 * @author Jiaju Zhuang
 */
public class BooleanBooleanConverter implements Converter<Boolean> {

    @Override
    public Class supportJavaTypeKey() {
        return Boolean.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.BOOLEAN;
    }

    @Override
    public Boolean convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return cellData.getBooleanValue();
    }

    @Override
    public CellData convertToExcelData(Boolean value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return new CellData(value);
    }

}
