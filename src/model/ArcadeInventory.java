package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Fernando Garcia - fggarcia
 * CIS175 - Fall 2021
 * Sep 16, 2021
 */
@Entity
@Table(name="ArcadeInventory")
public class ArcadeInventory {
	@Id
	@GeneratedValue
	@Column (name="ARCADEID")
	private int id;
	@Column (name="TITLE")
	private String title;
	@Column (name="COST_PER_PLAY")
	private double costPerPlay;
	@Column (name="PURCHASE_PRICE")
	private double purchasePrice;
	
	public ArcadeInventory() {}
	
	public ArcadeInventory(String title, double costPerPlay, double purchasePrice) {
		this.title = title;
		this.costPerPlay = costPerPlay;
		this.purchasePrice = purchasePrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getCostPerPlay() {
		return costPerPlay;
	}

	public void setCostPerPlay(double costPerPlay) {
		this.costPerPlay = costPerPlay;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public String returnArcadeDetails() {
		return "ID: " + this.id + " Title: " + this.title + ", Cost Per Play: " 
			+ this.costPerPlay + ", Purchased at: " + this.purchasePrice;
	}

}
