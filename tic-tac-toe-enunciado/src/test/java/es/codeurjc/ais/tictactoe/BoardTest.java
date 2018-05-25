package es.codeurjc.ais.tictactoe;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;



public class BoardTest {
	Player player1;
	Player player2;
	Board board;
		
	@Before
	public void setUp() {
		this.player1 = new Player(1,"X", "Jorge");
		this.player2 = new Player(2,"O", "Diego");
		board = new Board();
	}
	
	@Test
	public void playerOneWinTest() {
		Cell c0 = board.getCell(0);
		c0.setValue(player1.getLabel());
		Cell c1 = board.getCell(1);
		c1.setValue(player1.getLabel());
		Cell c2 = board.getCell(2);
		c2.setValue(player1.getLabel());
		int[] expectedResult = {0,1,2};
		
		int[] resultPlayerOne = board.getCellsIfWinner(player1.getLabel());
		int[] resultPlayerTwo = board.getCellsIfWinner(player2.getLabel());
		
		assertThat(expectedResult).isEqualTo(resultPlayerOne); //Comprobamos que se ha ganado la partida
		assertNull(resultPlayerTwo);
		assertEquals(false, board.checkDraw()); //Comprobamos que no hay empate
	}
	
	@Test
	public void playerTwoWinTest() {
		Cell c0 = board.getCell(0);
		c0.setValue(player2.getLabel());
		Cell c1 = board.getCell(1);
		c1.setValue(player2.getLabel());
		Cell c2 = board.getCell(2);
		c2.setValue(player2.getLabel());
		int[] expectedResult = {0,1,2};
		
		int[] resultPlayerOne = board.getCellsIfWinner(player1.getLabel());
		int[] resultPlayerTwo = board.getCellsIfWinner(player2.getLabel());
		
		assertThat(expectedResult).isEqualTo(resultPlayerTwo); //Comprobamos que se ha ganado la partida
		assertNull(resultPlayerOne);
		assertEquals(false, board.checkDraw()); //Comprobamos que no hay empate
	}
	
	@Test
	public void checkDrawTest() {
		Cell c0 = board.getCell(0);
		c0.setValue(player1.getLabel());
		Cell c4 = board.getCell(4);
		c4.setValue(player2.getLabel());
		Cell c8 = board.getCell(8);
		c8.setValue(player1.getLabel());
		Cell c1 = board.getCell(1);
		c1.setValue(player2.getLabel());
		Cell c7 = board.getCell(7);
		c7.setValue(player1.getLabel());
		Cell c6 = board.getCell(6);
		c6.setValue(player2.getLabel());
		Cell c2 = board.getCell(2);
		c2.setValue(player1.getLabel());
		Cell c5 = board.getCell(5);
		c5.setValue(player2.getLabel());
		Cell c3 = board.getCell(3);
		c3.setValue(player1.getLabel());
		int[] resultPlayerOne = board.getCellsIfWinner(player1.getLabel());
		int[] resultPlayerTwo = board.getCellsIfWinner(player2.getLabel());
		
		assertNull(resultPlayerOne); //Comprobamos que no ha ganado el jugador 1
		assertNull(resultPlayerTwo); // Comprobamos que no ha ganado el jugador 2
		assertTrue(board.checkDraw()); //Comprueba si hay empate
	}

}
