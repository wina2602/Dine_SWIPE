package dine.swipe.Dine.Repository;

import dine.swipe.Dine.Models.GroupPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroupPreferenceRepository extends JpaRepository<GroupPreference, UUID> {

     Optional<GroupPreference> findByGroupId(UUID groupId);
}
