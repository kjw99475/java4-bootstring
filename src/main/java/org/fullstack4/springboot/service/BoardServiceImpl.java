package org.fullstack4.springboot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.DTO.BoardDTO;
import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardServiceIf{
    private  final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    public int regist(BoardDTO boardDTO) {
        BoardEntity board = modelMapper.map(boardDTO, BoardEntity.class);
        int idx = boardRepository.save(board).getIdx();
        return idx;
    }

    @Override
    public BoardDTO view(int idx) {
        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity board = result.orElse(null);
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<BoardEntity> result = boardRepository.findById(boardDTO.getIdx());
        BoardEntity board = result.orElse(null);
        board.modify(board.getUser_id(), boardDTO.getTitle(),boardDTO.getContent(), boardDTO.getDisplay_date());
        boardRepository.save(board);
    }

    @Override
    public void delete(BoardDTO boardDTO) {

    }
}
