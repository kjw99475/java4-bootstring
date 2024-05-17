package org.fullstack4.springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.DTO.BoardReplyDTO;
import org.fullstack4.springboot.DTO.PageRequestDTO;
import org.fullstack4.springboot.DTO.PageResponseDTO;
import org.fullstack4.springboot.domain.BoardReplyEntity;
import org.fullstack4.springboot.repository.BoardReplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardReplyServiceImpl implements BoardReplyServiceIf{

    private final BoardReplyRepository boardReplyRepository;
    private final ModelMapper modelMapper;
    @Override
    public int regist(BoardReplyDTO replyDTO) {
        BoardReplyEntity reply = modelMapper.map(replyDTO, BoardReplyEntity.class);
        int result = boardReplyRepository.save(reply).getIdx();
        return result;
    }

    @Override
    public PageResponseDTO<BoardReplyDTO> getListOfReply(int bbs_idx, PageRequestDTO pageRequestDTO) {

        PageRequest pageable = PageRequest.of(
                (pageRequestDTO.getPage()<0? 0 : pageRequestDTO.getPage()-1),
                pageRequestDTO.getPage_size(),
                Sort.by("idx").ascending()
        );
                Page<BoardReplyEntity> replyResult =
                        boardReplyRepository.listOfBoardReply(bbs_idx, pageable);
        List<BoardReplyDTO> dtoList = replyResult.getContent().stream()
                .map(reply -> modelMapper.map(reply, BoardReplyDTO.class))
                .collect(Collectors.toList());
        return PageResponseDTO.<BoardReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total_count((int) replyResult.getTotalElements())
                .build();
    }
}
