package aseca.roobinhood.api.utils;

import aseca.roobinhood.api.domain.Ticker;

import java.util.Arrays;
import java.util.List;

public class CompanyUtils {

    // Due to having problems with docker file and cvs paths
    public static List<Ticker> COMPANIES_TICKERS = Arrays.asList(
            new Ticker("AMD", "Advanced Micro Devices"),
            new Ticker("AMZN", "Amazon.com Inc."),
            new Ticker("AAPL", "Apple Inc."),
            new Ticker("KO", "Coca-Cola Company"),
            new Ticker("EA", "Electronic Arts"),
            new Ticker("GE", "General Electric"),
            new Ticker("NFLX", "Netflix Inc."),
            new Ticker("ORCL", "Oracle Corp."),
            new Ticker("TSLA", "Tesla Inc."),
            new Ticker("WU", "Western Union Co")
    );

}
