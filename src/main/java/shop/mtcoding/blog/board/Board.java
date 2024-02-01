package shop.mtcoding.blog.board;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data //getter, setter, tostring
@Entity
@Table(name = "board_tb")
public class Board {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;
    private String title; // 데이터가 1개여도 쪼갤 가능성이 있다 익명게시판 사실 컬럼이 1개있는 경우는 없다
    private String content; // 외부키에는 제약조건을 넣지 않는게 좋다
    private int userId; // 카멜표기로 쓰면 '_' 만들어 진다

    @CreationTimestamp
    private LocalDateTime createdAt;

}
