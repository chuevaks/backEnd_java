package lesson3.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "slot",
        "position",
        "type",
        "value"
})
public class AddToMealPlanRequest {

    @JsonProperty("date")
    private Long date;
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("type")
    private String type;
    @JsonProperty("value")
    private AddToMealPlanValue value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("date")
    public Long getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(long date) {
        this.date = date;
    }

    @JsonProperty("slot")
    public Integer getSlot() {
        return slot;
    }

    @JsonProperty("slot")
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("value")
    public AddToMealPlanValue getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(AddToMealPlanValue value) {
        this.value = value;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
