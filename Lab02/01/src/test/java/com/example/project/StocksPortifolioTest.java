package com.example.project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StocksPortifolioTest {
    @Mock IStockMarketService mockMarket;
    @InjectMocks StocksPortifolio portfolio;
    @Test
    void getTotalValueTest() {
        portfolio.addStock(new Stock("Apple", 2));
        portfolio.addStock(new Stock("Blackberry", 5));
        when(mockMarket.lookUpPrice("Apple")).thenReturn(4.0);
        when(mockMarket.lookUpPrice("Blackberry")).thenReturn(2.0);
        double correctTotal = 2*4.0 + 5*2.0;
        //assertEquals(portfolio.getTotalValue(),correctTotal);
        assertTrue(portfolio.getTotalValue() == correctTotal);
        verify(mockMarket, times(2)).lookUpPrice(Mockito.anyString());
    }
}