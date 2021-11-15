import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class Demo {
    @Test
    public  void demo()  {
//        UserMapper mapper = MapperProxyFactory.getMapper(UserMapper.class);
//        List<User> users = mapper.selectAll();
//        User select = mapper.select();
//        int update = mapper.update();
//        int delete = mapper.delete();
//        int insert = mapper.insert();
//        System.out.println(select.toString());


        List<Integer> integers = selfDividingNumbers(1, 22);
        for (int a : integers
             ) {
            System.out.println(a);
        }
    }


    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for(int i = left;i <= right;i++){
            int j = i;
            while(j!=0){
                int x = j%10;
                if(x!=0&&i%x==0){
                    j = j/10;
                    if(j == 0){
                        list.add(i);
                        break;
                    }
                }else break;


            }
        }
        return list;
    }

}
