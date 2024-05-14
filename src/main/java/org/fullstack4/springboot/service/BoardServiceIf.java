package org.fullstack4.springboot.service;

import org.fullstack4.springboot.DTO.BoardDTO;
import org.fullstack4.springboot.DTO.PageRequestDTO;
import org.fullstack4.springboot.DTO.PageResponseDTO;

public interface BoardServiceIf {
    int regist(BoardDTO boardDTO);
    BoardDTO view(int idx);
    void modify(BoardDTO boardDTO);
    void delete(int idx);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

}
