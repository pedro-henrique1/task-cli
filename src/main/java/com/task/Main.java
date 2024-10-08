package com.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String PREFIX = "task-cli";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Command cc = new Command();
        ListTask listTask = new ListTask();
        try (FileWriter file = new FileWriter("task.json")) {
            file.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Digite --help para saber os comandos");

        while (true) {
            System.out.print(PREFIX + " ");  // Exibe o prefixo no prompt
            String input = scanner.nextLine();
            String[] inputArgs = (PREFIX + " " + input).split(" ");

            if (input.equalsIgnoreCase("exit")) {
                System.out.println(PREFIX + " Saindo... Até logo!");
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
                case "delete":
                    cc.delete(input);
                    break;
                case "mark-in-progress":
                    cc.updateTaskProgress(input);
                    break;
                case "mark-done":
                    cc.updateTaskProgressDone(input);
                    break;
                case "list":
                    cc.TaskList();
                    break;
                case "list-done":
                    listTask.listDone(Task.TaskStatus.DONE);
                    break;
                case "list-todo":
                    listTask.listDone(Task.TaskStatus.TODO);
                    break;
                case "list-in-progress":
                    listTask.listDone(Task.TaskStatus.IN_PROGRESS);
                    break;
                default:
                    help();
                    break;
            }
        }

    }

    private static void help() {
        System.out.println("add para adicionar tarefa");
        System.out.println("update para atualizar status da tarefa");
        System.out.println("delete para deletar a tarefa");
        System.out.println("list para listar as tarefas");
        System.out.println("list-done para listar tarefas ja feitas");
        System.out.println("list-todo para listar tarefas não feitas");
        System.out.println("list-in-progress para listar tarefas em andamento");

    }
}