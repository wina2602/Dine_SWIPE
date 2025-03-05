package dine.swipe.Dine.Controller;

import dine.swipe.Dine.Models.GroupPreference;
import dine.swipe.Dine.Models.Rooms;
import dine.swipe.Dine.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/rooms")
public class RoomsController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<Rooms> getRoomById(@PathVariable UUID id){
        return roomService.getRoomById(id).
                map(ResponseEntity ::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Rooms>> getAllRooms(){
        List<Rooms>rooms = roomService.getAllRooms();
        return rooms.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(rooms);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Rooms> getAllRoomsCreatedByUserId(@PathVariable String userId){
        return roomService.getAllRoomsCreatedByUserID(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/preference/{groupId}")
    public ResponseEntity<String> getGroupPreferences(@PathVariable UUID groupId){
        return roomService.getGroupPreferences(groupId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Rooms> createRoom(@RequestBody Rooms room){
        Rooms savedRoom  = roomService.saveRoom(room);
        return ResponseEntity.ok(savedRoom);
    }

    @PostMapping("/savepreference")
    public ResponseEntity<GroupPreference> saveGroupPreferences(@RequestBody GroupPreference groupPreference){
        GroupPreference savedGroupPreference = roomService.saveGroupPreference(groupPreference);
        return  ResponseEntity.ok(savedGroupPreference);
    }
}
