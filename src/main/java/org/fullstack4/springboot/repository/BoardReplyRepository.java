package org.fullstack4.springboot.repository;

import org.apache.ibatis.annotations.Param;
import org.fullstack4.springboot.domain.BoardReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardReplyRepository extends JpaRepository<BoardReplyEntity, Integer >  {
    @Query (value = "SELECT r FROM BoardReplyEntity r WHERE r.board_idx = :bbs_idx")
    Page<BoardReplyEntity> listOfBoardReply(@Param("bbs_idx") Integer bbs_idx, Pageable pageable);

}
