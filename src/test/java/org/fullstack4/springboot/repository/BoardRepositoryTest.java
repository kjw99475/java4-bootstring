package org.fullstack4.springboot.repository;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.BaseEntity;
import org.fullstack4.springboot.domain.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
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

    @Test
    public void testView() {
        int idx = 1;
        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = result.orElse(null);
//        result.get(); // 값이 없으면 NoSuchElementException 발생 //예외처리 예시
//        if (result.isPresent()) {  } else {throw new IllegalArgumentException(); }
//        result.orElseThrow(IllegalArgumentException :: new);
//        result.orElseThrow(() -> new IllegalArgumentException("no find data"));
//        result.orElseGet(BoardEntity::new);
//        result.ifPresent(b->{log.info("result{} : ", b );});
        log.info("===========================");
        log.info("BoardRepositoryTest >> testView Start");
        log.info("bbs : {}" , bbs);
        log.info("BoardRepositoryTest >> testView End");
        log.info("===========================");
    }

    @Test
    public void testModify() {
        int idx = 1;
        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = BoardEntity.builder()
                        .idx(idx)
                        .user_id("test")
                        .title("제목 수정 1")
                        .content("내용 수정 1")
                        .display_date(
                                new SimpleDateFormat("yyyy-MM-dd")
                                        .format(new Date()).toString()
                        )
                        .build();
        boardRepository.save(bbs);

//        bbs.modify("test", "제목 수정 1", "내용 수정 1", "2024-05-15");
//        boardRepository.save(bbs);

        log.info("===========================");
        log.info("BoardRepositoryTest >> testModify Start");
        log.info("bbs : {}" , bbs);
        log.info("BoardRepositoryTest >> testModify End");
        log.info("===========================");
    }


    @Test
    public void testDelete() {
        int idx = 1;
        boardRepository.deleteById(idx);
    }

    @Test
    public void testDelete2() {
        int idx = 1;
//        boardRepository.delete
    }

    @Test
    public void testList() {
        log.info("===========================");
        log.info("BoardRepositoryTest >> testList Start");

        PageRequest pageable = PageRequest.of(1, 10, Sort.by("idx").descending());
        boardRepository.search(pageable);

        log.info("BoardRepositoryTest >> testList End");
        log.info("===========================");
    }

    @Test
    public void testSearch() {
        log.info("===========================");
        log.info("BoardRepositoryTest >> testSearch Start");

        PageRequest pageable = PageRequest.of(1, 10, Sort.by("idx").descending());
        boardRepository.search(pageable);

        log.info("BoardRepositoryTest >> testSearch End");
        log.info("===========================");
    }
}
