package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository //IOC에 뜬다 내가 NEW할 필요가 없음
public class UserRepository { //UserRepository가 new가 된다

    private EntityManager em; //NULL이 아님

    public UserRepository(EntityManager em) { //IoC에서 가져다 쓰면 됨 DI(의존성 주입)
        this.em = em;
    }

    @Transactional  // 다른 메서드를 호출 할 일이 없어서 독립적인 트랜잭션으로 건다
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

//    @Transactional //select시 필요없음
    public User findByUsernameandPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("select * from user_tb where username=? and password=?", User.class);
        query.setParameter(1 , requestDTO.getUsername());
        query.setParameter(2 , requestDTO.getPassword());

        try {
            User user = (User) query.getSingleResult(); // 내가 만든 코드가 아님 직접 try ~ catch 해야
            return user;
        }catch (Exception e){
            return null;
        } // 내부적으로 터지면 찾아야 한다
    }

    public User findByUsername(String username) {
        Query query = em.createNativeQuery("select * from user_tb where username=? and password=?", User.class);
        query.setParameter(1 , username);

        try {
            User user = (User) query.getSingleResult(); // 내가 만든 코드가 아님 직접 try ~ catch 해야
            return user;
        }catch (Exception e){
            return null;
        } // 내부적으로 터지면 찾아야 한다
    }
}
