package com.dyzlg.network;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author larry
 * @version Created on 2014-3-15
 * 
 */
public class APIQueue {
	private final int HIGH_POOL_SIZE = 3;
	private final int NORMAL_POOL_SIZE = 3;
	private final int LOW_POOL_SIZE = 2;

	private final int HIGH_LEVEL = 0;
	private final int NORMAL_LEVEL = 1;
	private final int LOW_LEVEL = 2;

	// 可重用固定线程数的线程池
	private ThreadPoolExecutor mHighPool;
	private ThreadPoolExecutor mNormalPool;
	private ThreadPoolExecutor mLowPool;

	private static APIQueue mInstance;

	private APIQueue() {
		// 初始化所有
		mHighPool = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(HIGH_POOL_SIZE);
		mNormalPool = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(NORMAL_POOL_SIZE);
		mLowPool = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(LOW_POOL_SIZE);
	}

	public static APIQueue getInstance() {
		if (mInstance == null) {
			mInstance = new APIQueue();
		}
		return mInstance;
	}

	/**
	 * NORMAL_LEVEL
	 * @param runnable
	 */
	public void execute(Runnable runnable) {
		execute(runnable, NORMAL_LEVEL);
	}
	
	/**
	 * HIGH_LEVEL
	 * @param runnable
	 */
	public void executeHigh(Runnable runnable) {
		execute(runnable, HIGH_LEVEL);
	}
	
	/**
	 * LOW_LEVEL
	 * @param runnable
	 */
	public void executeLow(Runnable runnable) {
		execute(runnable, LOW_LEVEL);
	}

	private void execute(Runnable runnable, int priority) {
		if (runnable != null) {
			switch (priority) {
			case HIGH_LEVEL:
				if (mHighPool.getActiveCount() == HIGH_POOL_SIZE
						&& mLowPool.getActiveCount() < LOW_POOL_SIZE) {
					mLowPool.execute(runnable);
				} else if (mHighPool.getActiveCount() == HIGH_POOL_SIZE
						&& mNormalPool.getActiveCount() < NORMAL_POOL_SIZE) {
					mNormalPool.execute(runnable);
				} else {
					mHighPool.execute(runnable);
				}
				break;

			case NORMAL_LEVEL:
				mNormalPool.execute(runnable);
				break;

			case LOW_LEVEL:
				mLowPool.execute(runnable);
				break;
			}
		}
	}
}
