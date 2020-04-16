package data;

import java.io.Serializable;

/**
 * 商品类
 * @author Lian Guan
 */
public class Product implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private int id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 发布者id
     */
    private String publisherId;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 价格
     */
    private double price;

    /**
     * 是否已经被购买，1为是
     */
    private int bought;

    /**
     * 构造方法
     * @param id 商品id
     * @param name 商品名称
     * @param publisherId 发布者id
     * @param description 描述
     * @param price 价格
     * @param bought 是否被购买（1为是）
     */
    public Product(int id, String name, String publisherId, String description, double price, int bought) {
        this.id = id;
        this.name = name;
        this.publisherId = publisherId;
        this.description = description;
        this.price = price;
        this.bought = bought;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getBought() {
        return bought;
    }

    public String getDescription() {
        return description;
    }

    public String getPublisherId() {
        return publisherId;
    }
}
