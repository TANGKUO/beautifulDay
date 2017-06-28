package com.tk.cn.junit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//import org.junit.experimental.ParallelComputer;
import org.junit.runner.Computer;
import org.junit.runner.Runner;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.junit.runners.model.RunnerScheduler;

/**
 * 
 * @ClassName: ParallelComputerTest
 * @Description: (JUnit4提供了ParallerComputer类来使用多线程执行测试用例)
 * @author tangkuo
 * @date 2017年6月28日 下午9:53:55
 *
 */
public class ParallelComputer extends Computer {

	private final boolean classes;
	private final boolean methods;

	public ParallelComputer(boolean classes, boolean methods) {
		this.classes = classes;
		this.methods = methods;
	}

	public static Computer classes() {
		return new ParallelComputer(true, false);
	}

	public static Computer methods() {
		return new ParallelComputer(false, true);
	}

	private static Runner parallelize(Runner runner) {
		if (runner instanceof ParentRunner) {
			((ParentRunner<?>) runner).setScheduler(new RunnerScheduler() {
				private final ExecutorService fService = Executors.newCachedThreadPool();

				public void schedule(Runnable childStatement) {
					fService.submit(childStatement);
				}

				public void finished() {
					try {
						fService.shutdown();
						fService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace(System.err);
					}
				}
			});
		}
		return runner;
	}
	// 类的维度

	@Override
	public Runner getSuite(RunnerBuilder builder, java.lang.Class<?>[] classes) throws InitializationError {
		Runner suite = super.getSuite(builder, classes);
		return this.classes ? parallelize(suite) : suite;
	}
	// 方法的维度

	@Override
	protected Runner getRunner(RunnerBuilder builder, Class<?> testClass) throws Throwable {
		Runner runner = super.getRunner(builder, testClass);
		return methods ? parallelize(runner) : runner;
	}

	/*
	 * ParallelComputer类中parallelize(Runner runner)方法重写了
	 * ParentRunner类的方法runner.setScheduler(RunnerSchedulerscheduler)
	 * ，重新定义了调度顺序，定义了一个线程池 private final ExecutorService fService =
	 * Executors.newCachedThreadPool()来多线程执行，运行结束后finished()，关闭线程池fService.
	 * shutdown()，并返回该runner。 其中ParallelComputer类重写了父类
	 * Computer的getSuite()和getRunner:
	 * 
	 * @Override public Runner getSuite(RunnerBuilder builder,
	 * java.lang.Class<?>[] classes) throws InitializationError { Runner suite =
	 * super.getSuite(builder, classes); return this.classes ?
	 * parallelize(suite) : suite; }
	 * 
	 * @Override protected Runner getRunner(RunnerBuilder builder, Class<?>
	 * testClass) throws Throwable { Runner runner = super.getRunner(builder,
	 * testClass); return methods ? parallelize(runner) : runner; }
	 * 
	 * 
	 * 
	 * getSuite()和getRunner()
	 * 根据ParallelComputer类的全局final变量classes和methods的值去决定是否多线程执行；
	 * classes为true时，并发以类为维度，如下： return this.classes ? parallelize(suite) :
	 * suite; methods为true时，并发以方法为维度，如下： return methods ? parallelize(runner) :
	 * runner;
	 * 
	 * ParallelComputer类提供了带参的构造函数：public ParallelComputer(boolean classes,
	 * boolean methods) 可以在类初始化时，直接定义多线程执行（不同维度）的对象。
	 * JUnitCore类中的方法runClasses():public static Result runClasses(Computer
	 * computer,Class<?>... classes)，
	 * 可以在main()函数里直接运行测试用例，参数Computer是ParallelComputer的父类， 可以直接new
	 * ParallelComputer（boolean classes, boolean methods）对象作为第一个形参。
	 * 
	 * 
	 * 
	 */

}
