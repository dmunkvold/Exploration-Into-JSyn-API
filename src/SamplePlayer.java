import com.jsyn.*;
import com.jsyn.data.FloatSample;
import com.jsyn.devices.AudioDeviceManager;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.VariableRateStereoReader;

public class SamplePlayer {
	
	static int numInputChannels = 2;
	static int numOutputChannels = 2;
	FloatSample sound;
	static LineOut myOut;
	static VariableRateStereoReader samplePlayer;
	static Synthesizer synth;
	
	public SamplePlayer(FloatSample sample){
		sound = sample;
	}
	
	public void playSound(){
		synth = JSyn.createSynthesizer();
		synth.add(myOut = new LineOut());
		synth.add(samplePlayer = new VariableRateStereoReader());
		synth.start(44100, AudioDeviceManager.USE_DEFAULT_DEVICE, numInputChannels, AudioDeviceManager.USE_DEFAULT_DEVICE,
				numOutputChannels);
		myOut.start();
		samplePlayer.rate.set(sound.getFrameRate());
		samplePlayer.output.connect(0, myOut.input, 0);
		samplePlayer.output.connect(0, myOut.input, 1);
		samplePlayer.dataQueue.queue(sound, 0, sound.getNumFrames());
		do {
			try {
				synth.sleepFor(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (samplePlayer.dataQueue.hasMore() );
		synth.stop();
		myOut.stop();
	}
	
	
	

}
