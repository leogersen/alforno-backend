package com.leogersen.alforno.domain.restaurant;

import com.leogersen.alforno.util.StringUtils;
import lombok.Data;


@Data
public class SearchFilter {

	public enum SearchType {
		Text, Category
	}
	
	public enum Order {
		Tax, Time
	}
	
	public enum Command {
		FreeTax, LessTime, LessTax
	}
	
	private String text;
	private SearchType searchType;
	private Integer categoryId;
	private Order order = Order.Tax;
	private boolean asc;
	private boolean freeTax;
	

	
	public void processFilter(String cmdString) {

		if (!StringUtils.isEmpty(cmdString)) {
			Command cmd = Command.valueOf(cmdString);

			if (cmd == Command.FreeTax) {
			freeTax = !freeTax;

		} else if (cmd == Command.LessTax) {
				order = Order.Tax;
				asc = !asc;

			}else if (cmd == Command.LessTime){
				order = Order.Time;
				asc = !asc;
			}

		}


		if (searchType == SearchType.Text) {
			categoryId = null;
		} else if (searchType == SearchType.Category) {
			text = null;
		}
	}

}
