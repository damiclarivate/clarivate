package com.clarivate.service;

import com.clarivate.model.Trade;
import com.clarivate.model.TradeDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that calculates the sum of commissions for a list of trades
 */
@Service
public class TradeService {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Creates a trade object from the data transfer object provided
     *
     * @param dto The TradeDTO object.
     * @return Either a StockTrade, or BondTrade, or ForexTrade
     */
    private static Trade createTrade(TradeDTO dto) {

        // Create a subclass of Trade using the builder pattern
        Trade trade = TradeBuilder.builder()
                .quantity(dto.getQuantity())
                .securityType(dto.getSecurityType())
                .transactionType(dto.getTransactionType())
                .price(dto.getPrice())
                .build().build();
        return trade;
    }

    /**
     * Calculate sum of commissions
     *
     * @param listDTOs The list of trade DTOs provided by the controller.
     * @return Sum of all commissions.
     * @throws IllegalArgumentException When illegal data is provided.
     */
    public double getTotalCommission(List<TradeDTO> listDTOs) throws IllegalArgumentException {
        double totalCommision = 0;

        for (TradeDTO tradeDTO : listDTOs) {
            Trade trade = createTrade(tradeDTO);
            double commission = trade.calculateCommission();
            LOGGER.info("Added commission ${}", commission);
            totalCommision += commission;
        }
        return totalCommision;

    }


}
