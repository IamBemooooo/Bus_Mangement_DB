package Database;

import Interface.DaoInterface;
import models.Assignment;
import models.Driver;
import models.Line;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class AssignmentDao implements DaoInterface<Assignment> {

    public static AssignmentDao getInstance()
    {
        return new AssignmentDao();
    };
    @Override
    public boolean insert(Assignment assignment) {
        try {
            Connection con = JDBCUlt.getConnection();
            Statement stmt = con.createStatement();
            String Sql = "INSERT INTO `assignment`(`ID_Line`, `ID_Driver`, `Day`, `Turn`)" +
                    "VALUES ('"+assignment.getIdLine().getId()+"','"+assignment.getIdDriver().getId()+"','"+LocalDate.now()+"','"+assignment.getTurn()+"')";
            boolean rs = stmt.execute(Sql);
            JDBCUlt.CloseConnection(con);
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Assignment> getAll() {
        ArrayList<Assignment> drivers = new ArrayList<>();
        try {
            Connection con = JDBCUlt.getConnection();
            Statement stmt = con.createStatement();
            String Sql = "Select * from `assignment`";
            ResultSet rs = stmt.executeQuery(Sql);
            while (rs.next())
            {
                int id = rs.getInt("ID");
                Integer id_line = rs.getInt("ID_Line");
                Line line = LineDao.getInstance().getById(id_line);
                Integer id_driver = rs.getInt("ID_Driver");
                Driver driver = DriverDao.getInstance().getById(id_driver);
                String day = rs.getString("Day");
                int turn = rs.getInt("Turn");

                LocalDate day1 = LocalDate.parse(day);
                Assignment asm = new Assignment(id,line,day1,driver,turn);
                drivers.add(asm);
            }
            JDBCUlt.CloseConnection(con);
            return drivers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Assignment assignment) {
        return false;
    }

    @Override
    public Assignment getById(int id) {
        return null;
    }

    @Override
    public ArrayList<Assignment> getByCondition(String condition) {
        return null;
    }
}
