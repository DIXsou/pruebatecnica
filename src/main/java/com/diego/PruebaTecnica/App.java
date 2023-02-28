package com.diego.PruebaTecnica;

import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.diego.PruebaTecnica.DAO.ProductDAO;
import com.diego.PruebaTecnica.DAO.imp.ProductDaoCsvImp;
import com.diego.PruebaTecnica.Manager.ProductManager;
import com.diego.PruebaTecnica.Objects.Product;

public class App {
	private static final Logger LOG = LogManager.getLogger(App.class);
	
    public static void main( String[] args ) {
    	BasicConfigurator.configure();
    	LOG.info("Start Program");
    	
    	ProductDAO productDAO = new ProductDaoCsvImp();
    	
    	ArrayList<Product> products = productDAO.readAll();
    	
    	products = ProductManager.filterProductList(products);
    	ProductManager.sortProductList(products);
    	
    	LOG.info("Products: " + products);
    	

    	LOG.info("End Program");
    	
    }
}
