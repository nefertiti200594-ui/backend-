package com.sara.backend;

    public class User {

        private int id;
        private String name;
        private int age;


        public void setName(String name ){
            this.name = name;

        }
           public  void  setAge(int age ){
            this.age = age;
           }

        public User(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
