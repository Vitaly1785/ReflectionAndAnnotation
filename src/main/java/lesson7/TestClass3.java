package lesson7;

public class TestClass3 {
    @AfterSuite
    public void after(){
        System.out.println("After test");
    }

    @Test(priority = 3)
    public void testing3(){
        System.out.println("3");
    }

    @Test(priority = 1)
    public void testing1(){
        System.out.println("1");
    }

    @Test(priority = 2)
    public void testing2(){
        System.out.println("2");
    }

    @BeforeSuite
    public void before(){
        System.out.println("Before test");
    }
}
