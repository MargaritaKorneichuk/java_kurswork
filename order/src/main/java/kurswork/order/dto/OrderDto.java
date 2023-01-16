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
public class OrderDto {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String composition;

    @NotEmpty
    private String status;


    public Long getId() {
        return id;
    }

    public String getComposition() {
        return composition;
    }

    public String getName() {
        return name;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
