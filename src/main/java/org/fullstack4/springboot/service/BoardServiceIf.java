package org.fullstack4.springboot.service;

import org.fullstack4.springboot.DTO.BoardDTO;

public interface BoardServiceIf {
    int regist(BoardDTO boardDTO);
    BoardDTO view(int idx);
    void modify(BoardDTO boardDTO);
    void delete(BoardDTO boardDTO);

}
