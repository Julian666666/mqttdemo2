package com.zfs.mqttdemo2;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @ClassName PublishSample.java
 * @Description 发布端
 * @Author 朱福盛
 * @Date 2020/5/29 9:50
 * @Version 1.0
 */
public class PublishSample {
    public static void main(String[] args) {

        String topic = "IMEI/864626043637690/TD";
//        String topic = "IMEI/868334033336706/TD";
        // 2.设置经纬度
//        byte[] content = {0x0d, (byte)0x78, (byte)0x4c, (byte)0xca, 0x01, 0x00, (byte)0x10, (byte)0x9c, (byte)0x00, (byte)0x00, (byte)0x08};
        // 3
        byte[] content = {0x05, (byte)0xff, (byte)0xff, 0x02, 0x00};
        // 4.服务器发布返回方案自动指令
//        byte[] content = {0x07};
        // 5.对时
//        byte[] content = {0x08, (byte)0x00, (byte)0x0f, (byte)0x0d, (byte)0x05, (byte)0x06, (byte)0x14};
        // 6.清除方案
//        byte[] content = {0x0f};
        // 7.每日方案
        // 00 没用天文钟
//        byte[] content = {0x0f, (byte)0x10, (byte)0x05, (byte)0x02, 0x00, 0x00, (byte)0x0e, (byte)0x0a, (byte)0x06, (byte)0x00, 0x00, 0x00, (byte)0x11, (byte)0x0a, (byte)0x02, (byte)0x00};
        // 11 日出后（没测）
//        byte[] content = {0x0f, (byte)0x10, (byte)0x06, (byte)0x02, 0x11, 0x00, (byte)0x24, (byte)0x05, (byte)0x06, (byte)0x00, 0x11, 0x00, (byte)0x26, (byte)0x05, (byte)0x02, (byte)0x00};


//        byte[] content = {0x17};
        // 1.
//        byte[] content = {(byte)0xbe, 0x0b, 0x00, 0x04, (byte)0x9f, (byte)0x9a, 0x00, 0x00, (byte)0xed, (byte)0xc5, 0x01, 0x00, 0x08, 0x00};
        // 3.下发单灯控制器场景
//        byte[] content = {(byte)0x6E, 0x06, 0x00, 0x02, 0x00, 0x01, 0x01, 0x64, 0x00};
//        byte[] content = {(byte)0x6E, 0x06, 0x00, 0x03, 0x00, 0x01, 0x00, 0x0A, 0x00};
//        byte[] content = {(byte)0x6E, 0x06, 0x00, 0x04, 0x00, 0x01, 0x01, 0x0A, 0x0a, 0x01, 0x64, 0x00};
//        byte[] content = {(byte)0x6E, 0x06, 0x00, 0x05, 0x00, 0x01, 0x01, 0x64, 0x0a};
//        byte[] content = {(byte)0x6E, 0x06, 0x00, 0x06, 0x00, 0x01, 0x01, 0x0a, 0x0a, 0x00, 0x0a, 0x00};
        // 4.清除场景
//        byte[] content = {(byte)0xc1, (byte)0xa5, (byte)0x5a};
        // 6.下发单灯手动输出操作命令
//        byte[] content = {(byte)0xbc, 0x04, 0x00, 0x01, 0x00, 0x00, 0x00};
        // 7.下发远程手动单灯场景
//        byte[] content = {(byte)0x6f, 0x02, 0x00};
//        byte[] content = {(byte)0x6f, 0x03, 0x00};
//        byte[] content = {(byte)0x6f, 0x04, 0x00};
//        byte[] content = {(byte)0x6f, 0x05, 0x00};
//        byte[] content = {(byte)0x6f, 0x06, 0x00};
        // 8.返回自动
//        byte[] content = {(byte)0xbb, 0x02, 0x00, 0x01, 0x01};
        // 10.对时
//        byte[] content = {(byte)0x08, 0x00, 0x28, 0x0d, 0x0f, 0x07, 0x14};
        // 11.日方案
//        byte[] content = {(byte)0x01, 0x04, 0x02, 0x00, 0x00, 0x34, 0x0d, 0x02, 0x00, 0x00, 0x00, 0x36, 0x0d, 0x03, 0x00};
        // 天文钟
//        byte[] content = {(byte)0x01, 0x05, 0x02, 0x14, 0x00, 0x15, 0x03, 0x02, 0x00, 0x00, 0x00, 0x16, 0x10, 0x03, 0x00};

        // 12.周方案
//        byte[] content = {(byte)0x02, 0x05, 0x03, 0x02, 0x00, 0x00, 0x0a, 0x0e, 0x02, 0x00, 0x00, 0x00, 0x0c, 0x0e, 0x03, 0x00};
        // 13.特殊日方案
//        byte[] content = {(byte)0x03, 0x06, 0x0f, 0x07, 0x14, 0x10, 0x07, 0x14, 0x02, 0x00, 0x00, 0x1b, 0x0e, 0x02, 0x00, 0x00, 0x00, 0x1d, 0x0e, 0x03, 0x00};
        // 14.清除方案
//        byte[] content = {(byte)0xc2, (byte)0xa5, 0x5a};


        int qos = 0;
        String broker = "tcp://127.0.0.1:1883";
        String userName = "admin";
        String password = "password";
        String clientId = "pubClient";
        // 内存存储
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            // 创建客户端
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 设置连接的用户名
//            connOpts.setUserName(userName);
//            connOpts.setPassword(password.toCharArray());
            // 建立连接
            sampleClient.connect(connOpts);
            // 创建消息
            MqttMessage message = new MqttMessage(content);
            // 设置消息的服务质量
            message.setQos(qos);
            // 发布消息
            sampleClient.publish(topic, message);
            // 断开连接
            sampleClient.disconnect();
            // 关闭客户端
            sampleClient.close();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
