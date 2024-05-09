package org.fullstack4.springboot.repository;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.BaseEntity;
import org.fullstack4.springboot.domain.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testGetNow() {
        String now = boardRepository.getNow();
        log.info("===========================");
        log.info("now : " + now);
        log.info("===========================");
    }

    @Test
    public void testRegist() {
        log.info("===========================");
        log.info("BoardRepositoryTest >> testRegist Start");
        IntStream.rangeClosed(0,10)
                .forEach(i->{
                    BoardEntity bbs = BoardEntity.builder()
                            .user_id("test")
                            .title("테스트 제목" + i)
                            .content("테스트 제목" + i)
                            .display_date(new SimpleDateFormat("yyyy-MM-dd").format(
                                    new Date(2024-1900, 3, (i%10==0? 1 : i%10))).toString())
                            .build();
                    BoardEntity bbsResult = boardRepository.save(bbs);
                    log.info("result{} : " + bbsResult);
                });

        log.info("BoardRepositoryTest >> testRegist End");
        log.info("===========================");
    }
}
