/**
 * <p>文件名称: EmailDTO.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2020/6/7 10:22 </p>
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
package com.example.busdemo.rabitmqdemo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmailDTO {
    /**
     * SMTP服务器
     */
    private String smtpService;
    /**
     * 端口号
     */
    private String smtpPort;
    /**
     * 发送邮箱
     */
    private String fromMailAddress;
    /**
     * 发送邮箱的STMP口令
     */
    private String fromMailStmpPwd;
    /**
     * 邮件标题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 内容格式（默认采用html）
     */
    private String contentType;
    /**
     * 接受邮件地址集合
     */
    private List<String> list = new ArrayList<>();
}
