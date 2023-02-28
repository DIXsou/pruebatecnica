package com.diego.PruebaTecnica.DAO.imp;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.diego.PruebaTecnica.DAO.ProductDAO;
import com.diego.PruebaTecnica.Objects.Product;
import com.diego.PruebaTecnica.Objects.Size;
import com.diego.PruebaTecnica.Readers.CsvReader;
import com.diego.PruebaTecnica.Singleton.Properties;

public class ProductDaoCsvImp implements ProductDAO {
	private static final Logger LOG = LogManager.getLogger(CsvReader.class);
	
	@Override
	public ArrayList<Product> readAll() {
		Properties properties = Properties.getInstance();
		CsvReader reader = new CsvReader(',');
		ArrayList<Product> finalProducts = new ArrayList<>();
		
		try {
			ArrayList<String[]> csvProducts = reader.read(properties.URL_PRODUCT_CSV);
			for (String[] lineProduct : csvProducts) {
				Product newProduct = new Product(Integer.parseInt(lineProduct[0].trim()), Integer.parseInt(lineProduct[1].trim()));
				finalProducts.add(newProduct);
			}
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			LOG.error("Error read the product file (Path=" + properties.URL_PRODUCT_CSV + ")", e.fillInStackTrace());
			return null;
		}
		
		HashMap<Integer, Integer> stockMap = new HashMap<>();
		try {
			ArrayList<String[]> csvStock = reader.read(properties.URL_STOCK_CSV);
			for (String[] lineSize : csvStock) {
				stockMap.put(Integer.parseInt(lineSize[0].trim()), Integer.parseInt(lineSize[1].trim()));
			}
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			LOG.error("Error read the stock file (Path=" + properties.URL_STOCK_CSV + ")", e.fillInStackTrace());
			return null;
		}
		
		try {
			ArrayList<String[]> csvSize = reader.read(properties.URL_SIZE_CSV);
			for (String[] lineSize : csvSize) {
				int stock = 0;
				if (stockMap.containsKey(Integer.parseInt(lineSize[0].trim()))) {
					stock = stockMap.get(Integer.parseInt(lineSize[0].trim()));
				}
				Size newSize = new Size(Integer.parseInt(lineSize[0].trim()), stock , Boolean.parseBoolean(lineSize[2].trim()), Boolean.parseBoolean(lineSize[3].trim()));
				int idProduct = Integer.parseInt(lineSize[1].trim());
				int index = finalProducts.indexOf(new Product(idProduct, 0));
				finalProducts.get(index).setSize(newSize);
			}
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			LOG.error("Error read the size file (Path=" + properties.URL_SIZE_CSV + ")", e.fillInStackTrace());
			return null;
		}
		return finalProducts;
	}
	
}
