package com.jihen.ordermanagement.api;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Exposes a lightweight HTTP endpoint for checking application responsiveness.
 * This endpoint does not verify downstream dependencies.
 */
public class HealthController {
    @GetMapping("/api/v1/health")
    /**
     * Returns a basic application status response.
     *
     * @return a map containing the status value {@code UP}
     */
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
