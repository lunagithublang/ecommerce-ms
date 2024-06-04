package dev.arena.common.dto;

import dev.arena.common.utils.FormatDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseResponseDTO {
    private UUID id;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public String getUpdatedAt() {
        return FormatDateTime.format(updatedAt);
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return FormatDateTime.format(createdAt);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
