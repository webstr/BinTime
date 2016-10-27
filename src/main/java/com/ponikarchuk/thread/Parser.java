package com.ponikarchuk.thread;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import static com.ponikarchuk.controller.CustomRestController.*;

public class Parser extends Thread {
    private Semaphore semaphore;
    private Scanner scanner;

    public Parser(Semaphore semaphore, Scanner scanner) {
        this.semaphore = semaphore;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (scanner.hasNext()) {
            try {
                semaphore.acquire();

                String str = scanner.next();
                synchronized (map) {
                    if (map.containsKey(str)) {
                        map.replace(str, map.get(str) + 1);
                    } else {
                        map.put(str, 1);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
