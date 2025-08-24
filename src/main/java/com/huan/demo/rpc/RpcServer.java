package com.huan.demo.rpc;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!local")
public class RpcServer {

    @RabbitListener(queues = "rpc_queue")
    public RpcMessage processMessage(RpcMessage message) {
        System.out.println(" [x] Received request for " + message.getMessage());
        String responseMessage = "Response to " + message.getMessage();
        return new RpcMessage(responseMessage);
    }
}
