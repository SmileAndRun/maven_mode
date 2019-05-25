package com.hws.oa.core.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class MyThreadPoolExecutor  {

	private final BlockingQueue<Runnable> workQueue;              //任务缓存队列，用来存放等待执行的任务
	private volatile long  keepAliveTime;    //线程存活时间   
	private volatile TimeUnit unit; 		 //keepAliveTime的单位
	private volatile int   corePoolSize;     //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
	private volatile int   maximumPoolSize;   //线程池最大能容忍的线程数
	private volatile RejectedExecutionHandler handler; //任务拒绝策略
	private  volatile ThreadPoolExecutor threadPoolExecutor;
	public static MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor();
	public MyThreadPoolExecutor(){
		this.workQueue = new LinkedBlockingDeque<>(10);
		this.keepAliveTime = 5;
		this.unit = TimeUnit.SECONDS;
		this.corePoolSize = 5;
		this.maximumPoolSize = 10;
		this.handler =  new ThreadPoolExecutor.AbortPolicy();
	}
	
	public MyThreadPoolExecutor(BlockingQueue<Runnable> workQueue,
			long keepAliveTime, TimeUnit unit, int corePoolSize,
			int maximumPoolSize, RejectedExecutionHandler handler) {
		super();
		this.workQueue = workQueue;
		this.keepAliveTime = keepAliveTime;
		this.unit = unit;
		this.corePoolSize = corePoolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.handler = handler;
	}
	private boolean isStoped(){
		boolean flag = true;
		if(null != threadPoolExecutor){
			if(!threadPoolExecutor.isShutdown())flag = false;
		}
		return flag;
	}
	public void initThreadPoolExcutor(){
		if(isStoped())
		threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
	}
	
	public void initThreadPoolExcutor(int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler){
		if(isStoped())
		threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}

	public synchronized ThreadPoolExecutor getThreadPoolExecutor() {
	
		if(null == threadPoolExecutor)initThreadPoolExcutor();
		return threadPoolExecutor;
	}
	
}
