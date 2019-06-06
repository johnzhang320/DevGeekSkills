package com.prismoskills.solrj.pojos;

import org.apache.solr.client.solrj.beans.Field;

public class Item 
{
    @Field("id")
    String id;
    @Field("name")
    String name;
    @Field
    Float price;
    
    public Item() {} // Empty constructor is required
    
    public Item(String id, String name, Float price)
    {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    // Getter Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
}
