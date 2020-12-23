package com.example.springbatchexample.batch;

import com.example.springbatchexample.bean.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User,User> {
    private static final Map<String,String> DEPT_NAMES=new HashMap<>();
    public Processor(){
        DEPT_NAMES.put("001","Tech");
        DEPT_NAMES.put("002","Management");
        DEPT_NAMES.put("003","Tester");
    }
    @Override
    public User process(User user) throws Exception {
        user.setDept(DEPT_NAMES.get(user.getDept()));
        return user;
    }
}
