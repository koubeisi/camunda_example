package com.koubeisi.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@Slf4j
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);



      /*  ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // 长轮询超时时间
                .build();

        // 订阅指定的外部任务
        client.subscribe("charge-card")
                .lockDuration(1000) // 默认锁定时间为20秒，这里修改为1秒
                .handler((externalTask, externalTaskService) -> {
                    // 将您的业务逻辑写在这

                    // 获取流程变量
                    String item = (String) externalTask.getVariable("item");
                    var amount =  externalTask.getVariable("amount");

                    log.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");

                    // 完成任务
                    externalTaskService.complete(externalTask);
                })
                .open();*/

    }

}
