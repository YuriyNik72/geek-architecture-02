package ru.geekbrains.dto;

public class ProductDto {

    private Long id;
    private Long cost;
    private String title;

    public ProductDto(Long id, Long cost, String title){
        this.id = id;
        this.cost = cost;
        this.title = title;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getCost(){
        return cost;
    }

    public void setCost(Long cost){
        this.cost = cost;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
