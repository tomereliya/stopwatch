package com.task.fullStackTask.controllers;

import com.task.fullStackTask.Entities.Time;
import com.task.fullStackTask.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("api")
public class TimerController {

    @Autowired
    TimeService timeService;


    @PostMapping(value = "/add",produces = MediaType.TEXT_PLAIN_VALUE)
    public String addNewTime(@RequestParam String time, @CookieValue(value = "userId",required = false) String userId, HttpServletResponse response ){
        timeService.addTime(time,userId,response);

        return time;
    }

    @GetMapping(value = "/times",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Time> getAllTimes(@CookieValue(value = "userId",required = false) String userId){
       return timeService.getTimeByUserId(userId);
    }

    @DeleteMapping(value = "/remove",produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeTime(@CookieValue(value = "userId",required = false) String userId, @RequestParam(value = "time",required = false) String time){
        timeService.removeTime(userId, time);
    }

}
