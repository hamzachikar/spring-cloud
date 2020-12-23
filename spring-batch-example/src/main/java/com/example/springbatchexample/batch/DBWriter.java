package com.example.springbatchexample.batch;

import com.example.springbatchexample.bean.User;
import com.example.springbatchexample.repository.UserRepo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<User> {
    @Autowired
    private UserRepo repo;
    @Override
    public void write(List<? extends User> users) throws Exception {
        System.out.println("data saving writter saving ......");
        for(User user:users){
            repo.save(user);
        }
        System.out.println("done");
    }
}
