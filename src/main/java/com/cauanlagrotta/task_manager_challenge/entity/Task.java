package com.cauanlagrotta.task_manager_challenge.entity;

import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
}
