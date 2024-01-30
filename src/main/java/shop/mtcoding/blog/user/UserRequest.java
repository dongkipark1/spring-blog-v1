package shop.mtcoding.blog.user;

import lombok.Data;

/**
 * 요청 DTO = DATA TRANSFER OBJECT 데이터 전송 오브젝트
 * 클라이언트로 부터 통신으로 전달받는 데이터 오브젝트
 */
public class UserRequest {

    @Data //getter setter 다들고 있는 어노테이션
    public static class JoinDTO{
        private String username;
        private String password;
        private String email;
    }

    @Data //getter setter 다들고 있는 어노테이션
    public static class LoginDTO{
        private String username;
        private String password;
    }
}
