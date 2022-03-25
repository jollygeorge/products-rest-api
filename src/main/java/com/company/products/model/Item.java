package com.company.products.model;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "item_name")
	private String itemName;
	@Column(name = "item_category")
	private String category;

	public Item() {
	}

	public Item(String itemName, String category) {
		this.itemName = itemName;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public String getItemName() {
		return itemName;
	}

	public String getCategory() {
		return category;
	}
}