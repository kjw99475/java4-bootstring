package org.fullstack4.springboot.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Data
public class PageResponseDTO<E> {
    private int total_count;
    private int page;
    private int page_size;
    private int total_page;
    private int page_skip_count;
    private int page_block_size;
    private int page_block_start;
    private int page_block_end;

    //검색용
    private String search_type;
    private String[] search_types;
    private String search_word;
    private String linkParams;  //return url용
    
    private boolean prev_page_flag;
    private boolean next_page_flag;
    
    List<E> dtoList;
    
    PageResponseDTO() {}
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total_count) {
        log.info("=====================================");
        log.info("PageResponseDTO Start");

        this.total_count = total_count;
        this.page = pageRequestDTO.getPage();
        this.page_size = pageRequestDTO.getPage_size();
        this.total_page = (this.total_count > 0 ?
                (int)Math.ceil(this.total_count / (double)this.page_size) : 1 );
        this.page_skip_count = (this.page-1) * this.page_size;
        this.page_block_size = pageRequestDTO.getPage_block_size();
        this.page_block_start = pageRequestDTO.getPage_block_start();
        this.page_block_end = pageRequestDTO.getPage_block_end();
        this.prev_page_flag = (this.page_block_start > 1);
        this.next_page_flag = (this.total_page > this.page_block_end);
        this.dtoList = dtoList;

        this.linkParams = "?page_size="+this.page_size;

        log.info("total_count : {}", total_count);
    }
    public int getTotal_page() {
        return (this.total_count > 0 ? (int)Math.ceil(this.total_count/(double)this.page_size) : 1);
    }
    public int getPage_skip_count() {
        return (this.page -1 ) *this.page_size;
    }
    public void setPage_block_start () {
        this.page_block_start = (int)Math.floor(this.total_count/(double)this.page_size)
                *this.page_block_size + 1;
    }

    public void setPage_block_end () {
        this.page_block_end = (
                (int)Math.floor(this.total_count/(double)this.page_size)
                        *this.page_block_size)
                + this.page_block_size;

    }
}
