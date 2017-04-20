package uib.teamdank.common;

import static org.mockito.Mockito.mock;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;

public abstract class LibGdxDependentTest {
	private static Application application;
	
	@BeforeClass
	public static void initialize() {
		application = new HeadlessApplication(new ApplicationAdapter() {});
		Gdx.gl20 = mock(GL20.class);
		Gdx.gl = Gdx.gl20;
	}
	
	@AfterClass
	public static void cleanUp() {
		application.exit();
	}
}
