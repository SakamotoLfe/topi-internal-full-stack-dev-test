package com.topi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class created to be the model for Meals.
 *
 * @since 2021-03-14
 */

@Entity(name = "meals")
public class Meal extends BasicEntity {

    /**
     * Meal's name.
     */
    @Column(length = 70, nullable = false)
    private String name;

    /**
     * Category of the meal.
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private Category category;

    /**
     * Area where this meal was created.
     */
    @Column(length = 100, nullable = false)
    private String area;

    /**
     * Meal's tags.
     */
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<Tag> tags;

    /**
     * YouTube video with a example of the meal.
     */
    @Column
    private String youtube;

    /**
     * Meal's thumbnail.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Media thumbnail;

    /**
     * Meal's sintructions.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_id")
    private List<Instruction> cookingInstructions;

    /**
     * Meal's ingredients
     */
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "meal_id")
    private List<Ingredient> ingredients;

    /* Constructors */

    /**
     * Constructor with all params.
     *
     * @param id                  ID of the entity.
     * @param regDate             Date that the entity was registered.
     * @param updateDate          Date that the entity was last updated.
     * @param enabled             If the register is deleted or not.
     * @param name                Meal's name.
     * @param category            Category of the meal.
     * @param area                Area where this meal was created.
     * @param tags                Meal's tags.
     * @param youtube             YouTube video with a example of the meal.
     * @param thumbnail           Meal's thumbnail.
     * @param cookingInstructions Meal's sintructions.
     * @param ingredients         Meal's ingredients
     */
    public Meal(Long id, Date regDate, Date updateDate, boolean enabled, String name, Category category, String area,
                List<Tag> tags, String youtube, Media thumbnail, List<Instruction> cookingInstructions,
                List<Ingredient> ingredients) {
        super(id, regDate, updateDate, enabled);
        this.name = name;
        this.category = category;
        this.area = area;
        this.tags = tags;
        this.youtube = youtube;
        this.thumbnail = thumbnail;
        this.cookingInstructions = cookingInstructions;
        this.ingredients = ingredients;
    }

    /**
     * Constructor with this class' only params
     *
     * @param name                Meal's name.
     * @param category            Category of the meal.
     * @param area                Area where this meal was created.
     * @param tags                Meal's tags.
     * @param youtube             YouTube video with a example of the meal.
     * @param thumbnail           Meal's thumbnail.
     * @param cookingInstructions Meal's sintructions.
     * @param ingredients         Meal's ingredients
     */
    public Meal(String name, Category category, String area, List<Tag> tags, String youtube, Media thumbnail,
                List<Instruction> cookingInstructions, List<Ingredient> ingredients) {
        this.name = name;
        this.category = category;
        this.area = area;
        this.tags = tags;
        this.youtube = youtube;
        this.thumbnail = thumbnail;
        this.cookingInstructions = cookingInstructions;
        this.ingredients = ingredients;
    }

    /**
     * Empty constructor.
     */
    public Meal() {
        super.setEnabled(true);
    }

    /* Getters and Setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public Media getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Media thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Instruction> getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(List<Instruction> cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /* Hash Code and Equals methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meal meal = (Meal) o;
        return name.equals(meal.name) && category.equals(meal.category) && area.equals(meal.area)
                && tags.equals(meal.tags) && Objects.equals(youtube, meal.youtube)
                && Objects.equals(thumbnail, meal.thumbnail) && cookingInstructions.equals(meal.cookingInstructions)
                && ingredients.equals(meal.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, category, area, tags, youtube, thumbnail, cookingInstructions,
                ingredients);
    }

    /* To String */

    /**
     * To String method.
     *
     * @return {@link String}. String containing the actual object values.
     */
    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", area='" + area + '\'' +
                ", tags=" + tags +
                ", youtube='" + youtube + '\'' +
                ", thumbnail=" + thumbnail +
                ", cookingInstructions=" + cookingInstructions +
                ", ingredients=" + ingredients +
                '}';
    }
}
