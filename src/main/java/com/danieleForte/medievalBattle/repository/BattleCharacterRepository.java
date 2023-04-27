package com.danieleForte.medievalBattle.repository;

import com.danieleForte.medievalBattle.entity.BattleCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BattleCharacterRepository extends JpaRepository<BattleCharacter, Long > {


    @Query(value ="""
            select count(BATTLE_CHARACTER_ID ) as amount from BATTLE_CHARACTER
             WHERE BATTLE_ID = ?1  group by BATTLE_ID having sum(amount ) <2
            """,nativeQuery = true)
    public Optional<Integer> getHasTwoCharacterBattle(long battleId);

    public Optional<List<BattleCharacter>> getCharacterBattleByBattleId(long battleId);
}
