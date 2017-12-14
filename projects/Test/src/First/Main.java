package First;

import java.util.*;


public class Main {
	
	public static void main(String[] args){
   		Scanner scanner = new Scanner(System.in);
   		while(scanner.hasNext()){
   			String str = scanner.nextLine();
   			List<Character> list = new ArrayList<Character>();
   			Set<Character> setRes = new HashSet<Character>();
   			Set<Character> set = new HashSet<Character>();
   			set.add('a'); set.add('A');
   			set.add('o'); set.add('O');
   			set.add('e'); set.add('E');
   			set.add('i'); set.add('I');
   			set.add('u'); set.add('U');
   			for(int i=0;i<str.length();i++){
   				if(set.contains(str.charAt(i))){
   					list.add(str.charAt(i));
   					setRes.add(str.charAt(i));
   				}
   			}
   			System.out.println(setRes.size() + " " + list.size() + " " + (str.length()-list.size()));
   			StringBuilder sb = new StringBuilder();
   			for(int i=0;i<list.size();i++){
   				sb.append(list.get(i));
   			}
   			System.out.println(sb);
   		}
	}
}

/*	public static void main(String[] args){
   		Scanner scanner = new Scanner(System.in);
   		while(scanner.hasNext()){
   			int n = scanner.nextInt();
   			int m = scanner.nextInt();
   			int k = scanner.nextInt();
   			int[] weight = new int[n];
   			for(int i=0;i<n;i++){
   				weight[i] = scanner.nextInt();
   			}
   			int[] dest = new int[n];
   			for(int i=0;i<n;i++){
   				dest[i] = scanner.nextInt();
   			}
   			Stack<Integer> stack = new Stack<Integer>();
   			for(int i=n-1;i>=0;i--){
   				stack.push(weight[i]);
   			}
   			int res = 0;
   			int start = 0;
   			int end = 0;
   			while(!stack.isEmpty()){
   				int total = 0;
   				while(total<k){
   					if(stack.isEmpty()){
   						break;
   					}
   					int tem = stack.peek();
   					if(total+tem<k){
   						total += tem;
   						stack.pop();
   						end ++;
   					}else{
   						break;
   					}
   				}
   				Set<Integer> set = new HashSet<Integer>();
   				for(int i=start;i<end;i++){
   					set.add(dest[i]);
   				}
   				start = end;
   				res += set.size();
   			}
   			System.out.println(res);
   		}
	}

/*public class Main {
	
	public static void main(String[] args){
   		Scanner scanner = new Scanner(System.in);
   		while(scanner.hasNext()){
   			long n = scanner.nextLong();
   			if(n<0){
   				continue;
   			}
   			if(n<=9){
   				System.out.println(11);
   				continue;
   			}
   			n++;
   			while(!check(n)){
   				n ++;
   			}
   			System.out.println(n);
   		}
	}
	public static boolean check(long n){
		String str = String.valueOf(n);
		String str2 = new StringBuilder().append(n).reverse().toString();
		if(str.equals(str2)){
			return true;
		} else {
			return false;
		}
	}
}


/*	public static void main(String[] args){
   		Scanner scanner = new Scanner(System.in);
   		while(scanner.hasNext()){
   			String str1 = scanner.next();
   			String str2 = scanner.next();
   			List<Character> list = new ArrayList<Character>();
   			for(int i=0;i<str1.length();i++){
   				if(!list.contains(str1.charAt(i))){
   					list.add(str1.charAt(i));
   				}
   			}
   			List<Character> list2 = new ArrayList<Character>();
   			for(int i=0;i<str2.length();i++){
   				if(!list2.contains(str2.charAt(i))){
   					list2.add(str2.charAt(i));
   				}
   			}
   			int tem = list.size();
   			for(int i=0;i<list2.size();i++){
   				if(list.contains(list2.get(i))){
   					list.remove(list2.get(i));
   				}
   			}
   			if(tem==list2.size() && list.size()==0){
   				System.out.println(true);
   			} else {
   				System.out.println(false);
   			}
   		}
	}

/*	public static void main(String[] args){
   		Scanner scanner = new Scanner(System.in);
   		while(scanner.hasNext()){
   			int n = scanner.nextInt();
   	   		int r = scanner.nextInt();
   	   		int[] arr = new int[n];
   	   		for(int i=0;i<n;i++){
   	   			arr[i] = scanner.nextInt();
   	   		}
   	   		int tem = 0;
   	   		for(int i=r;i<=n;i++){
   	   			int[] arrTem = new int[i];
   	   			for(int j=0;j<n;j++){
   	   				for(int m=j;m<j+i;m++){
   	   					tem = m%n;
   	   					arrTem[m-j] = arr[tem];
   	   				}
   	   				Arrays.sort(arrTem);
   	   	   			arr[j] = arrTem[i/2];
   	   			}
   	   		}
   	   		System.out.println(arr[0]);
   		}
	}

/*
public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLong()){
			long n = scanner.nextLong();
			long m = scanner.nextLong();
			String str = String.valueOf(n);
			int[] arr = new int[str.length()];
			for(int i=0;i<arr.length;i++){
				arr[i] = Integer.parseInt(str.substring(i, i+1));
			}
			Arrays.sort(arr);
			int i=0;
			while(arr[i]==0){
				i++;
			}
			int tem = arr[0];
			arr[0] = arr[i];
			arr[i] = tem;
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<arr.length;j++){
				sb.append(arr[j]);
			}
			long value = Long.parseLong(sb.toString());
			if(value==m){
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
 */

/*
 * public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int resVal1 = 0;
		int resVal2 = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		while(scanner.hasNext()){
			int flag;
			try {
				flag = scanner.nextInt();
			} catch (Exception e) {
				break;
			}
			if(flag==1){
				int start = scanner.nextInt();
				int end = scanner.nextInt();
				int dayVal = scanner.nextInt();
				for(int j=start;j<=end;j++){
					if(!map.containsKey(j)){
						map.put(j, dayVal);
					} else {
						if(dayVal>map.get(j)){
							map.put(j, dayVal);
						}
					}
				}
			} else {
				int day = scanner.nextInt();
				int taskVal = scanner.nextInt();
				resVal2 += taskVal;
			}
			resVal1 = 0;
			for(int i:map.values()){
				resVal1 += i;
			}
		}
		System.out.println(resVal1+resVal2);
	}
	*/

/*
public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int resVal1 = 0;
		int resVal2 = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		while(scanner.hasNextInt()){
			int flag = scanner.nextInt();
			if(flag==1){
				int start = scanner.nextInt();
				int end = scanner.nextInt();
				int dayVal = scanner.nextInt();
				for(int j=start;j<=end;j++){
					if(!map.containsKey(j)){
						map.put(j, dayVal);
					} else {
						if(dayVal>map.get(j)){
							map.put(j, dayVal);
						}
					}
				}
			} else {
				int day = scanner.nextInt();
				int taskVal = scanner.nextInt();
				resVal2 += taskVal;
			}
			resVal1 = 0;
			for(int i:map.values()){
				resVal1 += i;
			}
			System.out.println(resVal1+resVal2);
		}
	}
 */


