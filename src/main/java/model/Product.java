package model;

import repository.generic.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`Product`")
public class Product implements GenericEntity {

    @Id
    @Column(name = "`id`")
    private String id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`categoryId`")
    private String categoryId;

    public Product() {
    }

    public Product(String id, String name, String categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String getEntityId() {
        return getId();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
