package Database;

import Interface.DaoInterface;
import models.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DriverDao implements DaoInterface<Driver> {
    public static DriverDao getInstance()
    {
        return new DriverDao();
    }

    @Override
    public boolean insert(Driver driver) {
        try {
            Connection con = JDBCUlt.getConnection();
            Statement stmt = con.createStatement();
            String Sql = "INSERT INTO `driver`(`Name`, `Address`, `Number`, `Lisence`)" +
                    " VALUES ('"+driver.getName()+"','"+driver.getAddress()+"','"+driver.getNumber()+"','"+driver.getLisence()+"')";
            boolean rs = stmt.execute(Sql);
            JDBCUlt.CloseConnection(con);
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Driver> getAll() {
        ArrayList<Driver> drivers = new ArrayList<>();
        try {
            Connection con = JDBCUlt.getConnection();
            Statement stmt = con.createStatement();
            String Sql = "Select * from `Driver`";
            ResultSet rs = stmt.executeQuery(Sql);
            while (rs.next())
            {
                int id = rs.getInt("ID");
                String Name = rs.getString("Name");
                String Address = rs.getString("Address");
                int Number = rs.getInt("Number");
                String Lisence = rs.getString("Lisence");

                Driver driver = new Driver(id,Lisence,Number,Address,Name);
                drivers.add(driver);
            }
            JDBCUlt.CloseConnection(con);
            return drivers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Driver driver) {
        return false;
    }

    @Override
    public Driver getById(int id)
    {
        Driver driver = null;
        try {
            Connection con = JDBCUlt.getConnection();
            Statement stmt = con.createStatement();
            String Sql = "Select * from driver where id = " + id;
            ResultSet rs = stmt.executeQuery(Sql);

            if (rs.next()) {
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int number = rs.getInt("Number");
                String license = rs.getString("Lisence");

                driver = new Driver(id, license, number, address, name );
            }
            JDBCUlt.CloseConnection(con);
            return driver;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Driver> getByCondition(String condition) {
        return null;
    }
}
