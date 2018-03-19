#include <iostream>
#include <sstream>

#include "wrapper_FasttextWrapper.h"
#include "fasttext.h"
#include "args.h"

using namespace fasttext;

void printUsage() {
	std::cerr
		<< "usage: fasttext <command> <args>\n\n"
		<< "The commands supported by fasttext are:\n\n"
		<< "  supervised              train a supervised classifier\n"
		<< "  quantize                quantize a model to reduce the memory usage\n"
		<< "  test                    evaluate a supervised classifier\n"
		<< "  predict                 predict most likely labels\n"
		<< "  predict-prob            predict most likely labels with probabilities\n"
		<< "  skipgram                train a skipgram model\n"
		<< "  cbow                    train a cbow model\n"
		<< "  print-word-vectors      print word vectors given a trained model\n"
		<< "  print-sentence-vectors  print sentence vectors given a trained model\n"
		<< "  nn                      query for nearest neighbors\n"
		<< "  analogies               query for analogies\n"
		<< std::endl;
}



void predict(int argc, char** argv) {
	if (argc < 4 || argc > 5) {
		exit(EXIT_FAILURE);
	}
	int32_t k = 1;
	if (argc >= 5) {
		k = atoi(argv[4]);
	}

	bool print_prob = std::string(argv[1]) == "predict-prob";
	FastText fasttext;
	fasttext.loadModel(std::string(argv[2]));

	std::string infile(argv[3]);
	if (infile == "-") {
		fasttext.predict(std::cin, k, print_prob);
	}
	else {
		std::ifstream ifs(infile);
		if (!ifs.is_open()) {
			std::cerr << "Input file cannot be opened!" << std::endl;
			exit(EXIT_FAILURE);
		}
		fasttext.predict(ifs, k, print_prob);
		ifs.close();
	}

	//exit(0);//ÐèÒªÈ¥µô
}


void train(int argc, char** argv) {
	std::shared_ptr<Args> a = std::make_shared<Args>();
	a->parseArgs(argc, argv);
	FastText fasttext;
	fasttext.train(a);
}


JNIEXPORT void JNICALL Java_wrapper_FasttextWrapper_train(JNIEnv *env, jobject, jobjectArray arr)
{
	int paraNum = env->GetArrayLength(arr);
	char **params = new char*[paraNum];
	for (int i = 0; i<paraNum; i++) {
		jstring string = (jstring)(env->GetObjectArrayElement(arr, i));
		const char *rawString = env->GetStringUTFChars(string, 0);
		
		char* c = nullptr;
		c = const_cast<char*>(rawString);
		params[i] = c;
	}
	train(paraNum, params);
}

JNIEXPORT jlong JNICALL Java_wrapper_FasttextWrapper_loadModel(JNIEnv *env, jobject, jstring str)
{
	FastText *fasttext = new FastText();
	std::string path = env->GetStringUTFChars(str, 0);
	fasttext->loadModel(path);

	return (jlong)(fasttext);
}

JNIEXPORT void JNICALL Java_wrapper_FasttextWrapper_unloadModel(JNIEnv *, jobject, jlong ftAddr)
{
	FastText * fasttext = (FastText *)ftAddr;
	delete fasttext;
	fasttext = NULL;
}


JNIEXPORT jstring JNICALL Java_wrapper_FasttextWrapper_predict(JNIEnv *env, jobject, jlong ftAddr, jstring text, jint k, jboolean prob)
{
	FastText * fasttext = (FastText *)ftAddr;

	std::string str = env->GetStringUTFChars(text, 0);
	std::istringstream is(str);

	std::string predictRes = fasttext->predict(is, k, prob);
	jstring res = env->NewStringUTF(predictRes.c_str());

	return res;
}




int main(int argc, char** argv) {

	//char* arr[] = {"fasttext","predict","model.bin","test.txt"};
	//predict(4, arr);

	for (int i = 0;i < 4;i++)
	{
		FastText *fasttext = new FastText();
		fasttext->loadModel("model.bin");

		std::string str = "football";
		std::istringstream is(str);

		std::string res = fasttext->predict(is, 1, true);
		std::cout << res;
		delete fasttext;
		//fasttext = NULL;
	}

	while (1);
	return 0;

}
