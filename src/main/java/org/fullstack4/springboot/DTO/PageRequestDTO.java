package org.fullstack4.springboot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {
    @Builder.Default
        private int total_count = 0;
    @Builder.Default
        private int page = 1;
    @Builder.Default
        private int page_size = 10;
    @Builder.Default
        private int total_page = 1;
    @Builder.Default
        private int page_skip_count = 0;
    @Builder.Default
        private int page_block_size = 10;
    @Builder.Default
        private int page_block_start = 1;
    @Builder.Default
        private int page_block_end = 1;

        //검색용
        private String search_type;
        private String[] search_types;
        private String search_word;
        private String linkParams;  //return url용

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }
        public int getPage_skip_count() { return (this.page-1) * this.page_size; }
        public String[] getSearch_types() {
            if (search_type == null || search_type.isEmpty()) {
                return null;
            }
            return search_type.split("");
        }

        public PageRequest getPageable(String...props) {
            return PageRequest.of(this.page-1, this.page_size, Sort.by(props).descending());
        }
        public String getLinkParams() {
            if (this.linkParams == null || linkParams.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("page=" + this.page);
                sb.append("&page_size=" + this.page_size);

                if (this.search_type != null && !search_type.isEmpty()) {
                    sb.append("&search_type=" + this.search_type);
                }

                if (this.search_word != null && !search_word.isEmpty()) {
                    try {
                        sb.append(sb.append("&search_word=" + URLEncoder.encode(search_word, "UTF-8")));
                    } catch (UnsupportedEncodingException e) {

                    }
                }

                linkParams=sb.toString();
                if (linkParams == null) { linkParams="";}
            }

            return linkParams;
        }
}
