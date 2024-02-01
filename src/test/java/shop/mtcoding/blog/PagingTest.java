package shop.mtcoding.blog;

import org.junit.jupiter.api.Test;

public class PagingTest {

    @Test
    public void count(){
        int totalCount = 6; // 게시글 개수
        int pagingCount = 3; // 페이지

        // 1. 나머지 여부 확인
        int remainCount = totalCount % pagingCount;
        System.out.println("나머지: " + remainCount);

        int totalPageCount = totalCount / pagingCount;
        System.out.println("몫: " + totalPageCount);
        // 2. 나머지가 있다면
        if (remainCount > 0){
            totalPageCount = totalPageCount + 1;
        }

        // 3. 나머지가 없다면
        if (remainCount == 0){

        }


        System.out.println("토탈페이지: " + totalPageCount);

    }
}
