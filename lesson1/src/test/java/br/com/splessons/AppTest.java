package br.com.splessons;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	ApplicationContext context;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    @Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		context = new ClassPathXmlApplicationContext("beans.xml");
	}

    public void testApp()
    {
    	HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        assertTrue( "Hello, Lesson 1!".equals(obj.sayHello()) );
    }
}
