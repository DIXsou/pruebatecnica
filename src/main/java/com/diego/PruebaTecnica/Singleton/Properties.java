package com.diego.PruebaTecnica.Singleton;

public final class Properties {
	 private static Properties instance;

    private Properties() {
    }

    public static Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
        }
        return instance;
    }

    public final String URL_PRODUCT_CSV = "src/test/testFiles/product.csv";
    public final String URL_SIZE_CSV = "src/test/testFiles/size.csv";
    public final String URL_STOCK_CSV = "src/test/testFiles/stock.csv";

    
    
}
