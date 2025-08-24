// McpAutoReconnect.java
package com.huan.demo.mcp;

import io.modelcontextprotocol.client.McpAsyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Configuration
//@EnableScheduling
@Slf4j
public class McpAutoReconnect {

    private final List<McpAsyncClient> clients;     // <-- auto-config đã tạo, mình chỉ dùng
    private final AtomicBoolean reconnecting = new AtomicBoolean(false);

    public McpAutoReconnect(List<McpAsyncClient> clients) {
        this.clients = clients;
    }

    // Mỗi 5 giây kiểm tra; nếu SSE đứt (server restart) thì re-init
    @Scheduled(fixedDelay = 5000)
    public void heartbeat() {
        for (var c : clients) {
            try {
                c.ping(); // rất nhẹ; sẽ ném lỗi nếu SSE đã rớt
                log.info("OK.....");
            } catch (Exception e) {
                if (reconnecting.compareAndSet(false, true)) {
                    try {
                        log.warn("MCP SSE lost, reinitializing…");
                        c.initialize();
                        log.info("MCP reinitialized OK");
                    } catch (Exception ex) {
                        log.error("MCP  reinitialize failed; will retry next tick", ex);
                    } finally {
                        reconnecting.set(false);
                    }
                }
            }
        }
    }
}
