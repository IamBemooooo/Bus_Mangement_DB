package Database;

import Interface.DaoInterface;
import models.Driver;
import models.Line;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LineDao implements DaoInterface<Line> {

    public static LineDao getInstance()
    {
        return new LineDao();
    };
    @Override
    public boolean insert(Line line) {
        try {
            Connection con = JDBCUlt.getConnection();
            Statement stmt = con.createStatement();
            String Sql = "INSERT INTO `line`(`Distance`, `Bus-Stop`)" +
                    " VALUES ('"+line.getDistance()+"','"+line.getBusStop()+"')";
            boolean rs = stmt.execute(Sql);
            JDBCUlt.CloseConnection(con);
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Line> getAll() {
        ArrayList<Line> drivers = new ArrayList<>();
        try {
            Connection con = JDBCUlt.getConnection();
            Statement stmt = con.createStatement();
            String Sql = "Select * from `Line`";
            ResultSet rs = stmt.executeQuery(Sql);
            while (rs.next())
            {
                int id = rs.getInt("ID");
                int distance = rs.getInt("Distance");
                int bus_stop = rs.getInt("Bus-Stop");

                Line driver = new Line(id,distance,bus_stop);
                drivers.add(driver);
            }
            JDBCUlt.CloseConnection(con);
            return drivers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Line line) {
        return false;
    }

    @Override
    public Line getById(int id) {
        Line line = null;
        try {
            Connection con = JDBCUlt.getConnection();
            Statement stmt = con.createStatement();
            String Sql = "Select * from line where id = " + id;
            ResultSet rs = stmt.executeQuery(Sql);

            if (rs.next()) {
                int ID = rs.getInt("ID");
                int distance = rs.getInt("Distance");
                int number = rs.getInt("Bus-Stop");

                line = new Line(ID,distance,number);
            }
            JDBCUlt.CloseConnection(con);
            return line;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Line> getByCondition(String condition) {
        return null;
    }
}
