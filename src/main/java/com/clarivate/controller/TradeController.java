package com.clarivate.controller;


import com.clarivate.model.TradeDTO;
import com.clarivate.service.TradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Controller class that handles incoming REST invocations.
 */
@RestController
@RequestMapping("/trades")
public class TradeController {

    private static final Logger LOGGER = LogManager.getLogger();

    // Trade service which is used to handle data for HTTP requests
    @Autowired
    private TradeService service;

    /**
     * Calculates the total commission for a list of trades.
     *
     * @param trades The list of TradeDTO trades.
     * @return Sum of commissions for all trades in this request.
     */
    @PostMapping("/calculate")
    public String calculate(@RequestBody List<TradeDTO> trades) {
        try {
            LOGGER.info("Calculating commission for {} trades", trades.size());

            // invoke service to perform calculation
            var commission = service.getTotalCommission(trades);
            String msg = String.format("Total commission for %s trades is $%f",
                    trades.size(), commission);
            LOGGER.info(msg);
            return msg;
        } catch (IllegalArgumentException e) {
            // Log exception and throw response exception
            LOGGER.error(e.getMessage());    // TODO include error message in returned string
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}