package com.zfs.mqttdemo2;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SubscribeSample.java
 * @Description 订阅端
 * @Author 朱福盛
 * @Date 2020/5/29 9:51
 * @Version 1.0
 */
public class SubscribeSample {
    public static void main(String[] args) throws MqttException {
        String HOST = "tcp://127.0.0.1:1883";
//        String TOPIC = "IMEI/868334033327861/FD";
        String TOPIC = "IMEI/864626043637690/FD";
        int qos = 1;
        String clientid = "subClient";
        String userName = "admin";
        String passWord = "password";
        try {
            // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            MqttClient client = new MqttClient(HOST, clientid, new MemoryPersistence());
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
//            options.setUserName(userName);
            // 设置连接的密码
//            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调函数
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost");
                }

                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("topic:"+topic);
                    System.out.println("Qos:"+message.getQos());
//                    System.out.println("message content:"+new String(message.getPayload()));
                    byte[] bytes = message.getPayload();
                    if (bytes[0] == 11) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("cmdType", bytes[0]);
                        map.put("len", bytes[2] + bytes[1]);
                        map.put("CSQ", bytes[3]);
                        map.put("sceneId", bytes[5] + bytes[4]);
                        map.put("TimePlanID", bytes[6]);
                        map.put("CommType", bytes[7]);
                        map.put("outputNum", bytes[8]);
                        map.put("temperature", bytes[9]/2.5);
                        map.put("D0", bytes[10]);
                        map.put("dim", bytes[11]);
                        StringBuilder sb = new StringBuilder();
                        if (bytes[15] < 0) {
                            int a = bytes[15] + 256;
                            String s = Integer.toHexString(a);
                            sb.append(s);

                            System.out.println("15: " + a);

                        } else {
                            String s = Integer.toHexString(bytes[15]);
                            sb.append(s);
                        }
                        if (bytes[14] < 0) {
                            int a = bytes[14] + 256;
                            String s = Integer.toHexString(a);
                            sb.append(s);

                            System.out.println("14: " + a);

                        } else {
                            String s = Integer.toHexString(bytes[14]);
                            sb.append(s);
                        }
                        if (bytes[13] < 0) {
                            int a = bytes[13] + 256;
                            String s = Integer.toHexString(a);
                            sb.append(s);

                            System.out.println("13: " + a);
                        } else {
                            String s = Integer.toHexString(bytes[13]);
                            sb.append(s);
                        }
                        if (bytes[12] < 0) {
                            int a = bytes[12] + 256;
                            String s = Integer.toHexString(a);
                            sb.append(s);
                            System.out.println("12: " + a);
                        } else {
                            String s = Integer.toHexString(bytes[12]);
                            sb.append(s);
                        }
                        int i = Integer.parseInt(sb.toString(), 16);
                        Integer integer = Integer.valueOf(sb.toString(), 16);
                        Float value = Float.intBitsToFloat(integer);
                        map.put("energy1", value);

                        String vl = "";
                        if (bytes[17] < 0) {
                            int a = bytes[17] + 256;
                            String s = Integer.toHexString(a);
                            vl += s;
                        } else {
                            String s = Integer.toHexString(bytes[17]);
                            vl += s;
                        }
                        if (bytes[16] < 0) {
                            int a = bytes[16] + 256;
                            String s = Integer.toHexString(a);
                            vl += s;
                        } else {
                            String s = Integer.toHexString(bytes[16]);
                            vl += s;
                        }
                        double vol = Integer.parseInt(vl, 16) / 100.00;
                        map.put("voltage", vol);
                        map.put("current", (bytes[19] + bytes[18])/100.00);

                        String powerStr = "";
                        if (bytes[21] < 0) {
                            int a = bytes[21] + 256;
                            String s = Integer.toHexString(a);
                            powerStr += s;
                        } else {
                            String s = Integer.toHexString(bytes[21]);
                            powerStr += s;
                        }
                        if (bytes[20] < 0) {
                            int a = bytes[20] + 256;
                            String s = Integer.toHexString(a);
                            powerStr += s;
                        } else {
                            String s = Integer.toHexString(bytes[20]);
                            powerStr += s;
                        }
                        double power = Integer.parseInt(powerStr, 16) / 100.00;
                        map.put("power", power);
                        map.put("pf", (bytes[23] + bytes[22])/100.00);

                        StringBuilder lampOnTimeSB = new StringBuilder();
                        if (bytes[27] < 0) {
                            int a = bytes[27] + 256;
                            String s = Integer.toHexString(a);
                            lampOnTimeSB.append(s);
                        } else {
                            String s = Integer.toHexString(bytes[27]);
                            lampOnTimeSB.append(s);
                        }
                        if (bytes[26] < 0) {
                            int a = bytes[26] + 256;
                            String s = Integer.toHexString(a);
                            lampOnTimeSB.append(s);
                        } else {
                            String s = Integer.toHexString(bytes[26]);
                            lampOnTimeSB.append(s);
                        }
                        if (bytes[25] < 0) {
                            int a = bytes[25] + 256;
                            String s = Integer.toHexString(a);
                            lampOnTimeSB.append(s);
                        } else {
                            String s = Integer.toHexString(bytes[25]);
                            lampOnTimeSB.append(s);
                        }
                        if (bytes[24] < 0) {
                            int a = bytes[24] + 256;
                            String s = Integer.toHexString(a);
                            lampOnTimeSB.append(s);
                        } else {
                            String s = Integer.toHexString(bytes[24]);
                            lampOnTimeSB.append(s);
                        }
                        int lampOnTimeInt = Integer.parseInt(lampOnTimeSB.toString(), 16);
                        Float lampOnTime = Float.intBitsToFloat(lampOnTimeInt);
                        map.put("lampOnTime", lampOnTime);
                        System.out.println(map);

                    } else {
                        System.out.println(Arrays.toString(bytes));
                    }
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------"+ token.isComplete());
                }

            });
            client.connect(options);
            //订阅消息
            client.subscribe(TOPIC, qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
