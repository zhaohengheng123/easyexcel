package com.epoint.excel.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.epoint.excel.write.metadata.style.WriteCellStyle;
import com.epoint.excel.write.metadata.style.WriteFont;

/**
 * @author jipengfei
 */
public class StyleUtil
{

    private StyleUtil() {
    }

    /**
     * @param workbook
     * @return
     */
    public static CellStyle buildDefaultCellStyle(Workbook workbook) {
        CellStyle newCellStyle = workbook.createCellStyle();
        newCellStyle.setWrapText(true);
        newCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        newCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        newCellStyle.setLocked(true);
        newCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        newCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        newCellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        newCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        newCellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        newCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        return newCellStyle;
    }

    /**
     * Build head cell style
     *
     * @param workbook
     * @param writeCellStyle
     * @return
     */
    public static CellStyle buildHeadCellStyle(Workbook workbook, WriteCellStyle writeCellStyle) {
        CellStyle cellStyle = buildDefaultCellStyle(workbook);
        if (writeCellStyle == null) {
            return cellStyle;
        }
        buildCellStyle(workbook, cellStyle, writeCellStyle, true);
        return cellStyle;
    }

    /**
     * Build content cell style
     *
     * @param workbook
     * @param writeCellStyle
     * @return
     */
    public static CellStyle buildContentCellStyle(Workbook workbook, WriteCellStyle writeCellStyle) {
        CellStyle cellStyle = workbook.createCellStyle();
        if (writeCellStyle == null) {
            return cellStyle;
        }
        buildCellStyle(workbook, cellStyle, writeCellStyle, false);
        return cellStyle;
    }

    private static short getStyle(BorderStyle style) {
        if (style.equals(BorderStyle.DASH_DOT)) {
            return XSSFCellStyle.BORDER_DASH_DOT;
        }
        else if (style.equals(BorderStyle.DASH_DOT_DOT)) {
            return (XSSFCellStyle.BORDER_DASH_DOT_DOT);
        }
        else if (style.equals(BorderStyle.DASHED)) {
            return (XSSFCellStyle.BORDER_DASHED);
        }
        else if (style.equals(BorderStyle.DOTTED)) {
            return (XSSFCellStyle.BORDER_DOTTED);
        }
        else if (style.equals(BorderStyle.DOUBLE)) {
            return (XSSFCellStyle.BORDER_DOUBLE);
        }
        else if (style.equals(BorderStyle.HAIR)) {
            return (XSSFCellStyle.BORDER_HAIR);
        }
        else if (style.equals(BorderStyle.MEDIUM)) {
            return (XSSFCellStyle.BORDER_MEDIUM);
        }
        else if (style.equals(BorderStyle.MEDIUM_DASH_DOT)) {
            return (XSSFCellStyle.BORDER_MEDIUM_DASH_DOT);
        }
        else if (style.equals(BorderStyle.MEDIUM_DASH_DOT_DOTC)) {
            return (XSSFCellStyle.BORDER_MEDIUM_DASH_DOT_DOT);
        }
        else if (style.equals(BorderStyle.MEDIUM_DASHED)) {
            return (XSSFCellStyle.BORDER_MEDIUM_DASHED);
        }
        else if (style.equals(BorderStyle.NONE)) {
            return (XSSFCellStyle.BORDER_NONE);
        }
        else if (style.equals(BorderStyle.SLANTED_DASH_DOT)) {
            return (XSSFCellStyle.BORDER_SLANTED_DASH_DOT);
        }
        else if (style.equals(BorderStyle.SLANTED_DASH_DOT)) {
            return (XSSFCellStyle.BORDER_SLANTED_DASH_DOT);
        }
        else if (style.equals(BorderStyle.THICK)) {
            return (XSSFCellStyle.BORDER_THICK);
        }
        else if (style.equals(BorderStyle.THIN)) {
            return (XSSFCellStyle.BORDER_THIN);
        }
        else {
            return (XSSFCellStyle.BORDER_NONE);
        }
    }

