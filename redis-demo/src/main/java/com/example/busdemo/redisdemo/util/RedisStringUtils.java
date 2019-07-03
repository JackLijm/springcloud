/**
 * <p>文件名称: RedisStringUtils.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>
 * <p>公    司: 金证财富南京科技有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2019/6/26 14:48 </p>
 * <p>完成日期：</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 *
 * @version 1.0
 * @author lijm@szkingdom.com
 */

package com.example.busdemo.redisdemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisStringUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setKey(String key, Object value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public String getKey(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return (String) valueOperations.get(key);
    }
}
