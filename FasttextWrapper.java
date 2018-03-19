package wrapper;

import java.io.File;

import javax.management.modelmbean.ModelMBean;

public class FasttextWrapper {
	
	
	private native void train(String[] params);
	
	private native long loadModel(String modelPath);
	
	private native void unloadModel(long ftAddr);
	
	private native String predict(long ftAddr, String text, int k, boolean prob);
	
	
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
		//long ftAddr = wrapper.loadModel(modelPath);
		String text = "football\r\nsoccer";
		for(int i=0;i<20;i++)
		{
			long ftAddr = wrapper.loadModel(modelPath);
			System.out.println(ftAddr);
			//String str = wrapper.predict(ftAddr, text, 1, true);
			//System.out.println(str);
			//wrapper.unloadModel(ftAddr);
//			System.out.println("unload finished");
			//System.out.println(str);
			//wrapper.unloadModel(ftAddr);
			//String str1 = wrapper.predict(ftAddr, text, 1, true);
			//System.out.println(str1);
		}
		
		
		System.out.println("fasttext process ended!");

	}

}
