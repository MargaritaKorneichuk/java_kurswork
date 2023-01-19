package kurswork.order.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String cost;

    @NotEmpty
    private String composition;

    public Long getId() {
        return id;
    }

    public String getComposition() {
        return composition;
    }

    public String getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