    private static void buildCellStyle(Workbook workbook, CellStyle cellStyle, WriteCellStyle writeCellStyle,
            boolean isHead) {
        buildFont(workbook, cellStyle, writeCellStyle.getWriteFont(), isHead);
        if (writeCellStyle.getDataFormat() != null) {
            cellStyle.setDataFormat(writeCellStyle.getDataFormat());
        }
        if (writeCellStyle.getHidden() != null) {
            cellStyle.setHidden(writeCellStyle.getHidden());
        }
        if (writeCellStyle.getLocked() != null) {
            cellStyle.setLocked(writeCellStyle.getLocked());
        }
        if (writeCellStyle.getQuotePrefix() != null) {
        }
        if (writeCellStyle.getHorizontalAlignment() != null) {
            HorizontalAlignment ali = writeCellStyle.getHorizontalAlignment();
            if (ali.equals(HorizontalAlignment.CENTER)) {
                cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            }
            else if (ali.equals(HorizontalAlignment.FILL)) {
                cellStyle.setAlignment(XSSFCellStyle.ALIGN_FILL);
            }
            else if (ali.equals(HorizontalAlignment.JUSTIFY)) {
                cellStyle.setAlignment(XSSFCellStyle.ALIGN_JUSTIFY);
            }
            else if (ali.equals(HorizontalAlignment.GENERAL)) {
                cellStyle.setAlignment(XSSFCellStyle.ALIGN_GENERAL);
            }
            else if (ali.equals(HorizontalAlignment.LEFT)) {
                cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            }
            else if (ali.equals(HorizontalAlignment.RIGHT)) {
                cellStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
            }
        }
        if (writeCellStyle.getWrapped() != null) {
            cellStyle.setWrapText(writeCellStyle.getWrapped());
        }
        if (writeCellStyle.getVerticalAlignment() != null) {
            VerticalAlignment ali = writeCellStyle.getVerticalAlignment();
            if (ali.equals(VerticalAlignment.CENTER)) {
                cellStyle.setAlignment(XSSFCellStyle.VERTICAL_CENTER);
            }
            else if (ali.equals(VerticalAlignment.JUSTIFY)) {
                cellStyle.setAlignment(XSSFCellStyle.VERTICAL_JUSTIFY);
            }
            else if (ali.equals(VerticalAlignment.BOTTOM)) {
                cellStyle.setAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
            }
            else if (ali.equals(VerticalAlignment.TOP)) {
                cellStyle.setAlignment(XSSFCellStyle.VERTICAL_TOP);
            }
        }
        if (writeCellStyle.getRotation() != null) {
            cellStyle.setRotation(writeCellStyle.getRotation());
        }
        if (writeCellStyle.getIndent() != null) {
            cellStyle.setIndention(writeCellStyle.getIndent());
        }
        if (writeCellStyle.getBorderLeft() != null) {
            BorderStyle style = writeCellStyle.getBorderLeft();
            cellStyle.setBorderLeft(getStyle(style));
        }
        if (writeCellStyle.getBorderRight() != null) {
            BorderStyle style = writeCellStyle.getBorderRight();
            cellStyle.setBorderRight(getStyle(style));
        }
        if (writeCellStyle.getBorderTop() != null) {
            BorderStyle style = writeCellStyle.getBorderTop();
            cellStyle.setBorderTop(getStyle(style));
        }
        if (writeCellStyle.getBorderBottom() != null) {
            BorderStyle style = writeCellStyle.getBorderBottom();
            cellStyle.setBorderBottom(getStyle(style));
        }
        if (writeCellStyle.getLeftBorderColor() != null) {
            cellStyle.setLeftBorderColor(writeCellStyle.getLeftBorderColor());
        }
        if (writeCellStyle.getRightBorderColor() != null) {
            cellStyle.setRightBorderColor(writeCellStyle.getRightBorderColor());
        }
        if (writeCellStyle.getTopBorderColor() != null) {
            cellStyle.setTopBorderColor(writeCellStyle.getTopBorderColor());
        }
        if (writeCellStyle.getBottomBorderColor() != null) {
            cellStyle.setBottomBorderColor(writeCellStyle.getBottomBorderColor());
        }
        if (writeCellStyle.getFillPatternType() != null) {
            FillPatternType patten = writeCellStyle.getFillPatternType();
            if (patten.equals(FillPatternType.ALT_BARS)) {
                cellStyle.setFillPattern(CellStyle.ALT_BARS);
            }
            else if (patten.equals(FillPatternType.BIG_SPOTS)) {
                cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
            }
            else if (patten.equals(FillPatternType.BRICKS)) {
                cellStyle.setFillPattern(CellStyle.BRICKS);
            }
            else if (patten.equals(FillPatternType.DIAMONDS)) {
                cellStyle.setFillPattern(CellStyle.DIAMONDS);
            }
            else if (patten.equals(FillPatternType.FINE_DOTS)) {
                cellStyle.setFillPattern(CellStyle.FINE_DOTS);
            }
            else if (patten.equals(FillPatternType.LEAST_DOTS)) {
                cellStyle.setFillPattern(CellStyle.LEAST_DOTS);
            }
            else if (patten.equals(FillPatternType.LESS_DOTS)) {
                cellStyle.setFillPattern(CellStyle.LESS_DOTS);
            }
            else if (patten.equals(FillPatternType.NO_FILL)) {
                cellStyle.setFillPattern(CellStyle.NO_FILL);
            }
            else if (patten.equals(FillPatternType.SOLID_FOREGROUND)) {
                cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
            else if (patten.equals(FillPatternType.SPARSE_DOTS)) {
                cellStyle.setFillPattern(CellStyle.SPARSE_DOTS);
            }
            else if (patten.equals(FillPatternType.SQUARES)) {
                cellStyle.setFillPattern(CellStyle.SQUARES);
            }
            else if (patten.equals(FillPatternType.THICK_BACKWARD_DIAG)) {
                cellStyle.setFillPattern(CellStyle.THICK_BACKWARD_DIAG);
            }
            else if (patten.equals(FillPatternType.THICK_FORWARD_DIAG)) {
                cellStyle.setFillPattern(CellStyle.THICK_FORWARD_DIAG);
            }
            else if (patten.equals(FillPatternType.THICK_HORZ_BANDS)) {
                cellStyle.setFillPattern(CellStyle.THICK_HORZ_BANDS);
            }
            else if (patten.equals(FillPatternType.THICK_VERT_BANDS)) {
                cellStyle.setFillPattern(CellStyle.THICK_VERT_BANDS);
            }
            else if (patten.equals(FillPatternType.THIN_BACKWARD_DIAG)) {
                cellStyle.setFillPattern(CellStyle.THIN_BACKWARD_DIAG);
            }
            else if (patten.equals(FillPatternType.THICK_FORWARD_DIAG)) {
                cellStyle.setFillPattern(CellStyle.THICK_FORWARD_DIAG);
            }
            else if (patten.equals(FillPatternType.THICK_HORZ_BANDS)) {
                cellStyle.setFillPattern(CellStyle.THICK_HORZ_BANDS);
            }
            else if (patten.equals(FillPatternType.THIN_VERT_BANDS)) {
                cellStyle.setFillPattern(CellStyle.THIN_VERT_BANDS);
            }
            else if (patten.equals(FillPatternType.THIN_VERT_BANDS)) {
                cellStyle.setFillPattern(CellStyle.THIN_VERT_BANDS);
            }
        }
        if (writeCellStyle.getFillBackgroundColor() != null) {
            cellStyle.setFillBackgroundColor(writeCellStyle.getFillBackgroundColor());
        }
        if (writeCellStyle.getFillForegroundColor() != null) {
            cellStyle.setFillForegroundColor(writeCellStyle.getFillForegroundColor());
        }
        if (writeCellStyle.getShrinkToFit() != null) {
            cellStyle.setShrinkToFit(writeCellStyle.getShrinkToFit());
        }
    }

    private static void buildFont(Workbook workbook, CellStyle cellStyle, WriteFont writeFont, boolean isHead) {
        Font font = null;
        if (isHead) {
            font = workbook.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 14);
            font.setBold(true);
            cellStyle.setFont(font);
        }
        if (writeFont == null) {
            return;
        }
        if (!isHead) {
            font = workbook.createFont();
            cellStyle.setFont(font);
        }
        if (writeFont.getFontName() != null) {
            font.setFontName(writeFont.getFontName());
        }
        if (writeFont.getFontHeightInPoints() != null) {
            font.setFontHeightInPoints(writeFont.getFontHeightInPoints());
        }
        if (writeFont.getItalic() != null) {
            font.setItalic(writeFont.getItalic());
        }
        if (writeFont.getStrikeout() != null) {
            font.setStrikeout(writeFont.getStrikeout());
        }
        if (writeFont.getColor() != null) {
            font.setColor(writeFont.getColor());
        }
        if (writeFont.getTypeOffset() != null) {
            font.setTypeOffset(writeFont.getTypeOffset());
        }
        if (writeFont.getUnderline() != null) {
            font.setUnderline(writeFont.getUnderline());
        }
        if (writeFont.getCharset() != null) {
            font.setCharSet(writeFont.getCharset());
        }
        if (writeFont.getBold() != null) {
            font.setBold(writeFont.getBold());
        }
    }

}
