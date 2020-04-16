package com.epoint.excel.analysis;

import java.util.List;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epoint.excel.analysis.v07.XlsxSaxAnalyser;
import com.epoint.excel.context.AnalysisContext;
import com.epoint.excel.context.xlsx.DefaultXlsxReadContext;
import com.epoint.excel.context.xlsx.XlsxReadContext;
import com.epoint.excel.exception.ExcelAnalysisException;
import com.epoint.excel.exception.ExcelAnalysisStopException;
import com.epoint.excel.read.metadata.ReadSheet;
import com.epoint.excel.read.metadata.ReadWorkbook;
import com.epoint.excel.read.metadata.holder.ReadWorkbookHolder;
import com.epoint.excel.read.metadata.holder.xls.XlsReadWorkbookHolder;
import com.epoint.excel.read.metadata.holder.xlsx.XlsxReadWorkbookHolder;
import com.epoint.excel.support.ExcelTypeEnum;
import com.epoint.excel.util.CollectionUtils;
import com.epoint.excel.util.DateUtils;
import com.epoint.excel.util.FileUtils;
import com.epoint.excel.util.NumberDataFormatterUtils;
import com.epoint.excel.util.StringUtils;

/**
 * @author jipengfei
 */
public class ExcelAnalyserImpl implements ExcelAnalyser
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelAnalyserImpl.class);

    private AnalysisContext analysisContext;

    private ExcelReadExecutor excelReadExecutor;
    /**
     * Prevent multiple shutdowns
     */
    private boolean finished = false;

    public ExcelAnalyserImpl(ReadWorkbook readWorkbook) {
        try {
            choiceExcelExecutor(readWorkbook);
        }
        catch (RuntimeException e) {
            finish();
            throw e;
        }
        catch (Throwable e) {
            finish();
            throw new ExcelAnalysisException(e);
        }
    }

    private void choiceExcelExecutor(ReadWorkbook readWorkbook) throws Exception {
        ExcelTypeEnum excelType = ExcelTypeEnum.valueOf(readWorkbook);
        switch (excelType) {
            case XLSX:
                XlsxReadContext xlsxReadContext = new DefaultXlsxReadContext(readWorkbook, ExcelTypeEnum.XLSX);
                analysisContext = xlsxReadContext;
                excelReadExecutor = new XlsxSaxAnalyser(xlsxReadContext, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void analysis(List<ReadSheet> readSheetList, Boolean readAll) {
        try {
            if (!readAll && CollectionUtils.isEmpty(readSheetList)) {
                throw new IllegalArgumentException("Specify at least one read sheet.");
            }
            analysisContext.readWorkbookHolder().setParameterSheetDataList(readSheetList);
            analysisContext.readWorkbookHolder().setReadAll(readAll);
            try {
                excelReadExecutor.execute();
            }
            catch (ExcelAnalysisStopException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Custom stop!");
                }
            }
        }
        catch (RuntimeException e) {
            finish();
            throw e;
        }
        catch (Throwable e) {
            finish();
            throw new ExcelAnalysisException(e);
        }
    }

    @Override
    public void finish() {
        if (finished) {
            return;
        }
        finished = true;
        if (analysisContext == null || analysisContext.readWorkbookHolder() == null) {
            return;
        }
        ReadWorkbookHolder readWorkbookHolder = analysisContext.readWorkbookHolder();

        Throwable throwable = null;

        try {
            if (readWorkbookHolder.getReadCache() != null) {
                readWorkbookHolder.getReadCache().destroy();
            }
        }
        catch (Throwable t) {
            throwable = t;
        }
        try {
            if ((readWorkbookHolder instanceof XlsxReadWorkbookHolder)
                    && ((XlsxReadWorkbookHolder) readWorkbookHolder).getOpcPackage() != null) {
                ((XlsxReadWorkbookHolder) readWorkbookHolder).getOpcPackage().revert();
            }
        }
        catch (Throwable t) {
            throwable = t;
        }
        try {
            if ((readWorkbookHolder instanceof XlsReadWorkbookHolder)
                    && ((XlsReadWorkbookHolder) readWorkbookHolder).getPoifsFileSystem() != null) {
                ((XlsReadWorkbookHolder) readWorkbookHolder).getPoifsFileSystem().close();
            }
        }
        catch (Throwable t) {
            throwable = t;
        }
        try {
            if (analysisContext.readWorkbookHolder().getAutoCloseStream()
                    && readWorkbookHolder.getInputStream() != null) {
                readWorkbookHolder.getInputStream().close();
            }
        }
        catch (Throwable t) {
            throwable = t;
        }
        try {
            if (readWorkbookHolder.getTempFile() != null) {
                FileUtils.delete(readWorkbookHolder.getTempFile());
            }
        }
        catch (Throwable t) {
            throwable = t;
        }

        clearEncrypt03();

        removeThreadLocalCache();

        if (throwable != null) {
            throw new ExcelAnalysisException("Can not close IO.", throwable);
        }
    }

    private void removeThreadLocalCache() {
        NumberDataFormatterUtils.removeThreadLocalCache();
        DateUtils.removeThreadLocalCache();
    }

    private void clearEncrypt03() {
        if (StringUtils.isEmpty(analysisContext.readWorkbookHolder().getPassword())
                || !ExcelTypeEnum.XLS.equals(analysisContext.readWorkbookHolder().getExcelType())) {
            return;
        }
        Biff8EncryptionKey.setCurrentUserPassword(null);
    }

    @Override
    public ExcelReadExecutor excelExecutor() {
        return excelReadExecutor;
    }

    @Override
    public AnalysisContext analysisContext() {
        return analysisContext;
    }
}
