import Database.AssignmentDao;
import Database.DriverDao;
import Database.JDBCUlt;
import Database.LineDao;
import jakarta.persistence.*;
import models.Assignment;
import models.Driver;
import models.Line;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.*;

public class Run {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        while (true) {
            displayMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    AddDriver();
                    PrintDriver();
                    break;
                case 2:
                    AddLine();
                    PrintLine();
                    break;
                case 3:
                    AddASM();
                    PrintASM();
                    break;
                case 4:
                    SortByName();
                    break;
                case 5:
                    SortByTurn();
                    break;
                case 6:
                    SumTurn();
                    break;
                case 7:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Nhập danh sách đầu sách mới .");
        System.out.println("2. Nhập danh sách bạn đọc mới.");
        System.out.println("3. Lập bảng bang diem.");
        System.out.println("5. Sắp xếp danh sách bang diem theo tên SV.");
        System.out.println("5. Sắp xếp danh sách bang diem theo ten MH.");
        System.out.println("6. In danh sach diem TB");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void AddDriver()
    {
        System.out.println("Moi nhap so nguoi lai xe:");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++)
        {
            Driver d = new Driver();
            System.out.println("Nhap ten :");
            d.setName(sc.nextLine());
            if(d.getName() == "")
            {
                d.setName(sc.nextLine());
            }
            System.out.println("Nhap dia chi :");
            d.setAddress(sc.nextLine());
            System.out.println("Nhap so dien thoai :");
            d.setNumber(sc.nextInt());
            System.out.println("Nhap bang lai xe :");
            d.setLisence(sc.nextLine());
            if(d.getLisence() == "")
            {
                d.setLisence(sc.nextLine());
            }
            DriverDao.getInstance().insert(d);
        }
    }

    public static void PrintDriver()
    {
        List<Driver> Lst = DriverDao.getInstance().getAll();
        Lst.forEach( i -> System.out.println(i));
    }

    public static void AddLine()
    {
        System.out.println("Moi nhap so tuyen duong:");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++)
        {
            Line d = new Line();
            System.out.println("Nhap khoang cach :");
            d.setDistance(sc.nextInt());
            if(d.getDistance() == 0)
            {
                d.setDistance(sc.nextInt());
            }
            System.out.println("Nhap so tram dung :");
            d.setBusStop(sc.nextInt());
            if(d.getBusStop() == 0)
            {
                d.setBusStop(sc.nextInt());
            }
            LineDao.getInstance().insert(d);
        }
    }

    public static void PrintLine()
    {
        List<Line> Lst = LineDao.getInstance().getAll();
        Lst.forEach( i -> System.out.println(i));
    }

    public static void AddASM()
    {
        List<Assignment> Lst = AssignmentDao.getInstance().getAll();
        System.out.println("Nhap ID tuyen duong :");
        Line l = LineDao.getInstance().getById(sc.nextInt());
        if(l == null)
        {
            l = LineDao.getInstance().getById(sc.nextInt());
        }
        System.out.println("Nhap ID nguoi lai :");
        Driver d = DriverDao.getInstance().getById(sc.nextInt());
        System.out.println("Nhap so luot :");
        int turn = sc.nextInt();
        if(turn == 0)
        {
            turn = sc.nextInt();
        }
        Assignment a = new Assignment();
        boolean check = true ;
        for(Assignment a1 : Lst)
        {
            if(a1.getIdDriver().getId() == d.getId() && a1.getIdLine().getId() == l.getId())
            {
                check = false;
                System.out.println("ban ghi da ton tai");
            }
        }

        if(turn > 15)
        {
            System.out.println("So luot khong duoc qua 15");
            check = false;
        }
        a.setTurn(turn);
        a.setIdLine(l);
        a.setIdDriver(d);
        if(check)
        {
            AssignmentDao.getInstance().insert(a);
        }
    }

    public static void PrintASM()
    {
        List<Assignment> Lst = AssignmentDao.getInstance().getAll();
        Lst.forEach( i -> System.out.println(i));
    }

    public static void  SortByName()
    {
        ArrayList<Assignment> Lst = AssignmentDao.getInstance().getAll();
        for(int i = 0 ; i < Lst.size() - 1; i++)
        {
            String Name1 = DriverDao.getInstance().getById(Lst.get(i).getIdDriver().getId()).getName();
            for (int j = i+1 ; j < Lst.size(); j++)
            {
                String Name2 = DriverDao.getInstance().getById(Lst.get(j).getIdDriver().getId()).getName();
                if(Name2.compareTo(Name1) > 0)
                {
                    Assignment temp = Lst.get(i);
                    Lst.set(i, Lst.get(j));
                    Lst.set(j, temp);
                }
            }
        }
        Lst.forEach( i -> System.out.println(i));
    }

    public static void  SortByTurn()
    {
        ArrayList<Assignment> Lst = AssignmentDao.getInstance().getAll();
        for(int i = 0 ; i < Lst.size() - 1; i++)
        {
            for (int j = i+1 ; j < Lst.size(); j++)
            {
                if(Lst.get(i).getDay() == Lst.get(j).getDay())
                {
                    if(Lst.get(i).getTurn() > Lst.get(j).getTurn())
                    {
                        Assignment temp = Lst.get(i);
                        Lst.set(i, Lst.get(j));
                        Lst.set(j, temp);
                    }
                }
            }
        }
        Lst.forEach( i -> System.out.println(i));
    }

    public static void  SumTurn()
    {
        ArrayList<Assignment> Lst = AssignmentDao.getInstance().getAll();
        Map<Driver,ArrayList<Assignment>> TodayLst = new HashMap<>();
        Lst.forEach( i ->
        {
            System.out.println("Tuyen duong : " + i.getIdLine().getId() + " ,Ten LX : " + i.getIdDriver().getName() + " ,Tong Khoảng cách : " + i.getTurn()*i.getIdLine().getDistance());
        });
    }
}
