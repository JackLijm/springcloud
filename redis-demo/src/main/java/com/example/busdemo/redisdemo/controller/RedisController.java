/**
 * <p>文件名称: RedisController.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>
 * <p>公    司: 金证财富南京科技有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2019/6/26 14:50 </p>
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

package com.example.busdemo.redisdemo.controller;

import com.example.busdemo.redisdemo.util.RedisStringUtils;
import com.example.redisdemo.util.RedisStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    RedisStringUtils redisStringUtils;

    @RequestMapping("/redisSet")
    public String setRedisKey() {
        redisStringUtils.setKey("lijm", "你好呀，勇士");
        return "success";
    }


    @RequestMapping("/redisget")
    public String getRedisKey() {
        return redisStringUtils.getKey("lijm");
    }
}
