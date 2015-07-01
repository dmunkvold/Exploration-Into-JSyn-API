import com.jsyn.*;
import com.jsyn.unitgen.*;


public class JSynPrac {
	public static double x=400;
	static LineOut myOut;
	static WhiteNoise myNoise;
	static FilterStateVariable myFilter;
	static PulseOscillator myOsc;
	
	public static void main(String[]args){
		Synthesizer synth = JSyn.createSynthesizer();
		synth.start();
		synth.add( myOut = new LineOut() );
		synth.add( myOsc = new PulseOscillator() );
		synth.add( myFilter = new FilterStateVariable() );
		
		myOsc.output.connect( myFilter.input );
		myFilter.output.connect( 0, myOut.input, 1); //right side
		myFilter.output.connect( 0, myOut.input, 0); //left side

		myOsc.frequency.set(x);
		myOsc.amplitude.set(-.5);
		myOut.start();
		try {
			synth.sleepUntil(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myOut.stop();
		
	}
}
