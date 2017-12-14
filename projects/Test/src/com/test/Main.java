package com.test;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {  
		Scanner scanner = new Scanner(System.in);  
		while(scanner.hasNext()){
			String strA = scanner.next();  
			String strB = scanner.next();  
			
	        int height = strA.charAt(strA.length()-1)-'1'+1;
	        for(int i=1;i<strA.length()/2;i++){  
	            int c = Integer.parseInt(strA.charAt(i*2-1)+"");  
	            if(height<c){  
	                height=c;  
	            }  
	        }  
	        int max = 1;
	        for(int i = 1;i<=height;i++){  
	            max *= 2;  
	        }  
	        char[] arr = new char[max];  
	        for(int i = 1;i<=strA.length()/2;i++){  
	            int c = strA.charAt(i*2-1)-'1'+1;  
	            char cTem = strA.charAt(i*2-2);  
	            int number =1;  
	            for(int j=1;j<c;j++){  
	                number*=2;  
	            }  
	            while(arr[number]!=0){  
	                number++;  
	            }  
	            arr[number] = cTem;  
	        }  
	        
	        StringBuilder sb = new StringBuilder();  
	        for (char c : strB.toCharArray()){  
	            int i = 0;  
	            for(;i<arr.length;i++){  
	                if(arr[i]==c){ 
	                	break;
	                }  
	            }  
	            sb.append(len(arr,i) + " ");  
	        }  
	        System.out.println(sb.substring(0,sb.length()-1));  
		}
    }  
	
    public static int len(char[] arr, int i) {  
        if(i>=arr.length || arr[i]==0){  
            return 0;  
        } else{  
            int x = len(arr,2*i);  
            int y = len(arr,2*i+1); 
            if(x>=y){
            	return x+1;
            } else {
            	return y+1;
            }
        }  
    } 
}




/*public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String str = scanner.next();
			String[] strArr = str.split(",");
			int[] arr = new int[strArr.length];
			for(int i=0;i<strArr.length;i++){
				arr[i] = Integer.parseInt(strArr[i]);
			}
			Arrays.sort(arr);
			int[] flag = new int[arr.length];
			for(int i=1;i<arr.length-1;i++){
				if(arr[i-1]+1==arr[i] && arr[i+1]-1==arr[i]){
					flag[i] = 1;
				}
			}
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<arr.length;i++){
				if(flag[i]!=1){
					sb.append(arr[i]);
					if(i!=arr.length-1){
						sb.append(" ");
					}
				}
			}
			System.out.println(sb);
		}
	}

/*	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			long m = scanner.nextLong();
			long n = scanner.nextLong();
			List<Integer> list =  new ArrayList<Integer>();
			for(int i=0;i<m;i++){
				int tem = Integer.MAX_VALUE;
				for(int j=1;j<=n;j++){
					if(!list.contains(j)){
						if(compare(j, tem)<0){
							tem = j;
						}
					}
				}
				list.add(tem);
			}
			System.out.println(list.get((int)(m-1)));
		}
	}
	public static int compare(int a, int b){
		if(a==b){
			return 0;
		}
		String stra = String.valueOf(a);
		String strb = String.valueOf(b);
		int len = Math.min(stra.length(), strb.length());
		for(int i=0;i<len;i++){
			if(stra.charAt(i)<strb.charAt(i)){
				return -1;
			}
			if(stra.charAt(i)>strb.charAt(i)){
				return 1;
			}
		}
		if(stra.length()<strb.length()){
			return -1;
		} else {
			return 1;
		}
	}

/*
 public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			int k = scanner.nextInt();
			if(k==0){
				System.out.println(1 + " " + 0);
				continue;
			}
			String init = "A";
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<k;i++){
				sb.delete(0, sb.length());
				for(int j=0;j<init.length();j++){
					if(init.charAt(j)=='A'){
						sb.append("B");
					}else if(init.charAt(j)=='B'){
						sb.append("BA");
					}
				}
				init = sb.toString();
			}
			int a = 0;
			int b = 0;
			for(int i=0;i<sb.length();i++){
				if(sb.charAt(i)=='A'){
					a ++;
				} else {
					b ++;
				}
			}
			System.out.println(a + " " + b);
		}
	}
 */

/*
public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLong()){
			long x = scanner.nextLong();
			long total = 0;
			long i = 1;
			while(total<x){
				total += i;
				i ++;
			}
			long res = 0;
			if(total==x){
				res = i-1;
			}else {
				long gap = total-x;
				if(gap%2==0){
					res = i-1;
				} else {
					if(i%2!=0){
						res = i;
					} else {
						System.out.println("---");
						res = i+1;
					}
				}
			}
			System.out.println(res);
		}
	}
 */


/*public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
while(scanner.hasNext()){
	String strA = scanner.next();
	String strB = scanner.next();
	int res = 0;
	if(strA.length()!=strB.length()){
		res = Math.abs(strA.length()-strB.length());
		if(strA.length()<strB.length()){
			for(int i=0;i<strA.length();i++){
				if(!strB.contains(strA.substring(i, i+1))){
					res ++;
				}
			}
		} else {
			for(int i=0;i<strB.length();i++){
				if(!strA.contains(strB.substring(i, i+1))){
					res ++;
				}
			}
		}
		System.out.println(res);
	} else {
		for(int i=0;i<strA.length();i++){
			if(strA.charAt(i)!=strB.charAt(i)){
				res ++;
			}
		}
		System.out.println(res);
	}
}
}*/


