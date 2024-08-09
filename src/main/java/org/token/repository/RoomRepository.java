package org.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.token.entity.Room;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<UUID, Room> {
}
