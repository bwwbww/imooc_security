package com.bww.async;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

   @Autowired
   private MockQueue mockQueue;

   @Autowired
   private DeferredResultHolder deferredResultHolder;

   private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("..........eeeeee....");
        new Thread(()->{
          while (true){
              if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                  String orderNumber = mockQueue.getCompleteOrder();
                  logger.info("返回订单处理结果:"+orderNumber);
                  DeferredResult<String> deferredResult = deferredResultHolder.getMap().get(orderNumber);
                  System.out.println(deferredResult);
                  deferredResult.setResult("success");
                  mockQueue.setCompleteOrder(null);
              }else {
                  logger.info("wait....:");
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        }).start();
    }
}
