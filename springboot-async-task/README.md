# 配置自定义线程池

在默认情况下，SpringBoot使用`SimpleAsyncTaskExecutor`这个线程池，但此线程不是真正意义上的线程池，因为线程不重用，每次调用都会创建一个新的线程。因此我们自己定义线程池。

[AsyncConfig](https://github.com/FeiChaoyu/SpringBoot-Tutorials/blob/master/AsyncTask/src/main/java/com/feichaoyu/async/config/AsyncConfig.java)



#  开启异步调用

启动类上或者配置类上加入`@EnableAsync`



# 异步任务接口

`@Async`标注在方法或类上，表示此任务是异步的。



# 异步回调

在某些情况下，我们想要获取异步任务的结果，就可以用到`Future`异步回调。

异步回调的结果用`AsyncResult`封装。