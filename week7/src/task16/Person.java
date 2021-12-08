package task16;

public class Person {

    private final String name;
    private final Integer age;

     public Person(String name, Integer age){
         this.name = name;
         this.age = age;

     }
     public String getName(){
         return this.name;
     }
     public Integer getAge(){
         return this.age;
     }
}
