package uib.teamdank.common;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uib.teamdank.common.util.Highscore;

public class HighscoreTest {

	Gson gson;
	FileHandle mockhandle;
	
	@Before
	public void setUp() throws Exception {
		gson = new GsonBuilder().create();
		mockhandle = mock(FileHandle.class);
	}

	@Test
	public void testWritesExpectedJsonToFile() {
		String expectedJson = "{\"name\":[\"test\"],\"score\":[{\"score\":0}]}";
		
		ArgumentCaptor<String> jsonCapture = ArgumentCaptor.forClass(String.class);
		
		Highscore.writeToJson(mockhandle, new String[]{"test"}, new Score[]{new Score()});
		
		verify(mockhandle).writeString(jsonCapture.capture(), anyBoolean());
		System.out.println(jsonCapture.getValue());
		
		assertEquals(expectedJson, jsonCapture.getValue());
	}
	
	@Test
	public void testCreatesCorrectHighScoresFromFile(){
		String json = "{\"name\":[\"test1\",\"test2\"],\"score\":[{\"score\":0},{\"score\":0}]}";
		
		// TODO
		when(mockhandle.read()).thenReturn(null);
	}

}
