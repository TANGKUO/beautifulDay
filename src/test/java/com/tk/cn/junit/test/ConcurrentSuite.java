package com.tk.cn.junit.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.Runner;
import org.junit.runners.ParentRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.junit.runners.model.RunnerScheduler;

/**
 * 
 * @ClassName: ConcurrentSuite
 * @Description: (自定义聚合多个类进行多线程执行的Runner, 有时我们需要聚合同一个模块的测试类，
 *               如果使用@RunWith(Suite.class)@SuiteClasses({A.class,B.class})，
 *               当类较多时，需要一一列举，效率不高； 可以使用ClasspathSuite，支持过滤，将类名符合一定规则的类聚合)
 * @author tangkuo
 * @date 2017年6月28日 下午10:26:19
 *
 */
public class ConcurrentSuite extends Suite {

	/**
	 * 
	* @Title: MulThread
	* @Description: (方法级别多线程并发执行)
	* @param  runner Runner
	* @return Runner    返回类型
	* @throws
	 */
	public static Runner MulThread(Runner runner) {
		if (runner instanceof ParentRunner) {
			// setScheduler(RunnerScheduler scheduler):Sets a scheduler that
			// determines the order and parallelization of children
			// RunnerScheduler:Represents a strategy for scheduling when
			// individual test methods should be run (in serial or parallel)
			((ParentRunner) runner).setScheduler(new RunnerScheduler() {
				private final ExecutorService fService = Executors.newCachedThreadPool();

				// private final ExecutorService fService =
				// Executors.newFixedThreadPool(10);

				// Schedule a child statement to run
				public void schedule(Runnable childStatement) {
					this.fService.submit(childStatement);
				}

				// Override to implement any behavior that must occur after all
				// children have been scheduled
				public void finished() {
					try {
						this.fService.shutdown();
						this.fService.awaitTermination(9223372036854775807L, TimeUnit.NANOSECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace(System.err);
					}
				}
			});
		}
		return runner;
	}

	public ConcurrentSuite(final Class<?> klass) throws InitializationError {
		// 调用父类ClasspathSuite构造函数
		// AllDefaultPossibilitiesBuilder根据不同的测试类定义（@RunWith的信息）返回Runner,使用职责链模式
		super(klass, new AllDefaultPossibilitiesBuilder(true) {
			@Override
			public Runner runnerForClass(Class<?> testClass) throws Throwable {
				List<RunnerBuilder> builders = Arrays.asList(new RunnerBuilder[] { ignoredBuilder(), annotatedBuilder(),
						suiteMethodBuilder(), junit3Builder(), junit4Builder() });
				for (RunnerBuilder each : builders) {
					// 根据不同的测试类定义（@RunWith的信息）返回Runner
					Runner runner = each.safeRunnerForClass(testClass);
					if (runner != null)
						// 方法级别，多线程执行
						return MulThread(runner);
				}
				return null;
			}
		});

		// 类级别，多线程执行
		setScheduler(new RunnerScheduler() {
			private final ExecutorService fService = Executors.newCachedThreadPool();

			@Override
			public void schedule(Runnable paramRunnable) {
				// TODO Auto-generated method stub
				fService.submit(paramRunnable);
			}

			@Override
			public void finished() {
				// TODO Auto-generated method stub
				try {
					fService.shutdown();
					fService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}
			}

		});
	}

}
