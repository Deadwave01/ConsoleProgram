package ru.wave.action;

import ru.wave.Main;
import ru.wave.users.User;
import ru.wave.users.UserController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public enum Action {
    SHOWSALARYTOP(1),
    SHOWDATETOP(2),
    SHOWEMPLOYEES(3);

    private static Scanner scanner = new Scanner(System.in);
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    enum ActionDataBase{
        ADDUSER(1,"Добавить сотрудника"),
        DELETEUSER(2,"Удалить сотрудника"),
        CHANGEUSER(3,"Поменять данные сотрудника"),
        EXIT(4,"Вернуться на прошлую страницу");

        private int id;
        private String localizedName;

        static List<ActionDataBase> list = new ArrayList<>(Arrays.asList(ADDUSER,DELETEUSER,CHANGEUSER,EXIT));

        ActionDataBase(int id,String localizedName) {
            this.id = id;
            this.localizedName = localizedName;
        }

        public int getId() {
            return id;
        }
        public String getLocalizedName() {
            return localizedName;
        }

        public static void activeAction(int id) throws SQLException {
            try {
                //Controller
                if (id == 1) {
                    User user = new User();
                    System.out.println("Впишите ФИО сотрудника");
                    user.setName(reader.readLine());
                    System.out.println("Впишите ФИО начальника");
                    user.setBossName(reader.readLine());
                    System.out.println("Впишите должность");
                    user.setPost(reader.readLine());
                    System.out.println("Впишите зарплату");
                    String salary = reader.readLine();
                    user.setSalary(Integer.parseInt(salary));
                    System.out.println("Впишите название отдела");
                    user.setDepartamentname(reader.readLine());
                    //Date parse
                    System.out.println("Впишите дату подписания трудового договора в виде: day/month/year(05/05/2005)");
                    String temp = reader.readLine();
                    String[] temp1 = temp.split("/");
                    int[] date = new int[3];
                    for (int i = 0; i < date.length; i++) {
                        date[i] = Integer.parseInt(temp1[i]);
                    }
                    Date date1 = new Date(date[2]-1900, date[1]-1, date[0]);
                    user.setDate(date1);
                    UserController.addUser(user);
                } else if(id == 3){
                    System.out.println("Впишите ID сотрудника,у которого хотите поменять данные (Он отображается напротив EMPLOYEE)");
                    int idEmployee = scanner.nextInt();
                    UserController.changeUser(idEmployee);
                } else if (id == 2) {
                    System.out.println("Впишите ID сотрудника,которого хотите удалить (Он отображается напротив EMPLOYEE)");
                    int idEmployee = scanner.nextInt();
                    UserController.deleteUser(idEmployee);
                } else {
                    Main.runConsoloeApplication();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private int id;
    Action(int id) {
    }

    private static List<Action> actions = new ArrayList<>(Arrays.asList(
            SHOWSALARYTOP,
            SHOWDATETOP,
            SHOWEMPLOYEES
    ));

    public int getId() {
        return id;
    }

    public static void activeAction(int id) throws SQLException {
        if(id == 2) {
            activeShowSalaryTop();
        } else if(id == 1){
            activeShowDateTop();
        } else if(id == 3){
            activeShowEmployeeTop();
        } else {
            return;
        }
    }

    private static void activeShowDateTop() throws SQLException {
        List<User> sortedList = User.getUsers();
        sortedList.sort(Comparator.comparing(User::getDate).thenComparing(User::getName));
        for(int i = 0; i < sortedList.size() && i < 10;i++){
            System.out.println(sortedList.get(i).toStringDate());
        }
        Main.runConsoloeApplication();
    }
    private static void activeShowSalaryTop() throws SQLException {
        List<User> sortedList = User.getUsers();
        sortedList.sort(Comparator.comparingInt(User::getSalary).thenComparing(User::getName));
        for(int i = 0; i < sortedList.size() && i < 10;i++){
            System.out.println(sortedList.get(i).toStringSalary());
        }
        Main.runConsoloeApplication();
    }
    private static void activeShowEmployeeTop() throws SQLException {
        if(User.getUsers().size() == 0){
            System.out.println("На данный момент нету пользователей в базе данных!\nХотите ли вы что-то выбрать: ");
            for(ActionDataBase a : ActionDataBase.list){
                System.out.println(a.getLocalizedName() + " " + a.getId());
            }
            int id = scanner.nextInt();
            ActionDataBase.activeAction(id);
        } else {
            for(int i = 0; i < User.getUsers().size();i++){
                System.out.println(User.getUsers().get(i));
            }
            System.out.println("Хотите ли вы что-то выбрать: ");
            for(ActionDataBase a : ActionDataBase.list){
                System.out.println(a.getLocalizedName() + " " + a.getId());
            }
            int id = scanner.nextInt();
            ActionDataBase.activeAction(id);
        }
    }
}
