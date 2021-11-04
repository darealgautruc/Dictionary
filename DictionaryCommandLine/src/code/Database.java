package code;

import java.sql.*;
import java.util.LinkedList;

public class Database {
    private Statement state;
    private ResultSet database;
    private LinkedList<String[]> data;
    private int num_id;

    public void Connection() throws SQLException {
        Connection connection = null;
        String url = "jdbc:mariadb://localhost:3306";
        String user = "root";
        String pwd = "08072001";
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        state = connection.createStatement();
        database = state.executeQuery("select * from dictionary");
        data = new LinkedList<>();
        String num[];
        while (database != null && database.next()) {
            num = new String[num_id];
            num[0] = database.getString("Eng").toLowerCase();
            num[1] = database.getString("Vietnamese");
            data.addLast(num);
        }
    }

    public void xoaTu(String s) throws SQLException {
        String cmd = "delete from dictionary where Eng =  " + s;
        database = state.executeQuery(cmd);
    }

    public void showAll() {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }
}
