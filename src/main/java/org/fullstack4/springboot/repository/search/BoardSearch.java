package org.fullstack4.springboot.repository.search;

import org.fullstack4.springboot.domain.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<BoardEntity> search(Pageable pageable);
    Page<BoardEntity> search2(Pageable pageable, String[] types, String search_keyword);


}
