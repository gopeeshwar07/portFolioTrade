package com.stateStreet.service;

public class TradeDTO {
	private String id;
	private String direction;
	private int amount;
	private double price;

	public TradeDTO(String id, String direction, int amount, double price) {
		this.id = id;
		this.direction = direction;
		this.amount = amount;
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return id+" - "+direction+"- "+amount;
}
}
