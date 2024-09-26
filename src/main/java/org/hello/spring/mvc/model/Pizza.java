package org.hello.spring.mvc.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizze")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min=2, max=255)
	@Column(length = 100)
	private String name;
	
	@NotNull
	@Size(max=500)
	@Column(length = 1000)
	private String description;
	
	@Column(name="image_url", nullable=true)
	private String url;

	@NotNull
	@Min(2)
	@Column(name = "price", nullable = false)
	private double price;
	
	
	private Boolean available;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	/*
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="updated_at")
	private Timestamp updatedAt;
	*/

	@NotNull
	@Max(250)
	private Integer numberOfDiscount;
	
	// 1 - Il tipo di relazione da qualificare
	// 2 - Comportamenti da seguire nel db qualora vengano cambiate le informazioni
	// A cascata verranno rimossi tutti gli sconti ad esso connessi
	@OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
	@JsonManagedReference
	private List<Discount> discounts;
	
	@Formula("( SELECT pizze.number_of_discount - count(discounts.id) " +
			"from pizze " +
			"left join discounts " +
			"on pizze.id = discounts.pizza_id " + 
			"where pizze.id = id)")
	private Integer validDiscount;
	
	//  Aggiungere relazione con categoria
	@ManyToMany()
	@JoinTable(
		name = "pizza_ingredient",
		joinColumns = @JoinColumn(name = "pizza_id"),
		inverseJoinColumns = @JoinColumn(name = "ingredient_id")
		)	
	private List<Ingredient> ingredients;
	
	public Integer getValidDiscount() {
		return validDiscount;
	}

	public void setValidDiscount(Integer validDiscount) {
		this.validDiscount = validDiscount;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getNumberOfDiscount() {
		return numberOfDiscount;
	}

	public void setNumberOfDiscount(Integer numberOfDiscount) {
		this.numberOfDiscount = numberOfDiscount;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
	
}