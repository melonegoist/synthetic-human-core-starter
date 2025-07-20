package edu.t1.synthetichumancorestarter.core.modules.command_module.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Command(
        @NotBlank @Size(max = 1000) String description,
        @NotNull Priority priority,
        @NotBlank @Size(max = 100) String author,
        @NotNull @Pattern(regexp = "") String time //TODO
) {
}
