/*
 * 
 */
package serverDB;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Team.
 */
public class Team implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private String[] id = new String[4];

	/** The score. */
	private int score = 0;

	/**
	 * Instantiates a new team.
	 * 
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @param c
	 *            the c
	 * @param d
	 *            the d
	 * @param s
	 *            the s
	 */
	public Team(String a, String b, String c, String d, int s) {
		id[0] = a;
		id[1] = b;
		id[2] = c;
		id[3] = d;
		score = s;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}

	/**
	 * Gets the score.
	 * 
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

}
