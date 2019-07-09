package cucumber.hooks;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.runners.TestState;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

public class GlobalHooks {

    private final TestState localState;

    public GlobalHooks(TestState localState) {
        this.localState = localState;
    }

    @Before
    public void beforeScenario() {
        Runtime.getRuntime().addShutdownHook( new Thread(){
            public void run () {
                System.out.println("This will run after the Scenario");
                localState.teardownState();
            }});
        localState.setupState();
    }

    @After //I cannot make that this after works
    public void afterScenario() {
        localState.teardownState();
    }
}
