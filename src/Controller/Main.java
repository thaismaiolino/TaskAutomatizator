package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.RecognizeDelegate;

import java.awt.Desktop; 
import java.awt.Desktop.Action; 
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random; 

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;


public class Main {

    public static void main( String[] args ) throws IOException, URISyntaxException {
    	SpeechToText service = new SpeechToText();
    	service.setUsernameAndPassword("a2eaf4c8-d832-4c7a-9e9c-5639cca8d897", "bdKwIHWMYFAo");
    	
    	final SoundRecorder recorder = new SoundRecorder();
    	Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });
 
        stopper.start();
 
        // start recording
        recorder.start();
    	
    	
    	
    	
    	int num = (int) ((int) 1 + (Math.random() * 3));
    	String aux = "trab.wav";;
    	if (num == 1){
    		aux = "openFb.wav";
    	}
    	if (num == 2){
    		aux = "app.wav";
    	}
    	else if (num == 3){
    		aux = "searchFb.wav";
    	}
    	
    	//  System.out.println(aux);
    	//  System.out.println(num);
    	 File audio = new File("src/resources/RecordAudio.wav");//+ aux);
    	
    	RecognizeOptions options = new RecognizeOptions();
    	//options.model(SpeechModel.PT_BROADBAND16K.getName());

    	SpeechResults transcript = service.recognize(audio, options);//HttpMediaType.AUDIO_WAV);
    	System.out.println(transcript);
    	
    	String speech = transcript.getResults().get(0).getAlternatives().get(0).getTranscript().trim();
    	Desktop desktop = Desktop.getDesktop();
    	System.out.println(speech);
  	if (speech.contains("start my applications")){
  		
			System.out.println("Opening Stickies");
			Desktop.getDesktop().open(new File("/Applications/Stickies.app"));
  	
  	
  	}
    	if (speech.contains("search")){ 
    		String search = speech.replace("search", "").trim();
    		System.out.println(search);
    		desktop.browse(new URI("https://www.google.com.br/#q="+ search));
    	}
    	if (speech.equalsIgnoreCase("open facebook")){
    		
    		desktop.browse(new URI("https://www.facebook.com"));
    	
    	}
    	}

    
    
    }


