package com.danieleForte.medievalBattle.repository;

import com.danieleForte.medievalBattle.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long > {
}
