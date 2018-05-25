package es.codeurjc.ais.tictactoe;





import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import static org.mockito.hamcrest.MockitoHamcrest.argThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;


public class TicTacToeGameTest {
	TicTacToeGame game;
	Player player1, player2;
	Connection c1,c2;
	
	@Before
	public void setUp(){
		game = new TicTacToeGame();
		c1 = mock(Connection.class);
		c2 = mock(Connection.class);
		game.addConnection(c1);
		game.addConnection(c2);
		player1 = new Player(1,"X", "Jorge");
		player2 = new Player(2,"O", "Diego");
		
		game.addPlayer(player1);
		reset(c1);
		reset(c2);
		game.addPlayer(player2);
		
		
	}
	
	@Test
	public void playerOneWinTest() {
		//Comprobamos que las dos conexiones reciben el evento JOIN_GAME con ambos jugadores
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		verify(c2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		
		//Comprobamos que ambas conexiones reciben el evento SET_TURN con el jugador 1
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		game.mark(0);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player2));
		
		reset(c1);
		reset(c2);
		
		game.mark(5);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		reset(c1);
		reset(c2);
		
		game.mark(1);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player2));
		
		reset(c1);
		reset(c2);
		
		game.mark(7);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		reset(c1);
		reset(c2);
		
		game.mark(2);
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue event = (WinnerValue) argument.getValue();
		
		//Comprobamos que la conexion 2 ha recibido el evento GAME_OVER con el objeto WinnerValue
		verify(c2).sendEvent(eq(EventType.GAME_OVER), eq(event));
		
		//Comprobamos que el ganador ha sido el jugador 1
		assertThat(event.player.equals(player1));
		
		//Comprobamos que no ha ganado el jugador 2
		assertThat(!event.player.equals(player2));
		
		//Comprobamos que no hay empate
		assertNotNull(event);
		
	}
	
	@Test
	public void playerTwoWinTest() {
		//Comprobamos que las dos conexiones reciben el evento JOIN_GAME con ambos jugadores
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		verify(c2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		
		//Comprobamos que ambas conexiones reciben el evento SET_TURN con el jugador 1
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		game.mark(5);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player2));
		
		reset(c1);
		reset(c2);
		
		game.mark(0);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		reset(c1);
		reset(c2);
		
		game.mark(7);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player2));
		
		reset(c1);
		reset(c2);
		
		game.mark(1);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		reset(c1);
		reset(c2);
		
		game.mark(4);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player2));
		
		reset(c1);
		reset(c2);
		
		game.mark(2);
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue event = (WinnerValue) argument.getValue();
		
		//Comprobamos que la conexion 2 ha recibido el evento GAME_OVER con el objeto WinnerValue
		verify(c2).sendEvent(eq(EventType.GAME_OVER), eq(event));
		
		//Comprobamos que el ganador ha sido el jugador 2
		assertThat(event.player.equals(player2));
		
		//Comprobamos que no ha ganado el jugador 1
		assertThat(!event.player.equals(player1));
		
		//Comprobamos que no hay empate
		assertNotNull(event);
		
	}
	
	@Test
	public void checkDrawTest() {
		//Comprobamos que las dos conexiones reciben el evento JOIN_GAME con ambos jugadores
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		verify(c2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
				
		//Comprobamos que ambas conexiones reciben el evento SET_TURN con el jugador 1
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		game.mark(0);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player2));
		
		reset(c1);
		reset(c2);
		
		game.mark(4);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		reset(c1);
		reset(c2);
		
		game.mark(8);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player2));
		
		reset(c1);
		reset(c2);
		
		game.mark(1);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		reset(c1);
		reset(c2);
		
		game.mark(7);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player2));
		
		reset(c1);
		reset(c2);
		
		game.mark(6);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		reset(c1);
		reset(c2);
		
		game.mark(2);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player2));
		
		reset(c1);
		reset(c2);
		
		game.mark(5);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(player1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(player1));
		
		reset(c1);
		reset(c2);
		
		game.mark(3);
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		Object event =  argument.getValue();
		
		//Comprobamos que la conexion 2 ha recibido el evento GAME_OVER con el objeto WinnerValue
		verify(c2).sendEvent(eq(EventType.GAME_OVER), eq(event));
		
		/*//Comprobamos que no ha ganado el jugador 1
		assertThat(!event.player.equals(player1));
		
		//Comprobamos que no ha ganado el jugador 2
		assertThat(!event.player.equals(player2));*/
		
		//Comprobamos que hay empate
		assertNull(event);
		
	}
	

}
