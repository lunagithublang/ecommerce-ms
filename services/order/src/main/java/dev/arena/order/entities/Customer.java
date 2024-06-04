package dev.arena.order.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Customer {
    @JsonProperty("_id")
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
