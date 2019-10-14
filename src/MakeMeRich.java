
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class MakeMeRich {
	public static final List<String> symbols = Arrays.asList("AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
			"AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

	public static void main(String[] args) throws MalformedURLException, IOException {

		String date = "2019-10-14";
		String symbol = "IBM";
		System.out.println(highStockPriceOnCurrentDate(symbol, date));
		
		
		
		// 1. Print these symbols using a Java 8 for-each and lambdas

		symbols.stream().forEach((s) -> System.out.print(s + " "));

		// 2. Use the StockUtil class to print the price of Bitcoin

		System.out.println("\nPrice of BTC: " + StockUtil.prices.get("BTC-USD"));

		// 3. Create a new List of StockInfo that includes the stock price

		List<Double> listOfStockValues = StockUtil.prices.values().stream().collect(Collectors.toList());
		System.out.println(listOfStockValues);

		// 4. Find the highest-priced stock under $500

		double highestStockBelow500 = listOfStockValues.stream().filter(stockPrice -> stockPrice < 500)
				.sorted(Comparator.reverseOrder()).findFirst().get();
		System.out.println(
				"The highest-priced stock under $500 is " + findStockNameByPrice(StockUtil.prices, highestStockBelow500)
						+ " valued at $" + highestStockBelow500 + ".");

	}

	private static String findStockNameByPrice(Map<String, Double> prices, double highestStockBelow500) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	private static String highStockPriceOnCurrentDate(String symbol, String date)
			throws MalformedURLException, IOException {
	
		
	final String API_KEY = "FUC3B93DA2NI1FG6";
	String rootURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED";
	
	rootURL+="&symbol="+symbol;
	rootURL+="&apikey="+API_KEY;

	URL request = new URL(rootURL);
	InputStream openStream = request.openStream();
	String response = IOUtils.toString(openStream);

	JSONObject root = new JSONObject(response);
	JSONObject daily = (JSONObject) root.get("Time Series (Daily)");
	JSONObject date1 = (JSONObject) daily.get(date);
	
	String open = (String) date1.get("1. open");
	String close = (String) date1.get("4. close");
	
	return open+" is the open price and " + close+ "is the close price for " +symbol+" stock on "+date+".";
	
	}
	
}
