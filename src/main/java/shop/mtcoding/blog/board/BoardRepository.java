package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog._core.Constant;


import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public int count(){
        Query query = em.createNativeQuery("select count(*) from board_tb");

        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List<Board> findAll(int page){
        final int COUNT = 3; //final을 쓸 때는 대문자
       int value = page*COUNT;
       Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?,?", Board.class);
       query.setParameter(1, value);
       query.setParameter(2, Constant.PAGING_COUNT);

       List<Board> boardList = query.getResultList();
       return boardList;
    }

    public BoardResponse.DetailDTO findById(int id) {
        // ENTITY가 아닌 것은 JPA가 파싱안해준다? BOARD는 이 결과와 다르게 생겼으니까
        Query query = em.createNativeQuery("select bt.id, bt.title, bt.content, bt.created_at, bt.user_id, ut.username from board_tb bt inner join user_tb ut on bt.user_id = ut.id where bt.id = ?" ); //entity가 아닌 걸 받으려면 result set 해야하나 entity가 아닌 걸 파싱해주는 라이브러리 다운해야
        query.setParameter(1, id);

        //join할 때 담을 항아리가 만들기
        JpaResultMapper rm = new JpaResultMapper();
        BoardResponse.DetailDTO responseDTO = rm.uniqueResult(query, BoardResponse.DetailDTO.class);
        return responseDTO;

//        // 결과가 1개니까
//        query.getSingleResult();
    }
}
