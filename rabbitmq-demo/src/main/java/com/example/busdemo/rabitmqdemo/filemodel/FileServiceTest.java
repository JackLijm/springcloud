/**
 * <p>文件名称: FileServiceTest.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2019/7/5 11:30 </p>
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

package com.example.busdemo.rabitmqdemo.filemodel;

import com.example.busdemo.rabitmqdemo.morConsumer.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;

public class FileServiceTest {
    /**
     * 1.创建一个与rabbitmq连接
     * 2.创建10个交换机exchange_node_i
     * 3.创建一批消费者，并启动，分别对所有队列进行监听
     * 4.循环往各个交换机中放东西，让消费者们消费，并落地文件
     * 5.关闭所有创建的连接
     * 5.把消费者的生成的文件进行合并
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        //获取连接
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        String exChangeTopic = "exchange_node_";
        //循环创建交换机
        Map<String, Channel> nodeChannel = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            //创建连接
            Channel channel = connection.createChannel();
            //创建交换机
            String exchangename = exChangeTopic + i;
            channel.exchangeDeclare(exchangename, "topic");
            nodeChannel.put(exchangename, channel);
        }

        //启动消费者
        runConsumer();
        //生产者
        for (int i = 0; i < 10000; i++) {
            String msg = "我是生产者生产的第" + i + "个产品";
            //获取数据对应的节点
            String exchangename = getNode(i);
            //放到对应的节点上
            nodeChannel.get(exchangename).basicPublish(exchangename, exchangename, null, msg.getBytes());
        }


        for (Iterator<Map.Entry<String, Channel>> it = nodeChannel.entrySet().iterator(); it.hasNext(); ) {
            Channel channel = it.next().getValue();
            channel.close();
        }
        connection.close();
        Thread.sleep(60*1000);
        new FileServiceTest().fileAdd();
    }

    private static void runConsumer() throws IOException {
        Connection connection = ConnectionUtil.getConnection();
        ExecutorService service = new ThreadPoolExecutor(10, 20, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(20));
        //生产者
        for (int i = 0; i < 10; i++) {
            service.submit(new FileConsumer(connection, i + ""));
        }
    }

    private static String getNode(int i) {
        //创建交换机
        String exchangename = "exchange_node_" + i % 10;
        return exchangename;
    }

    public void fileAdd() throws IOException {
        String mergeFilePath = "D:/test/merge.txt";
        Files.deleteIfExists(Paths.get(mergeFilePath));
        createFile(mergeFilePath);
        File mergeFile = new File(mergeFilePath);
        FileChannel mergeFileChannel = new FileOutputStream(mergeFile, true).getChannel();
        for (int i = 0; i < 10; i++) {
            FileChannel subFile = new FileInputStream(new File("D:/test/queue_node_" + i + ".txt")).getChannel();
            mergeFileChannel.transferFrom(subFile, mergeFileChannel.size(), subFile.size());
            subFile.close();
        }
        mergeFileChannel.close();
    }

    @Test
    public void test() throws IOException {
        new FileServiceTest().copy();
    }
    public void copy() throws IOException {
        String mergeFilePath = "C:\\Users\\lijm\\Desktop\\导入\\002\\20190312\\merge.txt";
        File mergeFile = new File(mergeFilePath);
        FileChannel mergeFileChannel = new FileOutputStream(mergeFile, true).getChannel();
        for (int i = 3; i < 8; i++) {
            FileChannel subFile = new FileInputStream(new File("C:\\Users\\lijm\\Desktop\\导入\\002\\20190312\\mergetxt"+i+".txt")).getChannel();
            mergeFileChannel.transferFrom(subFile, mergeFileChannel.size(), subFile.size());
            subFile.close();
        }
        mergeFileChannel.close();
    }

    /**
     * 创建文件，如果文件存在则返回
     *
     * @param filePath
     * @throws IOException
     */
    public Path createFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!path.getParent().toFile().exists()) {
            Files.createDirectories(path.getParent());
        }
        if (!Files.exists(path)) {
            return Files.createFile(path);
        } else {
            return path;
        }
    }
}
