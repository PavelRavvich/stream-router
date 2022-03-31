package com.example.streamrouter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    private String symbol;

    private PositionType type;

    private Boolean isBlocked;

    private BigDecimal leverage;

    private BigDecimal stopLoss;

    private BigDecimal takeProfit;

    private LocalDateTime createdDate;

    public enum PositionType {
        LONG, SHORT
    }
}
