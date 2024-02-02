package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog._core.PagingUtil;
import shop.mtcoding.blog.user.User;


import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;


    // 기본 mvc 패턴
    // http://localhost:8080?page=0
    @GetMapping({"/", "/board"})
    public String index(HttpServletRequest request , @RequestParam(defaultValue = "0") int page){
        System.out.println("페이지: " + page);
        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList);


        int currentPage = page;
        int nextPage = currentPage + 1;
        int prevPage = currentPage - 1;
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("prevPage", prevPage);

        boolean first = PagingUtil.isFirst(currentPage);
        boolean last = PagingUtil.isLast(currentPage, boardRepository.count());

        request.setAttribute("first" , first);
        request.setAttribute("last" , last);



        return "index";
    }

    // vc 패턴
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id , HttpServletRequest request) {   // @PathVariable : {}안에 있는 내용을 파싱한다 //  HttpServletRequest request : 서블릿에서 가방을 불러온다

        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id); // 가방에 담으면 끝
        request.setAttribute("board", responseDTO);
        return "board/detail";
    }
}
