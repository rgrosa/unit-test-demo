package com.example.demo.example;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DemoApplication {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        UserList userList = new UserList();

        int endProgram = 1;
        while (endProgram == 1) {
            System.out.println("--------------------");
            System.out.println("0 - Sair do programa");
            System.out.println("1 - Cadastrar usuario");
            System.out.println("2 - Listar todos usuarios");
            System.out.println("3 - Listar um usuario");
            System.out.println("4 - Enviar email para um usuario");
            System.out.println("--------------------");
            String terminalInput = scanner.nextLine();  // Read user input
            switch (terminalInput) {
                case "0":
                    endProgram = 0;
                    break;
                case "1":
                    String userString = caseAddUser(scanner, userList);
                    if(userString != null){
                        System.out.println(userString);
                    }
                    break;
                case "2":
                    List<User> allUsers = caseFindAll(userList);
                    if(!allUsers.isEmpty()){
                        System.out.println(allUsers);
                    }
                    break;
                case "3":
                    User user = caseFindByName(scanner, userList);
                    if(user != null){
                        System.out.println(user);
                    }
                    break;
                case "4":
                    if(caseEmail(scanner, userList)){
                        System.out.println("email sent");
                    }
                    break;
                default:
            }
        }
    }

    private static String caseAddUser(Scanner scanner, UserList userList){
        String returnValue = null;
        try{
            System.out.println("Digite o nome do usuario");
            String userName = scanner.nextLine();
            System.out.println("Digite a data de nascimento"); //	"2023-01-01T00:00:00Z",
            String birthDay = scanner.nextLine();
            System.out.println("Digite o email do usuario");
            String email = scanner.nextLine();
            returnValue =  userList.createUser(userName, birthDay, email).toString();
        }catch (Exception ex){
            System.out.println("Um error ocorreu: "+ ex.getMessage());
        }
        return returnValue;
    }

    private static List<User> caseFindAll(UserList userList){
         return userList.getUserList();
    }

    private static User caseFindByName(Scanner scanner, UserList userList){
        User returnValue = null;
        try {
            System.out.println("Digite o nome do usuario");
            returnValue = userList.getUserByName(scanner.nextLine());
        }catch (NoSuchElementException ex){
            System.out.println("No user found");
        }
        return returnValue;
    }

    private static boolean caseEmail(Scanner scanner, UserList userList){
        boolean returnValue = false;
        try {
            System.out.println("Digite o nome do usuario");
            String name = scanner.nextLine();
            System.out.println("Digite a mensagem");
            String message = scanner.nextLine();
            returnValue = userList.sendEmail(message, name);
        }catch (NoSuchElementException ex){
            System.out.println("No user found");
        }
        return returnValue;
    }
}

