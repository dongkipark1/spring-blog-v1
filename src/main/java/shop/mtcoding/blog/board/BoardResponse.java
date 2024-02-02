package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

//bt.id, bt.content, bt.title, bt.created_at, bt.user_id, ut.username >> 순서에 맞게 들고오지 않는다. 반드시 뿌려서 확인 해야 한다
public class BoardResponse {

    @AllArgsConstructor
    @Data
    public static class DetailDTO{
        private Integer id;
        private String content;
        private String title;
        private Timestamp createdAt;
        private Integer userId;
        private String username;

    }
}
