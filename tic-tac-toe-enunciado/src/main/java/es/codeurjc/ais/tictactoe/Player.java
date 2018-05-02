package es.codeurjc.ais.tictactoe;

import java.io.Serializable;

public class Player implements Serializable{

	private String label;
	private String name;
	private int id;

	public Player(int id, String label, String name) {
		this.id = id;
		this.label = label;
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
}
