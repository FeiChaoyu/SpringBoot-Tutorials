package com.feichaoyu.schedule.service.impl;

import com.feichaoyu.schedule.service.ScheduleService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author feichaoyu
 * @Date 2019/7/25
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    // 计数器
    int count1 = 1;
    int count2 = 1;

    // 每隔1秒执行一次
    @Scheduled(fixedRate = 1000)
    @Async
    @Override
    public void job1() {
        System.out.println("【" + Thread.currentThread().getName() + "】"
                + "【job1】每秒钟执行一次，执行第【" + count1 + "】次");
        count1++;
    }

    // 每隔1秒执行一次
    @Scheduled(fixedRate = 1000)
    @Async
    @Override
    public void job2() {
        System.out.println("【" + Thread.currentThread().getName() + "】"
                + "【job2】每秒钟执行一次，执行第【" + count2 + "】次");
        count2++;
    }

    int count3 = 1;
    int count4 = 1;

    // Spring IoC容器初始化后，第一次延迟3秒，每隔1秒执行一次
    @Scheduled(initialDelay = 3000, fixedRate = 1000)
    @Async
    @Override
    public void job3() {
        System.out.println("【" + Thread.currentThread().getName() + "】"
                + "【job3】每秒钟执行一次，执行第【" + count3 + "】次");
        count3++;
    }

    // 20:00到20:59点每一分钟执行一次
    @Scheduled(cron = "0 * 20 * * ?")
    @Async
    @Override
    public void job4() {
        System.out.println("【" + Thread.currentThread().getName()
                + "】【job4】每分钟执行一次，执行第【" + count4 + "】次");
        count4++;
    }
}
