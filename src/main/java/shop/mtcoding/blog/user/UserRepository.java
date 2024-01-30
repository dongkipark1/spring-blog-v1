package shop.mtcoding.blog.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository //IOC에 뜬다 내가 NEW할 필요가 없음
public class UserRepository { //UserRepository가 new가 된다

    private EntityManager em; //NULL이 아님

    public UserRepository(EntityManager em) { //IoC에서 가져다 쓰면 됨 DI(의존성 주입)
        this.em = em;
    }

    @Transactional
    public void save(UserRequest.JoinDTO requestDTO){
       Query query = em.createNativeQuery("insert into user_tb(username, password, email) values(?, ?, ?)");
       query.setParameter(1 , requestDTO.getUsername());
       query.setParameter(2 , requestDTO.getPassword());
       query.setParameter(3 , requestDTO.getEmail());

       query.executeUpdate();
    }

    @Transactional
    public void saveV2(UserRequest.JoinDTO requestDTO){
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());

        em.persist(user);
    }
}
