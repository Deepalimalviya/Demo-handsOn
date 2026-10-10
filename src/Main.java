import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        // Output is: [1, 3, 6, 9, 12]
        int[] arr = {1,2, 3, 4, 5};
        int k = 3;
        int[] newArr = cacl(arr,k);
       for(int i =0; i<newArr.length;i++){
         //  System.out.println(newArr[i]);
       }

       int[] arr2 ={7,8,9};
       int[] result = merge(arr, arr2);
        for(int i =0; i<result.length;i++){
         //    System.out.println(result[i]);
        }

     //   System.out.println(transform("hello java world"));

        //using java 8 transform -

        String str= "hello    jAVA world";
        String hashtag =  "#"+Arrays.stream(str.split("\\s+"))
                .map(word->Character.toUpperCase(word.charAt(0))
                        +word.substring(1).toLowerCase()).collect(Collectors.joining());
        System.out.println(hashtag);


        // only duplicate with order -
        duplicate(new int[]{1,3,2,3,1});

        //Input: ['h','e','l','l','o']
        //Output:['o','l','l','e','h']
        char[] result1 = reverseInPlace(new char[]{'h','e','l','l','o'});
      //  System.out.println(Arrays.toString(result1));


        // valid interger from given list of stream
        List<String> list = Arrays.asList("123", "abc", "45", "12a", "-10", "7");
       List<Integer> number= list.stream().filter(n->{
          try{
              Integer.parseInt(n);
              return  true;
          }catch (Exception e){
              return false;
          }
        }).map(n->Integer.parseInt(n)).collect(Collectors.toList());

       System.out.println(number.toString());


       // group Anagram
        List<String> list1 = Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat");

        System.out.println(groupAnagram(list1));

        // using java 8
       Map<String , List<String>> map1=  list1.stream().collect(Collectors.groupingBy(word->{
            char[] c = word.toCharArray();
            Arrays.sort(c);
            return new String(c);
        }));
       //System.out.println(map1.values());
        list1.stream().filter(s->s.startsWith("a")).collect(Collectors.toList());

       // Second largest String ny length

       List<String> strList = Arrays.asList("java","Microservices", "Microservice", "python", "PythonC");
       System.out.println(secondLargest(strList));


       // sum two int indices equal target
        int[] arr1 = {2, 7, 11, 15, 3, 6};
        int target = 9;
       sumTarget(arr1, target);


       //find dublicate substring appear atleast twice

        String dup = "abcdabc";

        Map<String, Long> map3 = IntStream.range(0, dup.length())
                .boxed()
                .flatMap(i-> IntStream.range(i+1, dup.length())
                        .mapToObj(j -> dup.substring(i,j)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        String longestDup = map3.entrySet().stream().filter(k1->k1.getValue()>=2).map(v->v.getKey()).
                max(Comparator.comparingInt(String::length)).get();
    }

    public static int[] cacl(int[] arr, int k ){
        int totalcount =0;
        int[] result = new int[arr.length];
        for(int i=0; i< arr.length;i++){
           totalcount+=arr[i];
           if(i>=k){
               totalcount-=arr[i-k];
           }
           result[i]=totalcount;
        }
        return result;
    }

    public static int[] merge(int[] a, int[]b){
        int[] result = new int[a.length+b.length];
        int i=0;
        int j=0;
        int k=0;
        while(j<a.length && k< b.length){
          if(a[j]<b[k]){
             result[i++] = a[j++];

          }else if(a[j]>b[k]){
              result[i++]=b[k++];
          }
        }
        while(j<a.length){
            result[i++] = a[j++];
        }
        while(k<b.length){
            result[i++]=b[k++];
        }
        return result;
    }

    //Input: "hello world java" Output: "#HelloWorldJava"

    public static  StringBuilder transform(String input){
        StringBuilder result = new StringBuilder("#");
        String[] str = input.split(" ");
       for(int i=0; i<str.length; i++){
           String s = str[i];
           result.append(Character.toUpperCase(s.charAt(0)));
           result.append(str[i].substring(1));
       }
        return result;
    }

    //1,3,2,3,1) inpout and out should be 3,1

    public static void duplicate(int[] input){
      Set<Integer> set = new HashSet<>();
      Arrays.stream(input).filter(n-> !set.add(n)).forEach(System.out::println);

    }

    //Input: ['h','e','l','l','o']
    //Output:['o','l','l','e','h']

    public static char[] reverseInPlace(char[] arr){

        for(int i=0;i<arr.length/2;i++){
           char temp = arr[i];
           arr[i] = arr[arr.length-i-1];
           arr[arr.length-i-1] = temp;
        }
        return arr;
    }

    // group anagram
    public static List<List<String>> groupAnagram(List<String> list){
        Map<String, List<String>> map = new HashMap<>();

          for(String s: list){
             char[] cha= s.toCharArray();
             Arrays.sort(cha);
             String key = new String(cha);
             map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
          }

          return new ArrayList<>(map.values());
    }

    public static String secondLargest(List<String> list){
        int largest = 0;
        int secondLargest =0;
        String secondLar = null;
        String firstLar = null;

        for(String s: list){
            int len = s.length();
            if(len>largest){
                secondLargest = largest;
                secondLar = firstLar;
                firstLar =s;
                largest = len;
            }else if(len<largest && len > secondLargest){
                secondLargest = len;
                secondLar = s;
            }
        }
        return  secondLar;
    }

    public static void sumTarget(int [] arr , int target){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<arr.length; i++){
            int complement = target-arr[i];
            if(map.containsKey(complement)){
                System.out.println("indices "+ map.get(complement) +" "+ i);
            }
           map.put(arr[i],i);
        }
    }
}