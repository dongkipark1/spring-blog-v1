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
    // IOC 컨테이너에 세션에 접근할 수 있는 변수가 들어가 있음. 의존성주입 하면 됨
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

        // 권한을 check 하는것
        // 1. 해당 페이지의 주인 여부
        boolean owner = false;

        // 2. 작성자 userId 확인하기
        int boardUserId = responseDTO.getUserId();


        // 3. 로그인 여부 체크 (로그인이 안됐으면 무조건 false)
        User sessionUser = (User) session.getAttribute("sessionUser"); // 제네릭은 이미 NEW가 되었다면 제네릭(내가 타입을 결정)을 쓸 수없다
        if (sessionUser != null){ // 로그인 함
            if (boardUserId == sessionUser.getId()){ // 로그인을 했다는 체크를 안하면 nullpointer익셉션 터짐
                owner = true;
            }
        }
        request.setAttribute("owner", owner);

        return "board/detail";




    }
}
