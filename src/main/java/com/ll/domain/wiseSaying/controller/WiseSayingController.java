package com.ll.domain.wiseSaying.controller;

import com.ll.Command;
import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.service.WiseSayingService;

import java.util.Optional;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
        this.wiseSayingService = new WiseSayingService();
    }

    public void actionAdd() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        WiseSaying wiseSaying = wiseSayingService.add(content, author);
        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------");
        for (WiseSaying wiseSaying : wiseSayingService.findAll().reversed()) {
            System.out.println(wiseSaying.getId() + " / " +
                    wiseSaying.getAuthor() + " / " +
                    wiseSaying.getContent());
        }
    }

    public void actionDelete(Command command) {
        int id = command.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id(숫자)를 입력해주세요");
            return;
        }
        boolean removed = wiseSayingService.deleteById(id);
        if (!removed) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    public void actionModify(Command command) {
        int id = command.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id(숫자)를 입력해주세요.");
            return;
        }
        Optional<WiseSaying> opWiseSaying = wiseSayingService.findById(id);
        if (opWiseSaying.isPresent()) {
            WiseSaying wiseSaying = opWiseSaying.get();
            System.out.println("명언(기존) : " + wiseSaying.getContent());
            System.out.print("명언 : ");
            String newContent = scanner.nextLine();

            System.out.println("작가(기존) : " + wiseSaying.getAuthor());
            System.out.print("작가 : ");
            String newAuthor = scanner.nextLine();

            wiseSayingService.modify(wiseSaying, newContent, newAuthor);
        }

    }
}
