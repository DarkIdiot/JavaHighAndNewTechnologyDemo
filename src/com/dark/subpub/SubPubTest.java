package com.dark.subpub;
  
/**  
 * @Description: 发布订阅测试类 
 * @author: darkidiot
 * @date: 2016年9月29日 下午5:28:53 
 */  
public class SubPubTest {  
    public static void main(String[] args) {  
        SubscribePublish<String> subscribePublish = new SubscribePublish<String>("订阅器");  
        IPublisher<String> publisher1 = new PublisherImpOne<String>("发布者1");  
        IPublisher<String> publisher2 = new PublisherImpOne<String>("发布者2");  
        ISubcriber<String> subcriber1 = new SubcriberImpOne<String>("订阅者1");  
        ISubcriber<String> subcriber2 = new SubcriberImpOne<String>("订阅者2");  
        
        subcriber1.subcribe(subscribePublish);  
        subcriber2.subcribe(subscribePublish);  
          
        publisher1.publish(subscribePublish, "welcome",true);  
        publisher1.publish(subscribePublish, "to",true);  
        publisher1.publish(subscribePublish, "yy",false);  
        publisher2.publish(subscribePublish, "welcome",true);  
        publisher2.publish(subscribePublish, "to",true);  
        publisher2.publish(subscribePublish, "yy",false);  
    }  
  
}  