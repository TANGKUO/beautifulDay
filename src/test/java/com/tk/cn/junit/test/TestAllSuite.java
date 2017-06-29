package com.tk.cn.junit.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
//import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tk.cn.io.TestPW;
import com.tk.cn.utils.excel.test.ExcelFileGeneratorTest;

/**
 * 
 * @ClassName: TkTestSuite
 * @Description: (suite文件,设计测试用例时需考虑线程安全。
 *               建议（本组内用例）,非final的全局变量，全改写到测试方法内定义，变成局部变量。)
 * @author tangkuo
 * @date 2017年6月28日 下午10:56:33
 *
 */

@RunWith(ConcurrentSuite.class)
@SuiteClasses({ExcelFileGeneratorTest.class,TestPW.class})
public class TestAllSuite {
	static Logger logger =LoggerFactory.getLogger(TestAllSuite.class);
	
	public static void main(String[] args) {
		logger.info("====TestAllSuite main is start====");
	}
	@BeforeClass
	public static void initClass() {
		logger.info("====TestAllSuite initClass is start====");
	}
	
	
	@AfterClass
	public static void clearClass() {
		logger.info("====TestAllSuite clearClass is start====");
	}

}
