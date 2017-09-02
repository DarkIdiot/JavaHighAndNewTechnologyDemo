package com.dark.subpub;
/**  
 * @Description: 发布者接口 
 * @author: darkidiot
 * @date: 2016年9月29日 下午5:07:20 
 */  
public interface IPublisher<M> {    
      
    /**  
     * @Description: 向订阅器发布消息    
     * @param subscribePublish 订阅器 
     * @param message 消息 
     * @param isInstantMsg  是否立即发送   
     * @author: darkidiot  
     * @date: 2016年9月29日 下午5:08:52 
     */  
    public void publish(SubscribePublish subscribePublish,M message,boolean isInstantMsg);  
}  