package com.danieleForte.medievalBattle.repository;

import com.danieleForte.medievalBattle.entity.BattleTurn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BattleTurnRepository extends JpaRepository<BattleTurn, Long > {

    @Query(value ="""
            SELECT *
                FROM Battle_Turn a
            inner join BATTLE_CHARACTER bc on bc.BATTLE_CHARACTER_ID =a.BATTLE_CHARACTER_ID
            inner join Battle b on b.BATTLE_ID = bc.BATTLE_ID
                WHERE a.TYPE_ACTION = 2
                AND b.BATTLE_ID = ?1
                AND ROWNUM = 1
                ORDER BY a.BATTLE_TURN_ID DESC
            """,nativeQuery = true)
    public Optional<BattleTurn> getLastDefenseByBattleId(long battleId);

    @Query(value = """
            SELECT *
                FROM Battle_Turn a
            inner join BATTLE_CHARACTER bc on bc.BATTLE_CHARACTER_ID =a.BATTLE_CHARACTER_ID
            inner join Battle b on b.BATTLE_ID = bc.BATTLE_ID
                WHERE a.TYPE_ACTION = 1
                AND b.BATTLE_ID = ?1
                AND ROWNUM = 1
                ORDER BY a.BATTLE_TURN_ID DESC
            """,nativeQuery = true)
    public Optional<BattleTurn> getLastAttackByBattleId(long battleId);

    @Query(value = """
            SELECT *
                FROM Battle_Turn a
            inner join BATTLE_CHARACTER bc on bc.BATTLE_CHARACTER_ID =a.BATTLE_CHARACTER_ID
            inner join Battle b on b.BATTLE_ID = bc.BATTLE_ID
                WHERE a.TYPE_ACTION = 0
                AND b.BATTLE_ID = ?1
                AND ROWNUM = 1
                ORDER BY a.BATTLE_TURN_ID DESC
            """,nativeQuery = true)
    public Optional<BattleTurn> getInitiativeWinByBattleId(long battleId);

    @Query(value ="""
            SELECT  count(*)
                FROM Battle_Turn a
                inner join BATTLE_CHARACTER bc on bc.BATTLE_CHARACTER_ID =a.BATTLE_CHARACTER_ID
                inner join Battle b on b.BATTLE_ID = bc.BATTLE_ID
                WHERE b.BATTLE_ID = ?1    
            """,nativeQuery = true)
    public int getQtdTurn(long battleId);
}
