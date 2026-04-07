package com.sara.backend;

 import org.springframework.web.bind.annotation.*;
 import org.springframework.web.bind.annotation.PutMapping;

 import  java.util.ArrayList;
 import  java.util.List;

   @RestController
       public class HelloController {
       private List<User> users = new ArrayList<>();
       private int nextId = 1;

       @GetMapping("/hello/{name}")
       public String helloName(@PathVariable String name) {
           return "Hello " + name + "!";
       }






       @GetMapping("/users")
       public List<User> getUsers() {
           return users;
       }

       @GetMapping("/users/{id}")
       public User getUserById(@PathVariable int id) {

           for (User user : users) {
               if (user.getId() == id) {
                   return user;
               }
           }
           return null;
       }

           @PostMapping("/users")
           public Object addUser (@RequestBody User user){
                if (user.getName() == null || user.getName().isEmpty()){
                    return "Name bos ola bilmez";

                }
                if (user.getAge() < 0) {
                    return "Age menfi ola bilmez";
               }
                  String cleanName = user.getName().trim();
                  User newUser = new User (nextId, cleanName, user.getAge());
                users.add (newUser);
                nextId++;
                return  newUser;
           }

          @PutMapping("/users/{id}")
       public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {

           for (User user : users) {
               if (user.getId() == id) {
                   user.setName(updatedUser.getName());
                   user.setAge(updatedUser.getAge());
                   return user;
               }
           }

           return null;
       }
       @DeleteMapping("/users/{id}")
       public String deleteUser(@PathVariable int id) {

           for (User user : users) {
               if (user.getId() == id) {
                   users.remove(user);
                   return "User silindi";
               }
           }

           return "User tapilmadi";
       }
   }

