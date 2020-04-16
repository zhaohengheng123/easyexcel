package com.epoint.excel.event;

import java.util.ArrayList;
import java.util.List;

import com.epoint.excel.context.AnalysisContext;

/**
 * Synchronous data reading
 *
 * @author Jiaju Zhuang
 */
public class SyncReadListener extends AnalysisEventListener<Object> {
    private List<Object> list = new ArrayList<Object>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        list.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {}

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
