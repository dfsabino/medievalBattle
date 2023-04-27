package com.danieleForte.medievalBattle.repository;

import com.danieleForte.medievalBattle.entity.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long > {

    public Optional<Battle> getBattleByCodeShare(String codeShare);
}
