package com.example.project;
import java.util.ArrayList;
public class StocksPortifolio {
    private ArrayList<Stock> stocks =  new ArrayList<Stock>();
    private IStockMarketService stockmarket;

    public ArrayList<Stock> getStocks() {
        return this.stocks;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public IStockMarketService getStockmarket() {
        return this.stockmarket;
    }

    public void setStockmarket(IStockMarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    
    public StocksPortifolio(IStockMarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue(){
        double total = 0.0;
        for (Stock stock : stocks) {
            total += stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }
        return total;
    }




}