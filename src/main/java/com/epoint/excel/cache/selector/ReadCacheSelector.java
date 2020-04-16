package com.epoint.excel.cache.selector;

import org.apache.poi.openxml4j.opc.PackagePart;

import com.epoint.excel.cache.ReadCache;

/**
 * Select the cache
 *
 * @author Jiaju Zhuang
 **/
public interface ReadCacheSelector {

    /**
     * Select a cache
     *
     * @param sharedStringsTablePackagePart
     * @return
     */
    ReadCache readCache(PackagePart sharedStringsTablePackagePart);
}
