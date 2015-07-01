import java.io.File;
import java.io.IOException;

import com.jsyn.unitgen.VariableRateStereoReader;
import com.jsyn.util.SampleLoader;
import com.jsyn.data.FloatSample;


public class FileLoader {
	
	public static File file;
	public FloatSample sample;
	public VariableRateStereoReader samplePlayer;
	//static FileInputStream fis;
	public FileLoader(String fileDest){
		file = new File(fileDest);
	}
	
	public FloatSample loadSample(){
		try {
			sample = SampleLoader.loadFloatSample(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sample;
	}
	
	/*public static void main(String[]args){
		file = new File("/Users/David/Downloads/looperman-l-1088502-0071468-tendeir0-fruit-bat.wav");
		
		
		byte[] soundFileByteArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.read(soundFileByteArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0;i<soundFileByteArray.length; i++){
			System.out.println(soundFileByteArray[i]);
		}
		System.out.println(soundFileByteArray.length);
	}*/


}
