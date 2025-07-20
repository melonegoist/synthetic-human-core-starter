package edu.t1.synthetichumancorestarter.core.modules.command_module.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public record CommandRequest(
   @NotBlank @Size(max = 1000) String description,
   @NotBlank String priority,
   @NotBlank @Size(max = 100) String author
) {}
