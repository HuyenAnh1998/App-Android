package com.example.gameadmin.quanlynguoichoi;

import java.io.Serializable;
import java.util.Comparator;

public class User implements Serializable {
    String id;
    String name, email;
    int score;
    int rank;

    public User(String name, String email, int score) {
        this.name = name;
        this.email = email;
        this.score = score;
    }

    public User(String id, String name, String email, int score) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.score = score;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getId() {
        return id;
    }
    public static Comparator<User> userComparator = new Comparator<User>() {
        @Override
        public int compare(User user, User t1) {
            return t1.getScore() - user.getScore();
        }
    };
}
