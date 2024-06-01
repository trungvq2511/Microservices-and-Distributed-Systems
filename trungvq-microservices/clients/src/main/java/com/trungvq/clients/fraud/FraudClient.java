package com.trungvq.clients.fraud;

import com.trungvq.clients.fraud.response.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "fraud",
        path = "api/v1/fraud-check")
public interface FraudClient {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
