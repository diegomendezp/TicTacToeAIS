package es.codeurjc.ais.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;


public class BoardTest {
	Player player1;
	Player player2;
	TicTacToeGame game;
		
	@Before
	public void setUp() {
		this.player1 = new Player(1,"X", "Jorge");
		this.player2 = new Player(2,"O", "Diego");
		this.game = new TicTacToeGame();
		game.addPlayer(player1); //Se le asigna el primer turno
		game.addPlayer(player2); //Se le asigna el segundo turno
	}
	
	@Test
	public void playerOneWinTest() {
		game.mark(0);
		game.mark(5);
		game.mark(1);
		game.mark(7);
		game.mark(2);
		
		int[] resultadoEsperado = {0,1,2}; 
		
		WinnerResult resultado = game.checkWinner(); 
		
		assertTrue(game.checkTurn(1)); // Comprobamos que es el turno del jugador 1
		assertThat(resultadoEsperado).isEqualTo(resultado.pos); //Comprobamos que se ha ganado la partida
		//Como alguien ha ganado y es el turno del jugador 1; el jugador 1 es el que ha ganado
		assertEquals(false, game.checkDraw()); //Comprobamos que no hay empate
	}
	
	@Test
	public void playerTwoWinTest() {
		game.mark(5);
		game.mark(0);
		game.mark(7);
		game.mark(1);
		game.mark(8);
		game.mark(2);
		
		int[] resultadoEsperado = {0,1,2};
		WinnerResult resultado = game.checkWinner();
		assertTrue(game.checkTurn(2)); // Comprobamos que es el turno del jugador 2
		assertThat(resultadoEsperado).isEqualTo(resultado.pos); //Comprobamos que se ha ganado la partida
		//Como alguien ha ganado y es el turno del jugador 2; el jugador 2 es el que ha ganado
		assertEquals(false, game.checkDraw()); //Comprobamos que no hay empate
	}
	
	@Test
	public void checkDrawTest() {
		game.mark(0);
		game.mark(4);
		game.mark(8);
		game.mark(1);
		game.mark(7);
		game.mark(6);
		game.mark(2);
		game.mark(5);
		game.mark(3);
		
		assertTrue(game.checkDraw()); //Comprueba si hay empate
	}

}
