/*
 * 
 */
package eliminate.model.room;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JButton;

import room.Room;
import eliminate.view.MyButton;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomList.
 */
public class RoomList extends Observable {

	/** The room list1. */
	private static Map<Long, Room> roomList1 = new HashMap<Long, Room>();

	/** The room list2. */
	private static Map<Long, Room> roomList2 = new HashMap<Long, Room>();

	/**
	 * Gets the room.
	 * 
	 * @param roomID
	 *            the room id
	 * @return the room
	 */
	public static Room getRoom(Long roomID) {
		Room obj = roomList1.get(roomID);
		if (obj == null) {
			obj = roomList2.get(roomID);
		}
		return obj;
	}

	/** The enter. */
	private String enter = "media/image/roomChoose/bt/enter.png";

	/** The enter_down. */
	private String enter_down = "media/image/roomChoose/bt/enter_down.png";

	/** The enter_hover. */
	private String enter_hover = "media/image/roomChoose/bt/enter_hover.png";

	/** The enter_disable. */
	private String enter_disable = "media/image/roomChoose/bt/enter_disable.png";

	/** The enter bt. */
	MyButton enterBt;

	/**
	 * Instantiates a new room list.
	 */
	public RoomList() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observable#addObserver(java.util.Observer)
	 */
	@Override
	public synchronized void addObserver(Observer arg0) {
		// TODO Auto-generated method stub
		super.addObserver(arg0);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the table data1.
	 * 
	 * @return the table data1
	 */
	public Object[][] getTableData1() {
		Object[][] tableData = new Object[roomList1.size()][4];
		Set<Long> ids = roomList1.keySet();
		Room room;
		int i = 0;
		for (long id : ids) {
			room = roomList1.get(id);
			tableData[i][0] = room.getID();
			tableData[i][1] = room.getCurrentUserNum() + "/"
					+ room.getMaxUserNum();
			tableData[i][2] = room.getName();
			try {
				tableData[i][3] = MyButton.makeButton(enter, enter_down,
						enter_hover, enter_disable);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tableData[i][3] = new JButton("Enter");
			}
			i++;
		}
		return tableData;
	}

	/**
	 * Gets the table data2.
	 * 
	 * @return the table data2
	 */
	public Object[][] getTableData2() {
		Object[][] tableData = new Object[roomList2.size()][4];
		Set<Long> ids = roomList2.keySet();
		Room room;
		int i = 0;
		for (long id : ids) {
			room = roomList2.get(id);
			tableData[i][0] = room.getID();
			tableData[i][1] = room.getCurrentUserNum() + "/"
					+ room.getMaxUserNum();
			tableData[i][2] = room.getName();
			try {
				tableData[i][3] = MyButton.makeButton(enter, enter_down,
						enter_hover, enter_disable);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tableData[i][3] = new JButton("Enter");
			}
			i++;
		}
		return tableData;
	}

	/**
	 * Sets the room list.
	 * 
	 * @param roomList1
	 *            the room list1
	 * @param roomList2
	 *            the room list2
	 */
	public void setRoomList(Map<Long, Room> roomList1, Map<Long, Room> roomList2) {
		RoomList.roomList1 = roomList1;
		RoomList.roomList2 = roomList2;
		System.out.println("RoomList:" + roomList1);
		setChanged();
		notifyObservers(this);
	}
}
