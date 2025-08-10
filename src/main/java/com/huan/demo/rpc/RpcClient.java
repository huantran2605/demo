package com.huan.demo.rpc;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RpcClient {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RpcClient(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String sendMessage(String message) {
        RpcMessage rpcMessage = new RpcMessage(message);
        System.out.println(" [x] Requesting " + message);
        RpcMessage response = (RpcMessage) rabbitTemplate.convertSendAndReceive("rpc_queue", rpcMessage);
        return response != null ? response.getMessage() : "No response";
    }
}
