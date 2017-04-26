package uib.teamdank.common;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

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
		String expectedJson = "{\"score\":[{\"score\":0,\"name\":\"Anonymous\"}]}";
		
		ArgumentCaptor<String> jsonCapture = ArgumentCaptor.forClass(String.class);
		
		Highscore.writeToJson(mockhandle, new Score[]{new Score()});
		
		verify(mockhandle).writeString(jsonCapture.capture(), anyBoolean());
		System.out.println(jsonCapture.getValue());
		
		assertEquals(expectedJson, jsonCapture.getValue());
	}
	
	@Test
	public void testCreatesCorrectHighScoresFromFile(){
		String json = "{\"score\":[{\"score\":2,\"name\":\"Anonymous\"},{\"score\":1,\"name\":\"test\"}]}";
		
		when(mockhandle.reader()).thenReturn(new StringReader(json));
		
		Score[] scores =  Highscore.createFromJson(mockhandle).getScores();
		
		assertEquals(2, scores.length);
		assertEquals("Anonymous", scores[0].getName());
		assertEquals("test", scores[1].getName());
		assertEquals(2, scores[0].getScore());
		assertEquals(1, scores[1].getScore());
	}

}
