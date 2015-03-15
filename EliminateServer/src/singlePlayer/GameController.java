/*
 * 
 */
package singlePlayer;

import java.util.ArrayList;

import login.LoginThread;
import login.OnlineUser;
import user.User;
import user.UserMsg;
import chess.MutiPlayerGameMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class GameController.
 */
public class GameController {

	/** The Constant TIME_TOOL_PRICE. */
	private static final int TIME_TOOL_PRICE = 35;

	/** The Constant BOMB_TOOL_PRICE. */
	private static final int BOMB_TOOL_PRICE = 35;

	/** The Constant SUPER_TOOL_PRICE. */
	private static final int SUPER_TOOL_PRICE = 35;

	/** The Constant HINT_TOOL_PRICE. */
	private static final int HINT_TOOL_PRICE = 35;

	/** The Constant SCORE_TOOL_PRICE. */
	private static final int SCORE_TOOL_PRICE = 35;

	/**
	 * End game.
	 * 
	 * @param user
	 *            the user
	 * @param score
	 *            the score
	 * @param combo
	 *            the combo
	 * @param gold
	 *            the gold
	 */
	public static void endGame(User user, int score, int combo, int gold) {
		OnlineUser.getSockets(user.getUserId()).getUser().setGameType(0);
		OnlineUser.getSockets(user.getUserId()).getUser().addGold(gold);
		LoginThread.saveUser(OnlineUser.getSockets(user.getUserId()).getUser());
		LoginThread.updateSingleRank(user, score, combo);
		user = LoginThread.getUser(user.getUserId(), user.getPassword());
		OnlineUser.getSockets(user.getUserId()).setUser(user);
		OnlineUser.getSockets(user.getUserId()).writeObject(
				new UserMsg(OnlineUser.getSockets(user.getUserId())
						.getUser(), UserMsg.UPDATE_USER_INFO));
	}

	/**
	 * Start game.
	 * 
	 * @param items
	 *            the items
	 * @param userID
	 *            the user id
	 */
	public static void StartGame(ArrayList<Integer> items, String userID) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		if (items == null) {
			return;
		}
		int gold = OnlineUser.getSockets(userID).getUser().getGold();
		for (int i = 0; i < items.size(); i++) {
			switch (items.get(i)) {
			case MutiPlayerGameMsg.BOMB_TOOL:
				if (gold >= BOMB_TOOL_PRICE) {
					gold -= BOMB_TOOL_PRICE;
					temp.add(MutiPlayerGameMsg.BOMB_TOOL);
				}
				break;
			case MutiPlayerGameMsg.TIME_TOOL:
				if (gold > TIME_TOOL_PRICE) {
					gold -= TIME_TOOL_PRICE;
					temp.add(MutiPlayerGameMsg.TIME_TOOL);
				}
				break;
			case MutiPlayerGameMsg.SCORE_TOOL:
				if (gold > SCORE_TOOL_PRICE) {
					gold -= SCORE_TOOL_PRICE;
					temp.add(MutiPlayerGameMsg.SCORE_TOOL);
				}
				break;
			case MutiPlayerGameMsg.SUPER_TOOL:
				if (gold > SUPER_TOOL_PRICE) {
					gold -= SUPER_TOOL_PRICE;
					temp.add(MutiPlayerGameMsg.SUPER_TOOL);
				}
				break;
			case MutiPlayerGameMsg.HINT_TOOL:
				if (gold > HINT_TOOL_PRICE) {
					gold -= BOMB_TOOL_PRICE;
					temp.add(MutiPlayerGameMsg.HINT_TOOL);
				}
				break;
			}
			if (gold < 0) {
				break;
			}
		}
		OnlineUser.getSockets(userID).getUser().setGold(gold);
		OnlineUser.getSockets(userID).getUser()
				.setGameType(User.SINGLE_PLAYER_GAME);
		OnlineUser.getSockets(userID)
				.writeObject(new SinglePlayerMsg(temp));
		OnlineUser.getSockets(userID).writeObject(
				new UserMsg(OnlineUser.getSockets(userID).getUser(),
						UserMsg.UPDATE_USER_INFO));
		LoginThread.saveUser(OnlineUser.getSockets(userID).getUser());
	}
}
