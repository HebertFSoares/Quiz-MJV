package com.quiz.mjv.repository;

import com.quiz.mjv.entity.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {
}