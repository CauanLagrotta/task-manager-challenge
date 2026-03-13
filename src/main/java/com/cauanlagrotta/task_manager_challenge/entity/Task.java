package com.cauanlagrotta.task_manager_challenge.entity;

import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "status")
  private Status status = Status.PENDING;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User userId;

  public Task(@NotBlank String title, String description, @NotNull Status status, @NotNull User userId) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.userId = userId;
  }
}
