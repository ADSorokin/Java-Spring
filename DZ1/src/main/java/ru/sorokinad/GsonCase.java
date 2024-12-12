package ru.sorokinad;

import com.google.gson.Gson;

public class GsonCase {
    public static void main(String[] args) {
        Person person = new Person("Вася", "Пупкин", 18);
        Person person1 = new Person("Вася", "Пупкин", 19);

        Gson gson = new Gson();
        String json = gson.toJson(person);
        String json1 = gson.toJson(person1);
        System.out.println(json);
        System.out.println(json1);

        Person deserializedPerson = gson.fromJson(json, Person.class);
        Person deserializedPerson1 = gson.fromJson(json1, Person.class);
        System.out.println("Deserialized: " + deserializedPerson + " Hash: " + deserializedPerson.hashCode());
        System.out.println("Deserialized: " + deserializedPerson1 + " Hash: " + deserializedPerson1.hashCode());

    }
}