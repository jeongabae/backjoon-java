import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> hs = new HashSet<>();

        //모든 전화번호를 hs에 저장
        for (String num : phone_book) {
            hs.add(num);
        }

        //각 번호 접두사 확인
        for (String num : phone_book) {
            for (int i=1; i<num.length(); i++) {
                String prefix = num.substring(0,i);
                //hs에 접두사 있으면 false
                if (hs.contains(prefix)) {
                    return false;
                }
            }
        }

        return true;
    }
}
//풀이 1 : 정렬
// import java.util.*;

// class Solution {
//     public boolean solution(String[] phone_book) {
//         Arrays.sort(phone_book);
            
//         for(int i=0;i<phone_book.length-1;i++){
//             if(phone_book[i+1].startsWith(phone_book[i])){
//                 return false;
//             }
//         }
//         return true;
//     }
// }