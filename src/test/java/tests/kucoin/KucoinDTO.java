package tests.kucoin;

import lombok.Data;

@Data
public class KucoinDTO {
    private String symbol;
    private String symbolName;
    private String buy;
    private String bestBidSize;
    private String sell;
    private String bestAskSize;
    private String changeRate;
    private String changePrice;
    private String high;
    private String low;
    private String vol;
    private String volValue;
    private String last;
    private String averagePrice;
    private String takerFeeRate;
    private String makerFeeRate;
    private String takerCoefficient;
    private String makerCoefficient;
}

