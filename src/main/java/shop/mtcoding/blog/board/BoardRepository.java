package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog._core.Constant;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public int count(){
        Query query = em.createNativeQuery("select count(*) from board_tb");

        BigInteger count = (BigInteger) query.getSingleResult();
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
}
