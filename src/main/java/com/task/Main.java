package com.task;

import java.util.Scanner;

public class Main {


    private static final String PREFIX = "task-cli";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Command cc = new Command();

        System.out.println("Digite --help para saber os comandos");

        while (true) {
            String input = scanner.nextLine();
            String[] inputArgs = (PREFIX + " " + input).split(" ");

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Saindo... Até logo!");

                break;
            }

            switch (inputArgs[1]) {
                case "--help":
                    help();
                    break;
                case "add":
                    cc.add(input);
                    break;
                case "update":
                    cc.update(input);
                    break;

            }
        }

    }

    private static void help() {
        System.out.println("add para adicionar tarefa");
        System.out.println("update para atualizar status da tarefa");
        System.out.println("delete para deletar a tarefa");
        System.out.println("list para listar as tarefas");
        System.out.println("list done para listar tarefas ja feitas");
        System.out.println("list todo para listar tarefas não feitas");
        System.out.println("list in-progress para listar tarefas em andamento");

    }
}