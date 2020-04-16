package com.epoint.excel.write.style;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.epoint.excel.event.NotRepeatExecutor;
import com.epoint.excel.metadata.CellData;
import com.epoint.excel.metadata.Head;
import com.epoint.excel.write.handler.CellWriteHandler;
import com.epoint.excel.write.handler.SheetWriteHandler;
import com.epoint.excel.write.metadata.holder.WriteSheetHolder;
import com.epoint.excel.write.metadata.holder.WriteTableHolder;
import com.epoint.excel.write.metadata.holder.WriteWorkbookHolder;

/**
 * Cell style strategy
 *
 * @author Jiaju Zhuang
 */
public abstract class AbstractCellStyleStrategy implements CellWriteHandler, SheetWriteHandler, NotRepeatExecutor {
    boolean hasInitialized = false;

    @Override
    public String uniqueValue() {
        return "CellStyleStrategy";
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
        Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
        Head head, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
        CellData cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
        List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (isHead == null || head == null) {
            return;
        }
        if (isHead) {
            setHeadCellStyle(cell, head, relativeRowIndex);
        } else {
            setContentCellStyle(cell, head, relativeRowIndex);
        }
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        initCellStyle(writeWorkbookHolder.getWorkbook());
        hasInitialized = true;
    }

    /**
     * Initialization cell style
     *
     * @param workbook
     */
    protected abstract void initCellStyle(Workbook workbook);

    /**
     * Sets the cell style of header
     *
     * @param cell
     * @param head
     * @param relativeRowIndex
     */
    protected abstract void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex);

    /**
     * Sets the cell style of content
     *
     * @param cell
     * @param head
     * @param relativeRowIndex
     */
    protected abstract void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex);

}
