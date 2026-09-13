package com.github.ilovejackylee.name100women;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/** Offline recall practice CLI for the Name 100 Women challenge. */
public final class Name100WomenCli {

    public static final String PLAY_URL = "https://nameahundred.com/women/";
    public static final String VERSION = "0.1.0";
    private static final int DEFAULT_GOAL = 100;

    private Name100WomenCli() {
    }

    public static void main(String[] args) {
        System.exit(run(args));
    }

    public static int run(String[] args) {
        boolean web = false;
        boolean printUrl = false;
        int goal = DEFAULT_GOAL;

        for (String arg : args) {
            if ("--web".equals(arg)) {
                web = true;
            } else if ("--print-url".equals(arg)) {
                printUrl = true;
            } else if ("--help".equals(arg) || "-h".equals(arg)) {
                printHelp();
                return 0;
            } else if ("--version".equals(arg) || "-V".equals(arg) || "-v".equals(arg)) {
                System.out.println(VERSION);
                return 0;
            } else if (arg.startsWith("--goal=")) {
                goal = Integer.parseInt(arg.substring("--goal=".length()));
            } else {
                System.err.println("Unknown option: " + arg);
                printHelp();
                return 1;
            }
        }

        if (printUrl) {
            System.out.println(PLAY_URL);
            return 0;
        }
        if (web) {
            return openWeb();
        }
        return runPractice(goal);
    }

    private static void printHelp() {
        System.out.println(
                "Usage: name100-women [options]\n"
                        + "\n"
                        + "  --print-url   Print the Name 100 Women challenge URL\n"
                        + "  --web         Print and open the online challenge\n"
                        + "  --goal=N      Offline practice target (default 100)\n"
                        + "  --version     Show version\n"
                        + "  --help        Show this help\n");
    }

    private static int openWeb() {
        System.out.println(PLAY_URL);
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(URI.create(PLAY_URL));
            }
        } catch (IOException | UnsupportedOperationException ex) {
            System.err.println("Could not open browser: " + ex.getMessage());
        }
        return 0;
    }

    private static int runPractice(int goal) {
        System.out.println("Name 100 Women — offline practice");
        System.out.println("Online (Wikidata-checked): " + PLAY_URL);
        System.out.println();
        System.out.println("Type a woman's name and press Enter. Aim for " + goal + ".");
        System.out.println("Empty line = finish. Ctrl+C = quit.");
        System.out.println();

        List<String> names = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        long started = System.nanoTime();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (names.size() < goal) {
                System.out.printf("[%d/%d] > ", names.size(), goal);
                String raw = reader.readLine();
                if (raw == null) {
                    break;
                }
                raw = raw.trim();
                if (raw.isEmpty()) {
                    break;
                }
                String key = raw.toLowerCase(Locale.ROOT);
                if (!seen.add(key)) {
                    System.out.println("  (already listed)");
                    continue;
                }
                names.add(raw);
            }
        } catch (IOException ex) {
            System.out.println();
        }

        double elapsed = (System.nanoTime() - started) / 1_000_000_000.0;
        System.out.println();
        System.out.printf("Named %d · %s%n", names.size(), formatElapsed(elapsed));
        if (!names.isEmpty()) {
            System.out.println("Your list:");
            for (int i = 0; i < names.size(); i++) {
                System.out.printf("  %d. %s%n", i + 1, names.get(i));
            }
        }
        System.out.println();
        System.out.println("Play the checked round online → " + PLAY_URL);
        return 0;
    }

    private static String formatElapsed(double seconds) {
        int sec = (int) Math.round(seconds);
        int mm = sec / 60;
        int ss = sec % 60;
        return mm > 0 ? String.format("%d:%02d", mm, ss) : sec + " sec";
    }
}
