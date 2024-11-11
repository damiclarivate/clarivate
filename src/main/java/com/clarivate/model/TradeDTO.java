package com.clarivate.model;

import lombok.Data;

/**
 * Encapsulates data provided in HTTP request
 */

@Data
public class TradeDTO {
    String securityType;
    String transactionType;
    int quantity;
    double price;
    // Timestamp is actually not needed by the application as per the requirements
    String tradeTime;
}
