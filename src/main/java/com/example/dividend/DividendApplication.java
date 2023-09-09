package com.example.dividend;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

//@SpringBootApplication
public class DividendApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DividendApplication.class, args);

		try {
			Connection connection = Jsoup.connect("https://finance.yahoo.com/quote/COKE/history?period1=99100800&period2=1694217600&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true");
			Document document = connection.get();

			Elements eles = document.getElementsByAttributeValue("data-test", "historical-prices"); // html 문서에서 태그, id, 클래스 값 찾아서 사용
			Element ele = eles.get(0);

			Element tbody = ele.children().get(1); // head는 0 foot은 2
			for (Element e : tbody.children()) {
				String txt = e.text();
				// 가지고 올 데이터의 특성 반영
				if (!txt.endsWith("Dividend")) {
					continue;
				}

				String[] splits = txt.split(" ");
				String month = splits[0];
				int day = Integer.valueOf(splits[1].replace(",", ""));
				int year = Integer.valueOf(splits[2]);
				String dividend = splits[3];

				System.out.println(year + "/" + month + "/" + day + " -> " + dividend);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
