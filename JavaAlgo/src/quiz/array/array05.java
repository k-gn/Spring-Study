package quiz.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 나이 순 정렬
public class array05 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Person> personList = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int age = Integer.parseInt(br.readLine());
            String name = br.readLine();
            personList.add(new Person(age, name));
        }

        Collections.sort(personList);

        for(int i=0; i<n; i++) {
            Person p = personList.get(i);
            System.out.println(p.age + " " + p.name);
        }
    }

    static class Person implements Comparable {

        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Object o) {
            if(o instanceof Person){
                Person person = (Person) o;
                return this.age - person.age;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "{" +
                    "age : " + age +
                    ", name : '" + name + '\'' +
                    '}';
        }
    }
}
