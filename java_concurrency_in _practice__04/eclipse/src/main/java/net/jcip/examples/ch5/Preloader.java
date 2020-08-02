package net.jcip.examples.ch5;

import java.util.concurrent.*;
import net.jcip.examples.ch5.LaunderThrowable;
/**
 * Preloader
 *
 * Using FutureTask to preload data that is needed later
 *
 * @author Brian Goetz and Tim Peierls
 */

public class Preloader {
    ProductInfo loadProductInfo() throws DataLoadException {
    	ProductInfo productInfo = new SilverProductInfo();
    	productInfo.getProductStock();
        return productInfo;
    }

    private final FutureTask<ProductInfo> future =
        new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
            public ProductInfo call() throws DataLoadException {
                return loadProductInfo();
            }
        });
    private final Thread thread = new Thread(future);

    public void start() { thread.start(); }

    public ProductInfo get()
            throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException)
                throw (DataLoadException) cause;
            else
                throw LaunderThrowable.launderThrowable(cause);
        }
    }

    interface ProductInfo {
    	public String getProductStock();
    }
    
    class SilverProductInfo implements ProductInfo{
    	String name = "Silver";
    	String stockPrice = null;
		@Override
		public String getProductStock() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stockPrice = "Stock Price: 10";
			return "Stock Price: 10";
		}
		@Override
		public String toString() {
			return "SilverProductInfo [name=" + name + ", stockPrice=" + stockPrice + "]";
		}
    }
    
    public static void main(String[] args) throws DataLoadException, InterruptedException {
		Preloader preloader = new Preloader();
		preloader.start();
		ProductInfo productInfo = preloader.get();
		System.out.println(productInfo);
	}
}


class DataLoadException extends Exception { }
