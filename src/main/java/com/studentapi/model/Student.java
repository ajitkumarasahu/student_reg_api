package com.studentapi.model;

public class Student {
    private int id;
    private String name;
    private String email;
    private String mob;
    private String course;
    private String fathername;
    private String mothername;
    private String city;
    private String pin;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMob() { return mob; }
    public void setMob(String mob) { this.mob = mob; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getFathername() { return fathername; }
    public void setFathername(String fathername) { this.fathername = fathername; }

    public String getMothername() { return mothername; }
    public void setMothername(String mothername) { this.mothername = mothername; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
}
