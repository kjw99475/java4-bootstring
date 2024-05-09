package org.fullstack4.springboot.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.domain.QBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    public BoardSearchImpl() {
        super(BoardEntity.class);
    }

    @Override
    public Page<BoardEntity> search(Pageable pageable) {
        log.info("=============================");
        log.info("BoardSearchImpl >> search START");

        QBoardEntity qBoard = QBoardEntity.boardEntity; //QBoardEntity 객체 생성
        JPQLQuery<BoardEntity> query = from(qBoard);    //SELECT ... FROM QboardEntity --> tbl_board

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        log.info("query : {}", query);
        List<BoardEntity> boards = query.fetch();
        int total = (int)query.fetchCount();    //기본 리턴타입 long

        log.info("boards : {}", boards);
        log.info("total : {}", total);

        log.info("BoardSearchImpl >> search END");
        log.info("=============================");
        return null;
    }
}
