package org.fullstack4.springboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.domain.QBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

        //쿼리 내부에서 파라미터 입력
        //and로 묶이는 경우
//        query.where(qBoard.title.contains("제목"));
//        query.where(qBoard.title.like("제목"));

        //or로 묶는 경우 (where 괄호로 묶는 경우)
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(qBoard.title.contains("제목"));
        booleanBuilder.or(qBoard.content.contains("제목"));
        query.where(booleanBuilder);

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        log.info("query : {}", query);
        List<BoardEntity> boards = query.fetch();
        int total = (int)query.fetchCount();    //기본 리턴타입 long

        log.info("boards : {}", boards);
        log.info("total : {}", total);

        log.info("BoardSearchImpl >> search END");
        log.info("=============================");

        return new PageImpl<>(boards, pageable, total);
    }

    @Override
    public Page<BoardEntity> search2(Pageable pageable, String[] types, String search_keyword) {
        QBoardEntity qBoard = QBoardEntity.boardEntity;
        JPQLQuery<BoardEntity> query = from(qBoard);

        if (types != null && types.length > 0 && search_keyword != null && search_keyword != ""){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            //type: t=제목, c=내용, w=사용자아이디
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(qBoard.title.like( "%"+search_keyword+ "%" ));
                    case "c":
                        booleanBuilder.or(qBoard.content.like( "%"+search_keyword+ "%" ));
                    case "w":
                        booleanBuilder.or(qBoard.user_id.like( "%"+search_keyword+ "%" ));
                }
            }
            query.where(booleanBuilder);
        }
        this.getQuerydsl().applyPagination(pageable, query);

        log.info("query : {}", query);
        List<BoardEntity> boards = query.fetch();
        int total = (int)query.fetchCount();    //기본 리턴타입 long

        log.info("boards : {}", boards);
        log.info("total : {}", total);

        return new PageImpl<>(boards, pageable, total);
    }
}
