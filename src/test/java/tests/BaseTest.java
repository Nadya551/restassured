package tests;

import api.api_manager.ApiManager;
import listeners.Interceptor;
import listeners.LogListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;


@Listeners({LogListener.class, Interceptor.class})
public class BaseTest {
    public ApiManager apiManager = new ApiManager();


    @BeforeClass
    public void setUp() {
    }

}
