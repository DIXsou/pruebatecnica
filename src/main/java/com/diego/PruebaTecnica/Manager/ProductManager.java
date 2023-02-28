package com.diego.PruebaTecnica.Manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import com.diego.PruebaTecnica.Objects.Product;
import com.diego.PruebaTecnica.Objects.Size;

public class ProductManager {
	public static void sortProductList (ArrayList<Product> productList) {
		Collections.sort(productList);
	}
	
	public static ArrayList<Product> filterProductList (ArrayList<Product> productList) {
		ArrayList<Product> newProducList = new ArrayList<>();
		for (Product product : productList) {
			if (!hasSpecialSize(product) && hasStockOrBackSoon(product)) {
				newProducList.add(product);
			} else if ( hasStockOrBackSoonSpecial(product)) {
				newProducList.add(product);
			}
		}
		return newProducList;
	}
	
	private static boolean hasStockOrBackSoon (Product product) {
		HashSet<Size> sizes = product.getAllSizes();
		for (Size size : sizes) {
			if (size.getStock() > 0 || size.isBackSoon()) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean hasStockOrBackSoonSpecial (Product product) {
		Iterator<Size> iterSize = product.getAllSizes().iterator();
		boolean hasStockOrBackSoonSpecialSize = false;
		boolean hasStockOrBackSoonNormalSize = false;
		
		while(iterSize.hasNext() && !(hasStockOrBackSoonSpecialSize && hasStockOrBackSoonNormalSize)) {
			Size size = iterSize.next();
			if (size.getStock() > 0 || size.isBackSoon()) {
				if (size.isSpecial()) {
					hasStockOrBackSoonSpecialSize = true;
				} else {
					hasStockOrBackSoonNormalSize = true;
				}
			}
		}
		return hasStockOrBackSoonSpecialSize && hasStockOrBackSoonNormalSize;
	}
	
	private static boolean hasSpecialSize (Product product) {
		HashSet<Size> sizes = product.getAllSizes();
		for (Size size : sizes) {
			if (size.isSpecial()) {
				return true;
			}
		}
		return false;
	}
}
