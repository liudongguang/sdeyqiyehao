package com.yzcx.impl.service.handler;

import com.yzcx.api.vo.yzcxdisplay.QyglVo;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.io.IOException;

public class YzcxEHcacheUtil {
    private final static YzcxEHcacheUtil ins=new YzcxEHcacheUtil();
    private static CacheManager singletonManager;
    private static Cache<String, Object> yzcxdaycache;
    public static YzcxEHcacheUtil getInstance(){
        return ins;
    }
    private YzcxEHcacheUtil(){
            singletonManager =   CacheManagerBuilder
                    .newCacheManagerBuilder()
                    .withCache(
                            "preConfigured",
                            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                    String.class, Object.class,
                                    ResourcePoolsBuilder.heap(100)).build())
                    .build(true);

        yzcxdaycache = singletonManager.getCache("preConfigured", String.class, Object.class);
    }
    public  void putDayData(String key,Object val) throws IOException {
        yzcxdaycache.put(key,val);
    }
    public  Object getDayData(String key)  {
        Object obj=yzcxdaycache.get(key);
        return obj;
    }
    public  void clearDayData()  {
         yzcxdaycache.clear();

    }
    public static void main(String[] args) throws IOException {
        YzcxEHcacheUtil instance =YzcxEHcacheUtil.getInstance();
        QyglVo qygl =new QyglVo();
        instance.putDayData("indexgetQygl_ri",qygl);
        YzcxEHcacheUtil instance2 =YzcxEHcacheUtil.getInstance();
        System.out.println(instance2.getDayData("indexgetQygl_ri"));
    }
}
