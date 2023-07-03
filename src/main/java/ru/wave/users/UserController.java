package ru.wave.users;

import ru.wave.Main;
import ru.wave.connection.ConnectionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static ru.wave.connection.ConnectionManager.connection;

public class UserController {

    private static Scanner scanner = new Scanner(System.in);

    public static User findUserWithName(String str) throws SQLException {
        for(User user : User.getUsers()){
            if(user.getName().equals("str")){
                return user;
            }
        }
        return null;
    }

    public static User findUserWithId(int id) throws SQLException {
        for(User user : User.getUsers()){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public static void addUser(User user) throws SQLException{
        String name = user.getName();
        Date date = user.getDate();
        int salary = user.getSalary();
        String post = user.getPost();
        String depname = user.getDepartamentname();
        String bossName = user.getBossName();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users (name,date,salary,departamentname,post,bossName) values(?,?,?,?,?,?)");
        preparedStatement.setString(1,name);
        preparedStatement.setDate(2,date);
        preparedStatement.setInt(3,salary);
        preparedStatement.setString(4,depname);
        preparedStatement.setString(5,post);
        preparedStatement.setString(6,bossName);
        preparedStatement.executeUpdate();
        System.out.println("Успешно!");
        Main.runConsoloeApplication();
    }

    public static void changeUser(int id) throws SQLException {
        User user = findUserWithId(id);
        if(user != null){
            PreparedStatement preparedStatement = null;
            System.out.println("Введите поля которые вы хотите заменить :\ndate,\nname,\ndepartamentname,\nbossName,\ndate,\nsalary,\npost");
            List<String> fields = List.of(scanner.nextLine().replaceAll(" ", "").split(","));
            if(fields.contains("name")){
                System.out.println("Введите новое ФИО");
                String name = scanner.nextLine();
                user.setName(name);
                preparedStatement = connection.prepareStatement("UPDATE users SET name = '" + name +"' WHERE id = '" + id + "'");
            }
            if(fields.contains("date")){
                System.out.println("Введите новую дату");
                String date =  scanner.nextLine().replaceAll("/","-");
                String[] temp = date.split("/");
                int[] temp1 = new int[3];
                for (int i = 0; i < temp1.length; i++) {
                    temp1[i] = Integer.parseInt(temp[i]);
                }
                Date date1 = new Date(temp1[2]-1900, temp1[1]-1, temp1[0]);
                user.setDate(date1);
                preparedStatement = connection.prepareStatement("UPDATE users SET date = '" + date + "' WHERE id = '" + id + "'");
            }
            if(fields.contains("salary")){
                System.out.println("Введите новую зарплату");
                int salary = scanner.nextInt();
                user.setSalary(salary);
                preparedStatement = connection.prepareStatement("UPDATE users SET salary = '" + salary +"' WHERE id = '" + id + "'");
            }
            if(fields.contains("departamentname")){
                System.out.println("Введите новое название департамента");
                String depName = scanner.nextLine();
                user.setDepartamentname(depName);
                preparedStatement = connection.prepareStatement("UPDATE users SET departamentname = '" + depName +"' WHERE id = '" + id + "'");
            }
            if(fields.contains("post")){
                System.out.println("Введите новую должность");
                String post = scanner.nextLine();
                user.setPost(post);
                preparedStatement = connection.prepareStatement("UPDATE users SET post = '" + post +"' WHERE id = '" + id + "'");
            }
            if(fields.contains("bossName")){
                System.out.println("Введите новое ФИО начальника");
                String bossName = scanner.nextLine();
                user.setBossName(bossName);
                preparedStatement = connection.prepareStatement("UPDATE users SET bossName = '" + bossName +"' WHERE id = '" + id + "'");

            }
            preparedStatement.executeUpdate();
            System.out.println("Успешно!");
            Main.runConsoloeApplication();
        }
    }

    public static void deleteUser(int id) throws SQLException {
        User user = findUserWithId(id);
        if(user != null){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = '" + id + "'");
            preparedStatement.executeUpdate();
            User.getUsers().remove(user);
            System.out.println("Успешно!");
            Main.runConsoloeApplication();
        }
    }
}
