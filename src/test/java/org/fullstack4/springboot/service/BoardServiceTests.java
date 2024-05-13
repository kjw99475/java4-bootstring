package org.fullstack4.springboot.service;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.DTO.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class BoardServiceTests {
    @Autowired
    private BoardServiceIf boardService;

    @Test
    public void testRegist() {
        log.info("===========================");
        log.info("BoardServiceTest >> testRegist Start");

        log.info("boardService.getClass().getName() : {}",
                boardService.getClass().getName()
        );
        BoardDTO boardDTO = BoardDTO.builder()
                .user_id("test")
                .title("제목테스트")
                .content("내용테스트")
                .display_date("2024-05-13")
                .build();
        int result = boardService.regist(boardDTO);

        log.info("boardDTO : {}", boardDTO);
        log.info("result : {}", result);

        log.info("BoardServiceTest >> testRegist End");
        log.info("===========================");
    }

    @Test
    public void testModify() {
        log.info("===========================");
        log.info("BoardServiceTest >> testModify Start");

        BoardDTO boardDTO = BoardDTO.builder()
                .idx(9)
                .user_id("test")
                .title("제목테스트 37")
                .content("내용테스트 37")
                .display_date("2024-05-13")
                .build();

        boardService.modify(boardDTO);

        log.info("BoardServiceTest >> testModify End");
        log.info("===========================");
    }

    @Test
    public void testView() {
        log.info("===========================");
        log.info("BoardServiceTest >> testView Start");

        int idx = 9;
        BoardDTO boardDTO = boardService.view(idx);
        log.info("boardDTO : {} ", boardDTO);

        log.info("BoardServiceTest >> testView End");
        log.info("===========================");
    }
}
