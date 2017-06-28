package com.tk.cn.threads;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * @ClassName: TkTestSuite
 * @Description: (suite文件,设计测试用例时需考虑线程安全。
 *               建议（本组内用例）,非final的全局变量，全改写到测试方法内定义，变成局部变量。)
 * @author tangkuo
 * @date 2017年6月28日 下午10:56:33
 *
 */

@RunWith(Categories.class)
@SuiteClasses(IntegrationBeijingOneTests.class)
public class TkTestSuite {

}
