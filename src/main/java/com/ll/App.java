package com.ll;

import com.ll.domain.system.controller.SystemController;
import com.ll.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private final WiseSayingController wiseSayingController;
    private final SystemController systemController;


    public App(Scanner scanner) {
        this.scanner = scanner;
        this.wiseSayingController = new WiseSayingController(scanner);
        this.systemController = new SystemController();
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Command command = new Command(cmd);
            switch (command.getActionName()) {
                case "등록" -> wiseSayingController.actionAdd();
                case "목록" -> wiseSayingController.actionList();
                case "삭제" -> wiseSayingController.actionDelete(command);
                case "수정" -> wiseSayingController.actionModify(command);
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
                default -> System.out.println("다시 입력하세요.");
            }
        }
    }
}
