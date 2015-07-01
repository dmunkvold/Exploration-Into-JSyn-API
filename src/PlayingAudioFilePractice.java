import java.io.File;
import java.io.IOException;

import com.jsyn.*;
import com.jsyn.data.FloatSample;
import com.jsyn.devices.AudioDeviceManager;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.VariableRateStereoReader;
import com.jsyn.util.SampleLoader;


public class PlayingAudioFilePractice{
	static int numInputChannels = 2;
	static int numOutputChannels = 2;
	static FloatSample kickSample;
	static LineOut myOut;
	static VariableRateStereoReader samplePlayer;
	
	public static void main(String[]args){
		Synthesizer synth = JSyn.createSynthesizer();		
		File sample = new File("/Users/David/Downloads/looperman-l-1088502-0076166-tendeir0-percs-with-kick.wav");
		synth.add(myOut = new LineOut());
		synth.add(samplePlayer = new VariableRateStereoReader());
		synth.start(44100, AudioDeviceManager.USE_DEFAULT_DEVICE, numInputChannels, AudioDeviceManager.USE_DEFAULT_DEVICE,
				numOutputChannels);
		myOut.start();
		try {
			kickSample = SampleLoader.loadFloatSample(sample);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		samplePlayer.rate.set(kickSample.getFrameRate());
		samplePlayer.output.connect(0, myOut.input, 0);
		samplePlayer.output.connect(0, myOut.input, 1);
		samplePlayer.dataQueue.queueLoop(kickSample, 0, kickSample.getNumFrames());
		do {
			try {
				synth.sleepFor(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (samplePlayer.dataQueue.hasMore() );
		synth.stop();
	}
		
}
