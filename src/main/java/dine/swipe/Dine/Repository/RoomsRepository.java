package dine.swipe.Dine.Repository;

import dine.swipe.Dine.Models.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomsRepository extends JpaRepository<Rooms, UUID> {

   Optional<Rooms> findByCreatedBy(String id);
}
