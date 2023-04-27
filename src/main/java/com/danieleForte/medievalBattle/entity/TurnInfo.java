package com.danieleForte.medievalBattle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnInfo {
    private long battleId;
    private long characterBattleId;
}
