import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8 {

    public static void main(String[] args){

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 1,2);

        //Find maximum and minimum
        int max= list.stream().max((a,b)->a-b).get();
        System.out.println("max "+max);

        int min= list.stream().min(Integer::compareTo).get();
        System.out.println("min "+min);

        //Partition numbers into even and odd
        Map<Boolean, List<Integer>> map = list.stream().collect(Collectors.partitioningBy(n->n%2==0));
        System.out.println(map.toString());

        //find even number
        list.stream().filter(n->n%2==0).forEach(System.out::println);

        //find duplicate
        Set<Integer> set = new HashSet<>();
        list.stream().filter(n->!set.add(n)).forEach(System.out::println);

        //find second highest number
        list.stream().sorted(Comparator.reverseOrder()).skip(1).limit(1).forEach(System.out::println);

        //Find frequency of each element
        list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().forEach((k->System.out.println(k.getKey()+" :" +k.getValue())));

        //Find first non-repeated character
        String input= "swiss";
        Character c = input.chars().mapToObj(x->(char)x).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(k->k.getValue()==1).map(m->m.getKey()).findFirst().get();

        System.out.println("first non-repeated character "+c);

        //Count character occurrences
        input.chars().mapToObj(x->(char)x).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().forEach((k->System.out.println(k.getKey()+" :" +k.getValue())));

        //Find common elements in two lists

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(2, 3, 4);
        list2.stream().filter(n->list1.contains(n)).forEach(System.out::println);

       // Convert list of strings to uppercase
        List<String> lists = Arrays.asList("java", "spring");
        lists.stream().map(String::toUpperCase).forEach(System.out::println);

        //Join strings with a delimiter
       String s =  lists.stream().collect(Collectors.joining("-"));
        System.out.println("joining Sring is ="+s);

        //Flatten a list of lists
        List<List<Integer>> list3 = Arrays.asList(Arrays.asList(1, 2),Arrays.asList(3, 4));
        list3.stream().flatMap(List::stream).forEach(System.out::println);

       // Find the top 3 largest distinct numbers
        List<Integer> list4 = Arrays.asList(5, 10, 10, 8, 3);
        list4.stream().sorted(Comparator.reverseOrder()).distinct().limit(3).forEach(System.out::println);

        //Check whether a number is prime
        int number = 0;
        boolean prime = number>1 && IntStream.range(2, number)
                .noneMatch(n->number%n==0);

        System.out.println(prime);

        // Reverse a string
        String str = "madam Ji";
        String reverse = IntStream.range(0, str.length()).mapToObj(c1->str.charAt(str.length()-c1-1))
                .map(String::valueOf).collect(Collectors.joining());

        System.out.println(reverse);

        //Check whether a string is a palindrome
        boolean isPalindrome = IntStream.range(0, str.length()/2)
                .allMatch(i-> str.charAt(i)==str.charAt(str.length()-1-i));
        System.out.println("isPalindrome " +isPalindrome);

        //Count vowels and consonants
        long countVowel = str.chars()
                .filter(x-> x>='a' && x<='z')
                .filter(x-> "aeiou".indexOf(x)>=0)
                .count();

        System.out.println("countVowel " +countVowel);

        long consonants = str.toLowerCase().chars()
                .filter(x-> x>='a' && x<='z')
                .filter(x-> "aeiou".indexOf(x)<0)
                .count();

        System.out.println("consonants " +consonants);

        System.out.println("-----------------------------------------------------------------------");

        Employee e1 = new Employee("Deep",28,"IT",30000.00,1);
        Employee e2 = new Employee("Deep2",20,"HR",29000.00,2);
        Employee e3 = new Employee("Deep3",27,"Admin",35000.00,3);
        Employee e4 = new Employee("Deepa",29,"HR",25000.00,4);
        Employee e5 = new Employee("Deepu",30,"IT",25000.00,5);

        List<Employee> employees = Arrays.asList(e1,e2,e3,e4,e5);

        //Find the highest-paid employee
        Employee highestSalriedEmp = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).findFirst().get();
        Employee highestSalriedEmp2 =employees.stream().max(Comparator.comparing(Employee::getSalary)).get();
        System.out.println("highestSalriedEmp  "+highestSalriedEmp2);

        //Find the second-highest salary
        Employee secondhighestSalriedEmp = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).findFirst().get();
        System.out.println("secondhighestSalriedEmp  "+secondhighestSalriedEmp);

        //Group employees by department
        Map<String, List<Employee>> employeemap= employees.stream().collect(Collectors.groupingBy(Employee::getDept));
        System.out.println("Group employees by department "+employeemap);

        //Find average salary per department
        Map<String, Double> employeemap1 = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("average salary per department "+employeemap1);

        //Find the highest-paid employee in each department
        Map<String, Optional<Employee>> employeemap3 = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println("he highest-paid employee in each department "+employeemap3);

        //Sort by salary, then name
        List<Employee> employeeList1 = employees.stream().sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName)).collect(Collectors.toList());
        System.out.println("Sort by salary, then name "+employeeList1);

        //Get employee names by department
        Map<String, List<String>> empl4= employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println("employee names by department "+empl4);

        //Calculate total salary per department
        Map<String, Double> totalSal = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.summingDouble(Employee::getSalary)));
        System.out.println("Calculate total salary per department "+totalSal);

        //Find duplicate employee IDs
        employees.stream().collect(Collectors.groupingBy(Employee::getId, Collectors.counting()))
                .entrySet().stream().filter(n->n.getValue()>1)
                .map(k->k.getKey()).forEach(System.out::println);

        //Convert employee list into an ID map
        Map<Integer,Employee> map2 =employees.stream().collect(Collectors.toMap(Employee::getId, e->e));
        System.out.println("Convert employee list into an ID map "+map2);

    }
}
