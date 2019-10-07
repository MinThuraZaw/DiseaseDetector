package com.example.diseasedetector.model;

public class Data
{
    private String bloodtype;

    private String gender;

    private String updated_at;

    private String name;

    private String weight;

    private String created_at;

    private String id;

    private String age;

    private String happened;

    private String height;

    public String getBloodtype ()
    {
        return bloodtype;
    }

    public void setBloodtype (String bloodtype)
    {
        this.bloodtype = bloodtype;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getWeight ()
    {
        return weight;
    }

    public void setWeight (String weight)
    {
        this.weight = weight;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getHappened ()
    {
        return happened;
    }

    public void setHappened (String happened)
    {
        this.happened = happened;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bloodtype = "+bloodtype+", gender = "+gender+", updated_at = "+updated_at+", name = "+name+", weight = "+weight+", created_at = "+created_at+", id = "+id+", age = "+age+", happened = "+happened+", height = "+height+"]";
    }
}