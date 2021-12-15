package ru.croccode.hypernull.bot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

import ru.croccode.hypernull.domain.MatchMode;
import ru.croccode.hypernull.geometry.Offset;
import ru.croccode.hypernull.geometry.Size;
import ru.croccode.hypernull.io.SocketSession;
import ru.croccode.hypernull.message.Hello;
import ru.croccode.hypernull.message.MatchOver;
import ru.croccode.hypernull.message.MatchStarted;
import ru.croccode.hypernull.message.Move;
import ru.croccode.hypernull.message.Register;
import ru.croccode.hypernull.message.Update;

public class StarterBot implements Bot {

	private final MatchMode mode;
	private FriendlyBot myBot;

	public StarterBot(MatchMode mode) {
		this.mode = mode;
	}

	@Override
	public Register onHello(Hello hello) {
		Register register = new Register();
		register.setMode(mode);
		register.setBotName("NocTQx");
		return register;
	}

	@Override
	public void onMatchStarted(MatchStarted matchStarted) {

		Size mapSize = matchStarted.getMapSize();
		char[][] map = new char[mapSize.width()][mapSize.height()];
		for (int i = 0; i < mapSize.width(); i++){
			Arrays.fill(map[i], ' ');
		}
		int viewRadius = matchStarted.getViewRadius();
		int id = matchStarted.getYourId();
		if (matchStarted.getMode() == MatchMode.FRIENDLY){
			myBot = new FriendlyBot(mapSize, map, viewRadius, id);
		}
	}

	@Override
	public Move onUpdate(Update update) {

		Move move = new Move();
		move.setOffset(myBot.getMoveOffset(update.getBlocks(), update.getBotCoins(), update.getBots(), update.getCoins()));
		return move;
	}

	@Override
	public void onMatchOver(MatchOver matchOver) {
	}

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket();
		socket.setTcpNoDelay(true);
		socket.setSoTimeout(300_000);
		socket.connect(new InetSocketAddress("localhost", 2021));

		SocketSession session = new SocketSession(socket);
		StarterBot bot = new StarterBot(MatchMode.FRIENDLY);
		new BotMatchRunner(bot, session).run();
	}
}
