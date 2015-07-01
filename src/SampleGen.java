import com.jsyn.data.FloatSample;


public class SampleGen {
	
	public FloatSample mySample;
	float[] data;
	
	public SampleGen(float[] songdata){
		data = songdata;
	}
	
	public FloatSample SamGen(){
		mySample = new FloatSample(data, 2);
		
		return mySample;
	}
}
