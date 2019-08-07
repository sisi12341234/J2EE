package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseOperate {
	static public void main(String[] args) {
	}

	static public int RegisterUser(String UserName, String PassWord)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection DataConnection = java.sql.DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/myfriend", "root", "12345678");
		Statement DataStatement = DataConnection.createStatement();
		ResultSet UserIsExistResult = DataStatement
				.executeQuery("select name from user where name = '" + UserName
						+ "'");
		if (UserIsExistResult.next()) {
			return 0;
		} else {
			ResultSet MaxIdResult = DataStatement
					.executeQuery("select max(userid) from user");
			int MaxId;
			if (MaxIdResult.next()) {
				MaxId = MaxIdResult.getInt("max(userid)");
			} else {
				MaxId = 0;
			}
			MaxId++;
			DataStatement.executeUpdate("INSERT INTO user VALUES ('" + MaxId
					+ "', '" + UserName + "', '" + PassWord + "')");
			return 1;
		}
	}

	static public int LoginUser(String UserName, String PassWord)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection DataConnection = java.sql.DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/myfriend", "root", "si13044");
		Statement DataStatement = DataConnection.createStatement();
		ResultSet UserIsExistResult = DataStatement
				.executeQuery("select * from user where name = '" + UserName
						+ "'");
		if (UserIsExistResult.next()) {
			String DBPassWord = UserIsExistResult.getString("password");
			if (DBPassWord.equals(PassWord)) {
				return 0;
			} else {
				return 2;
			}
		} else {
			return 1;
		}
	}

	static public String[] GetFriend(String FriendId)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection DataConnection = java.sql.DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/myfriend", "root", "si13044");
		Statement DataStatement = DataConnection.createStatement();
		ResultSet MyFriendsResult = DataStatement
				.executeQuery("select * from myfriend where id = '" + FriendId
						+ "'");
		if (MyFriendsResult.next()) {
			String[] RowStringArray = new String[9];
			RowStringArray[0] = String.valueOf(MyFriendsResult.getInt("id"));
			RowStringArray[1] = String
					.valueOf(MyFriendsResult.getInt("userid"));
			RowStringArray[2] = String.valueOf(MyFriendsResult
					.getString("name"));
			RowStringArray[3] = String
					.valueOf(MyFriendsResult.getString("sex"));
			RowStringArray[4] = String.valueOf(MyFriendsResult.getInt("age"));
			RowStringArray[5] = String.valueOf(MyFriendsResult.getString("qq"));
			RowStringArray[6] = String.valueOf(MyFriendsResult
					.getString("telephone"));
			RowStringArray[7] = String.valueOf(MyFriendsResult
					.getString("email"));
			RowStringArray[8] = String.valueOf(MyFriendsResult
					.getString("address"));
			return RowStringArray;
		}
		return null;
	}

	static public String[][] GetAllFriends(String UserName, String SubName)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection DataConnection = java.sql.DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/myfriend", "root", "si13044");
		Statement DataStatement = DataConnection.createStatement();
		ResultSet MyFriendsResult = DataStatement
				.executeQuery("select * from myfriend where userid in (select userid from user where name = '"
						+ UserName + "')");
		if (MyFriendsResult.next()) {
			MyFriendsResult.beforeFirst();
			ArrayList<String[]> ResultArray = new ArrayList<String[]>();
			while (MyFriendsResult.next()) {
				String[] RowStringArray = null;
				String TempName = String.valueOf(MyFriendsResult
						.getString("name"));
				if (SubName == "" || TempName.matches("^.*" + SubName + ".*$")) {
					RowStringArray = new String[9];
					RowStringArray[0] = String.valueOf(MyFriendsResult
							.getInt("id"));
					RowStringArray[1] = String.valueOf(MyFriendsResult
							.getInt("userid"));
					RowStringArray[2] = String.valueOf(MyFriendsResult
							.getString("name"));
					RowStringArray[3] = String.valueOf(MyFriendsResult
							.getString("sex"));
					RowStringArray[4] = String.valueOf(MyFriendsResult
							.getInt("age"));
					RowStringArray[5] = String.valueOf(MyFriendsResult
							.getString("qq"));
					RowStringArray[6] = String.valueOf(MyFriendsResult
							.getString("telephone"));
					RowStringArray[7] = String.valueOf(MyFriendsResult
							.getString("email"));
					RowStringArray[8] = String.valueOf(MyFriendsResult
							.getString("address"));
					ResultArray.add(RowStringArray);
				}
			}
			String[][] Result = new String[ResultArray.size()][9];
			for (int i = 0; i < ResultArray.size(); i++) {
				Result[i] = ResultArray.get(i);
			}
			return Result;
		} else {
			return null;
		}
	}

	static public int Insert(String UserName, String NewName, String NewSex,
			String NewAge, String NewQQ, String NewTel, String NewMail,
			String NewAddr) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection DataConnection = java.sql.DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/myfriend", "root", "si13044");
		Statement DataStatement = DataConnection.createStatement();
		ResultSet UserIdResult = DataStatement
				.executeQuery("select userid from user where name = '"
						+ UserName + "'");
		String userid;
		if (UserIdResult.next()) {
			userid = UserIdResult.getString("userid");
		} else {
			return -1;
		}
		ResultSet MaxIdResult = DataStatement
				.executeQuery("select max(id) from myfriend");
		int MaxId;
		if (MaxIdResult.next()) {
			MaxId = MaxIdResult.getInt("max(id)");
		} else {
			MaxId = 0;
		}
		MaxId++;
		System.out.println(MaxId);
		DataStatement.executeUpdate("INSERT INTO myfriend VALUES ('" + MaxId
				+ "', '" + userid + "', '" + NewName + "', '" + NewSex + "', '"
				+ NewAge + "', '" + NewQQ + "', '" + NewTel + "', '" + NewMail
				+ "', '" + NewAddr + "')");
		return 0;
	}

	static public void DeleteFriend(String FriendId)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection DataConnection = java.sql.DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/myfriend", "root", "si13044");
		Statement DataStatement = DataConnection.createStatement();
		DataStatement.executeUpdate("delete from myfriend where id = '"
				+ FriendId + "'");
	}

	static public int Modify(String FriendId, String NewName, String NewSex,
			String NewAge, String NewQQ, String NewTel, String NewMail,
			String NewAddr) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection DataConnection = java.sql.DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/myfriend", "root", "si13044");
		Statement DataStatement = DataConnection.createStatement();
		DataStatement
				.executeUpdate("UPDATE myfriend SET name='" + NewName
						+ "', sex='" + NewSex + "', age='" + NewAge + "', qq='"
						+ NewQQ + "', telephone='" + NewTel + "', email='"
						+ NewMail + "', address='" + NewAddr + "' WHERE id = '"
						+ FriendId + "' ");
		return 0;
	}
}
