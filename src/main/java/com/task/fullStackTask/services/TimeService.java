package com.task.fullStackTask.services;


import com.task.fullStackTask.Entities.Time;
import com.task.fullStackTask.Repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public void addTime(String time, String userId, HttpServletResponse response) {
        if(userId == null){
            userId = UUID.randomUUID().toString();
            response.addCookie(new Cookie("userId", userId));
        }
        Time newTime = new Time();
        newTime.setTime(time);
        newTime.setUserId(userId);
        timeRepository.save(newTime);

    }

    public Iterable<Time> getTimeByUserId(String userId) {
        if(userId != null){
            return timeRepository.findByUserId(userId);
        } else{
            return new ArrayList<>();
        }
    }

    public void removeTime(String userId, String time) {
        if(time != null){
            timeRepository.deleteByUserIdAndTime(userId, time);
        } else {
            timeRepository.deleteAllByUserId(userId);

        }
    }
}
