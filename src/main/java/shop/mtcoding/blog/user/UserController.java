package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 컨트롤러
 * 1. 요청받기 (URL - URI 포함(주소)) L: LOCATION, I: IDENTIFY
 * 2. 데이터(HTTP BODY)는 어떻게? DTO로 받는다
 * 3. 기본 MIME 전략: X-WWW-FORM-URLENCODED (USERNAME=SSAR&PASSWORD=1234)
 * 4. 유효성 검사하기 (BODY 데이터가 있다면)
 * 5. 클라이언트가 VIEW(MUSTACHE)만 원하는지? 혹은 DB 처리 후 VIEW도 원하는지
 * 6. VIEW만 있다면 VIEW를 응답하면 끝
 * 7. DB처리를 원하면 MODEL(DAO)에게 위임(MODEL에게는 클라이언트의 ID PASSWORD가 중복이 되었는지 조회) 후 VIEW를 응답하면 끝 -
 */

@Controller
public class UserController {

    private UserRepository userRepository; // null

    public UserController(UserRepository userRepository) { //IoC컨테이너에서 찾으니 있음
        System.out.println("풀 생성자 userController");
        this.userRepository = userRepository;

    }

    @PostMapping("/join") // 1
    public String join(UserRequest.JoinDTO requestDTO){ // 2
        System.out.println(requestDTO);
        // DB INSERT

        //1. 유효성 검사
        if (requestDTO.getUsername().length() < 3){
            return "error/400";
        }

        //2. MODEL에게 위임하기

        userRepository.saveV2(requestDTO);

        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
