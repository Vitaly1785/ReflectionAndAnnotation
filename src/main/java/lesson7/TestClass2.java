package lesson7;

public class TestClass2 {
    @BeforeSuite
    public void before(){
        System.out.println("Before test");
    }
    @Test(priority = 1)
    public void testing1(){
        System.out.println("1");
    }

    @Test(priority = 3)
    public void testing3(){
        System.out.println("3");
    }
    @BeforeSuite
    public void before1(){
        System.out.println("Before test1");
    }
}
