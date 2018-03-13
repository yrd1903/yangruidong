package wrapper;

import java.io.File;

import javax.management.modelmbean.ModelMBean;

public class FasttextWrapper {
	
	
	public native void train(String[] params);
	
	public native long loadModel(String modelPath);
	
	public native String predict(long obj, String text);
	
	
	static
	{
		String relaPath = "./dlls/MyFasttext.dll";
		File file = new File(relaPath);
		String absPath = file.getAbsolutePath();
		System.load(absPath);
	}
	
	public static void main(String[] args) {
		
		FasttextWrapper wrapper = new FasttextWrapper();
		System.out.println("fasttext process started!");
//		String[] arr = new String[]
//				{"fasttext","skipgram","-input","data.txt","-output","model"};
//		String[] arr = new String[]
//				{"fasttext","supervised","-input","./data/labeled_data.txt","-output","./data/model"};
//		String[] arr = new String[]
//				{"fasttext","predict","./data/model.bin","./data/test.txt","1"};
//		wrapper.train(arr);

		
		
		String modelPath = "./data/model.bin";
		long add = wrapper.loadModel(modelPath);
		String text = "football";
		for(int i=0;i<40;i++)
		{
			wrapper.predict(add, text);
		}
		
		
		System.out.println("fasttext process ended!");

	}

}
