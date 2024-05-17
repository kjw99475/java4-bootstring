package org.fullstack4.springboot.service;

import org.fullstack4.springboot.DTO.BoardReplyDTO;
import org.fullstack4.springboot.DTO.PageRequestDTO;
import org.fullstack4.springboot.DTO.PageResponseDTO;

public interface BoardReplyServiceIf {
    int regist(BoardReplyDTO replyDTO);
    PageResponseDTO<BoardReplyDTO> getListOfReply(int bbs_idx, PageRequestDTO pageRequestDTO);
}
