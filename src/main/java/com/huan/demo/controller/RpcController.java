package com.huan.demo.controller;

import com.huan.demo.rpc.RpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpcController {

    private final RpcClient rpcClient;

    @Autowired
    public RpcController(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    @GetMapping("/rpc/send")
    public String sendRpcMessage(@RequestParam String message) {
        String response = rpcClient.sendMessage(message);
        return "RPC Response: " + response;
    }
}
