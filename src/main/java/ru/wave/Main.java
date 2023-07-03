package ru.wave;

import ru.wave.action.Action;
import ru.wave.connection.ConnectionManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        runConsoloeApplication();
    }

    public static void runConsoloeApplication() throws SQLException {
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.getConnection();
        System.out.println("Выберете одно из действий:\n" +
                "- Топ-10 работников по дате вступление в огранизацию 1\n- Топ-10 работников по зарплате 2\n" +
                "- Просмотреть работников 3");
        int idAction = scanner.nextInt();
        Action.activeAction(idAction);
    }



}