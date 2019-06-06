package com.java.eight.stream.filter.map;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

public class GroupByList {
	@Test
	public void GroupByString () {
		//3 apple, 2 banana, others 1
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        // must be Long type instead of Integer
        Map<String,Long> result = items.stream().collect(
        		Collectors.groupingBy(
        				Function.identity(),Collectors.counting()
        			)
        		);
     
        System.out.println(result);             //output : spring, node 
	}
	//Sort a map and add to finalMap
	@Test
	public void GroupByAndSortedMap () {
		//3 apple, 2 banana, others 1
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        // must be Long type instead of Integer
        Map<String,Long> result = items.stream().collect(
        		Collectors.groupingBy(
        				Function.identity(),Collectors.counting()
        			)
        		);
    
        System.out.println("Unsorted Map:"+result);             //output : spring, node 
        
      //Sort a map and add to finalMap
        Map<String, Long> finalMap = new LinkedHashMap<>();

        //Sort a map and add to finalMap
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println("Sorted Map:"+finalMap);

	}
	class Item {

	    private String name;
	    private int qty;
	    private BigDecimal price;
		public Item(String name, int qty, BigDecimal price) {
			super();
			this.name = name;
			this.qty = qty;
			this.price = price;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getQty() {
			return qty;
		}
		public void setQty(int qty) {
			this.qty = qty;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		@Override
		public String toString() {
			return "Item [name=" + name + ", qty=" + qty + ", price=" + price + "]\n";
		}
	    
	}
	//Group by the name + Count or Sum the Qty.
		@Test
		public void GroupByCountAndSumTheQty () {
			//3 apple, 2 banana, others 1
	        List<Item> items = Arrays.asList(
	                new Item("apple", 10, new BigDecimal("9.99")),
	                new Item("banana", 20, new BigDecimal("19.99")),
	                new Item("orang", 10, new BigDecimal("29.99")),
	                new Item("watermelon", 10, new BigDecimal("29.99")),
	                new Item("papaya", 20, new BigDecimal("9.99")),
	                new Item("apple", 10, new BigDecimal("9.99")),
	                new Item("banana", 10, new BigDecimal("19.99")),
	                new Item("apple", 20, new BigDecimal("9.99"))
	        );
	        Map<String, Long> counting = items.stream().collect(
	                Collectors.groupingBy(Item::getName, Collectors.counting()));

	        System.out.println(counting);

	        Map<String, Integer> sum = items.stream().collect(
	                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

	        System.out.println(sum);
	        
	      //group by price
	        Map<BigDecimal, List<Item>> groupByPriceMap = 
				items.stream().collect(Collectors.groupingBy(Item::getPrice));

	        System.out.println("\n"+groupByPriceMap);

			// group by price, uses 'mapping' to convert List<Item> to Set<String>
	        Map<BigDecimal, Set<String>> result =
	                items.stream().collect(
	                        Collectors.groupingBy(Item::getPrice,
	                                Collectors.mapping(Item::getName, Collectors.toSet())
	                        )
	                );

	        System.out.println(result);

		}
}
