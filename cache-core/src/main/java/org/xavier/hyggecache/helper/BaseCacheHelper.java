package org.xavier.hyggecache.helper;

import org.xavier.hyggecache.enums.CacheHelperType;
import org.xavier.hyggecache.enums.ImplementsType;
import org.xavier.hyggecache.operator.BaseCacheOperator;
import org.xavier.hyggecache.serializer.BaseSerializer;

/**
 * 描述信息：<br/>
 * 缓存逻辑执行核心对象基类
 *
 * @author Xavier
 * @version 1.0
 * @date 2018.11.13
 * @since Jdk 1.8
 */
public abstract class BaseCacheHelper {
    /**
     * 缓存逻辑类型
     */
    protected CacheHelperType cacheHelperType;
    /**
     * 实现类型
     */
    protected ImplementsType implementsType;

    /**
     * 序列化工具
     */
    protected BaseSerializer serializer;

    /**
     * 缓存落地操作对象
     */
    protected BaseCacheOperator cacheOperator;

    public CacheHelperType getCacheHelperType() {
        return cacheHelperType;
    }

    public void setCacheHelperType(CacheHelperType cacheHelperType) {
        this.cacheHelperType = cacheHelperType;
    }

    public ImplementsType getImplementsType() {
        return implementsType;
    }

    public void setImplementsType(ImplementsType implementsType) {
        this.implementsType = implementsType;
    }

    public BaseSerializer getSerializer() {
        return serializer;
    }

    public void setSerializer(BaseSerializer serializer) {
        this.serializer = serializer;
    }

    public BaseCacheOperator getCacheOperator() {
        return cacheOperator;
    }

    public void setCacheOperator(BaseCacheOperator cacheOperator) {
        this.cacheOperator = cacheOperator;
    }
}
