package dine.swipe.Dine.Service;

import dine.swipe.Dine.Models.GroupPreference;
import dine.swipe.Dine.Models.Rooms;
import dine.swipe.Dine.Repository.GroupPreferenceRepository;
import dine.swipe.Dine.Repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private GroupPreferenceRepository groupPreferenceRepository;

    public Optional<Rooms> getRoomById(UUID id){
        return roomsRepository.findById(id);
    }

    public List<Rooms> getAllRooms(){
        return roomsRepository.findAll();
    }

    public Optional<Rooms> getAllRoomsCreatedByUserID(String userID){
        return roomsRepository.findByCreatedBy(userID);
    }

    public Optional<String> getGroupPreferences(UUID groupId){
        return groupPreferenceRepository.findByGroupId(groupId)
                .map(GroupPreference::getGroupPreference);
    }

    public Rooms saveRoom(Rooms room){
        return roomsRepository.save(room);
    }

    public GroupPreference saveGroupPreference(GroupPreference groupPreference){
        return groupPreferenceRepository.save(groupPreference);
    }
}
