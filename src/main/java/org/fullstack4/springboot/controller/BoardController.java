package org.fullstack4.springboot.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.DTO.BoardDTO;
import org.fullstack4.springboot.DTO.PageRequestDTO;
import org.fullstack4.springboot.DTO.PageResponseDTO;
import org.fullstack4.springboot.service.BoardServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.model.IModel;

@Log4j2
@Controller
@RequiredArgsConstructor    //의존성 주입. 오류날 수 있음. 안쓸거면 대신 @Autowired..
@RequestMapping("/board")
public class BoardController {
    private final BoardServiceIf boardService;

    @GetMapping("/list")
    public void list (
        PageRequestDTO pageRequestDTO,
            Model model
    ) {
        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);

        log.info("pageResponseDTO : {}", pageResponseDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
    }

    @GetMapping("/view")
    public void view (
            int idx,
            PageRequestDTO pageRequestDTO,
            Model model
    ) {
        log.info("========================");
        log.info("BoardController view Start");

        BoardDTO boardDTO = boardService.view(idx);
        model.addAttribute("dto", boardDTO);

        log.info("BoardController view End");
        log.info("========================");
    }


    @RequestMapping(value = "/regist", method = {RequestMethod.GET})
    public void registGET(Model model) {
    }

    @RequestMapping(value = "/regist", method = {RequestMethod.POST})
    public String registPOST(
            @Valid BoardDTO boardDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            log.info("BoardController >> registPost ERROR");
            redirectAttributes.addFlashAttribute("errors", bindingResult);
            return "redirect:/board/regist";
        }
        int result_idx = boardService.regist(boardDTO);
        redirectAttributes.addFlashAttribute("result_idx", result_idx);

        log.info("boardDTO : {}", boardDTO);
        log.info("result_idx : ", result_idx);
        return "redirect:/board/list";
    }


    @RequestMapping(value = "/modify", method = {RequestMethod.GET})
    public void modifyGET (int idx,
                           PageRequestDTO pageRequestDTO,
                           Model model
    ) {
        BoardDTO boardDTO = boardService.view(idx);
        model.addAttribute("dto", boardDTO);
    }

    @RequestMapping(value = "/modify", method = {RequestMethod.POST})
    public String modifyPOST (@Valid BoardDTO boardDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Model model
    ) {
        if (bindingResult.hasErrors()) {
            log.info("BoardController >> modifyPost ERROR");
            redirectAttributes.addFlashAttribute("errors", bindingResult);
            return "redirect:/board/modify";
        }

        boardService.modify(boardDTO);
        log.info("=================BOARD DTO"+ boardDTO);
        return "redirect:/board/view?idx="+boardDTO.getIdx();
    }
}
