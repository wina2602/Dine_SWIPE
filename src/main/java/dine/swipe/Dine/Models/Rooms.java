package dine.swipe.Dine.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "rooms")
public class Rooms implements Serializable {

   @Id
   private UUID roomId;

   @Column(nullable = false,unique = true)
   private String roomName;

   @Column(nullable = false)
   private String createdBy;

   @Column(nullable = false)
   private int groupSize;

   @Column
   private String joiningLink;
}
