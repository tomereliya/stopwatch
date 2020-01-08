package com.task.fullStackTask.Repositories;

import com.task.fullStackTask.Entities.Time;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface TimeRepository extends CrudRepository<Time, Integer> {

    @Query("from Time where userId=:uuid")
    Iterable<Time> findByUserId(String uuid);

    @Transactional
    @Modifying
    @Query("delete from Time where userId=:uuid and time=:time")
    void deleteByUserIdAndTime(String uuid, String time);

    @Transactional
    @Modifying
    @Query("delete from Time where userId=:uuid")
    void deleteAllByUserId(String uuid);

}
