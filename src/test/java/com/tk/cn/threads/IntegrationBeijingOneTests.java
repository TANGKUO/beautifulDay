package com.tk.cn.threads;

import org.junit.runner.RunWith;

/**
 * 
 * @ClassName: IntegrationBeijingOneTests
 * @Description: (建一个聚合的IntegrationBeijingOneTests.java文件)
 * @author tangkuo
 * @date 2017年6月28日 下午10:30:57
 *
 */
@RunWith(ConcurrentSuite.class)
@ClassnameFilters({ "com.tk.*Test", "!.*RemindTest", "com.tk.cn.*tk" })
@Concurrent
public interface IntegrationBeijingOneTests {

}