/*public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextInt()){
			int x = scanner.nextInt();
			int n = scanner.nextInt();
			int[] arr = new int[n];
			for(int i=0;i<n;i++){
				arr[i] = scanner.nextInt();
			}
			int left = 0;
			int right = n-1;
			int cur = 0;
			boolean flag = false;
			while(left<=right){
				cur = (left + right)/2;
				if(x==arr[cur]){
					while(cur-1>=0 && arr[cur-1]==arr[cur]){
						cur --;
					}
					flag = true;
					System.out.println(cur);
					break;
				} else if(x<arr[cur]){
					right = cur-1;
				} else if(x>arr[cur]){
					left = cur+1;
				}
			}
			if(!flag){
				if(left==arr.length){
					System.out.println( arr.length);
				} else {
					if(x>arr[left]){
						System.out.println( (left+1));
					} else {
						System.out.println( (left));
					}
				}
			}
		}
	}*/

/*public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while(scanner.hasNext()){
    	String seqAll = scanner.next();
    	String seq1 = scanner.next();
    	String seq2 = scanner.next();
    	String seqRev = new StringBuilder(seqAll).reverse().toString();
    	boolean forward = false;
    	boolean backward = false;
    	if(seqAll.contains(seq1)){
    		int index = seqAll.indexOf(seq1);
    		String seqLeft = seqAll.substring(index+seq1.length());
    		if(seqLeft.contains(seq2)){
    			forward = true;
    		}
    	}
    	if(seqRev.contains(seq1)){
    		int index = seqRev.indexOf(seq1);
    		String seqLeft = seqRev.substring(index+seq1.length());
    		if(seqLeft.contains(seq2)){
    			backward = true;
    		}
    	}
    	if(forward==true && backward==true){
    		System.out.println("both");
    	} else if(forward==true){
    		System.out.println("forward");
    	} else if(backward==true){
    		System.out.println("backward");
    	} else {
    		System.out.println("invalid");
    	}
    }
}*/

	/*public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    while(scanner.hasNext()){
	    	int t = scanner.nextInt();
	    	int maxMem = scanner.nextInt();
	    	int[] mem = new int[maxMem];
	    	Set<Integer> set = new HashSet<Integer>();
	    	int handleOrder = 1;
	    	for(int i=0;i<t;i++){
	    		String ope = scanner.next();
	    		if("new".equals(ope)){
	    			int size = scanner.nextInt();
	    			int start = 0;
	    			boolean nullFlag = true;
	    			for(int m=0;m<maxMem-size;m++){
	    				int n = m;
	    				while(n<m+size && mem[n]==0) n++;
	    				if(n==m+size){
	    					start = m;
	    					nullFlag = false;
	    					break;
	    				}
	    			}
	    			if(nullFlag){
	    				System.out.println("NULL");
	    			} else {
	    				for(int k=start;k<start+size;k++){
	    					mem[k] = handleOrder;
	    				}
	    				set.add(handleOrder);
    					System.out.println(handleOrder);
    					handleOrder++;
	    			}
	    			
	    		} else if("del".equals(ope)){
	    			int handle = scanner.nextInt();
	    			if(!set.contains(handle)){
	    				System.out.println("ILLEGAL_OPERATION");
	    			} else {
	    				for(int k=0;k<maxMem;k++){
	    					if(mem[k]==handle){
	    						mem[k] = 0;
	    					}
	    				}
	    				set.remove(handle);
	    			}
	    		} else if("def".equals(ope)){
	    			
	    			List<Integer> list = new ArrayList<Integer>();
	    			for(int k=0;k<maxMem;k++){
	    				if(mem[k]!=0){
	    					list.add(mem[k]);
	    				}
	    			}
	    			for(int k=0;k<list.size();k++){
	    				mem[k] = list.get(k);
	    			}
	    			for(int k=list.size();k<maxMem;k++){
	    				mem[k] = 0;
	    			}
	    			
	    		}
	    	}
	    }
	}
}*/


/*
public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    while(scanner.hasNextInt()){
    	  int n = scanner.nextInt();
        int[][] data = new int[n][n];
        int r1 = 0;
        int r2 = n-1;
        int cTem = 0;
        int c = n-1;
        int k = 1;
        while(r2>=r1 && c>=cTem){
          for(int i=cTem;i<=c;i++){
          	data[r1][i] = k;
            	k++;
        	}
          r1++;
          for(int i=r1;i<=r2;i++){
          	data[i][c] = k;
             k++;
          }
          c--;
          for(int i=c;i>=cTem;i--){
          	data[r2][i] = k;
             k++;
          }
          r2--;
          for(int i=r2;i>=r1;i--){
          	data[i][cTem] = k;
             k++;
          }
          cTem++;
        }
        for(int i=0;i<n;i++){
          for(int j=0;j<n;j++){
          	System.out.print(data[i][j]+" ");
          }
        }
    }
}*/