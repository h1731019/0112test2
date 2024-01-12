package rensyuu_9;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		System.out.println("1円以上のアイテム一覧を表示します");
		ArrayList<Item> items = ItemsDAO.findByMinimumPrice(1);
		for(Item item : items) {
			System.out.println(item.getName()+item.getPrice()+item.getWeight());
		}
	}
}
