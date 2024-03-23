package com.webvoyager;

import java.util.Scanner;

public class Main {

    /**
     * Task examples:
     * 1. What time is it?
     * 2. What is the latest Sam Altman tweet?
     * 3. Show me Dune 2 movie trailer
     */
    public static void main(String[] args) {

        var webVoyager = new WebVoyager();
        var scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter a task (or type 'exit' to quit):");

            var task = scanner.nextLine();

            if ("exit".equalsIgnoreCase(task)) {
                break;
            }

            var result = webVoyager.execute(task);
            System.out.println(result);
        }
    }


}